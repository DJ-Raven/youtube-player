package com.raven.socket.client;

import com.raven.socket.data.EventConnecting;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import java.net.URISyntaxException;
import java.util.ArrayList;
import com.raven.master.Master;
import org.json.JSONException;
import org.json.JSONObject;
import com.raven.socket.data.Event;
import com.raven.socket.data.EventNewVideo;
import com.raven.models.Video;

public class Client {

    private static Client instance;
    private static final int DEFAULT_PORT = 9999;
    private Socket client;
    private boolean connect;
    private Event event_check_client;
    private Event event_new_client;
    private Event event_client_deconnect;
    private Event event_error_message;
    private Event event_remove_video;
    private EventConnecting eventOnlineOffline;
    private EventNewVideo event_new_video;
    private String clientName;
    private String serverName;
    private final ArrayList<String> members;

    private Client() {
        members = new ArrayList();
    }

    public static Client getInstance() {
        if (instance == null) {
            instance = new Client();
        }
        return instance;
    }

    public void connect(String ip, EventConnecting event) throws URISyntaxException {
        if (client != null) {
            client.close();
            client = null;
        }
        client = IO.socket("http://" + ip + ":" + DEFAULT_PORT);
        client.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                if (connect == false) {
                    event.execute(EventConnecting.ConnectType.SUCCESS);
                    eventOnlineOffline.execute(EventConnecting.ConnectType.SUCCESS);
                }
                connect = true;
            }
        });
        client.on(Socket.EVENT_RECONNECT, new Emitter.Listener() {
            @Override
            public void call(Object... os) {
                eventOnlineOffline.execute(EventConnecting.ConnectType.SUCCESS);
                sendMessage("new_client", clientName);
            }
        });
        client.on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {
            @Override
            public void call(Object... os) {
                eventOnlineOffline.execute(EventConnecting.ConnectType.ERROR);
            }
        });
        client.on(Socket.EVENT_CONNECT_ERROR, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                if (!connect) {
                    event.execute(EventConnecting.ConnectType.ERROR);
                    closeClient();
                }
                System.out.println("Error ..");
            }
        });
        client.on("check_client", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                String name = args[0].toString();
                if (!name.equals("")) {
                    clientName = name;
                    serverName = args[1].toString();
                }
                event_check_client.execute(args);
            }
        });
        client.on("new_client", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                if (clientName != null) {
                    String name = args[0].toString();
                    if (!name.equals(clientName)) {
                        members.add(name);
                        event_new_client.execute(args);
                    }
                }
            }
        });
        client.on("get_all_client", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                members.clear();
                for (Object o : args) {
                    String name = o.toString();
                    members.add(name);
                }
                event_new_client.execute(args);
            }
        });
        client.on("client_deconnect", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                String name = args[0].toString();
                removeClient(name);
                event_client_deconnect.execute(args);
            }
        });
        client.on("new_video", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                try {
                    JSONObject obj = (JSONObject) args[0];
                    String videoID = obj.getString("videoID");
                    String videoURL = obj.getString("videoURL");
                    String videoTitle = obj.getString("videoTitle");
                    String thumbnailURL = obj.getString("thumbnailURL");
                    String description = obj.getString("description");
                    String duration = obj.getString("duration");
                    String channalTitle = obj.getString("channalTitle");
                    Video video = new Video(videoID, videoURL, videoTitle, thumbnailURL, description, duration, channalTitle);
                    event_new_video.execute(video);
                } catch (JSONException e) {
                    Master.getInstance().showError(e);
                }
            }
        });
        client.on("get_all_video_shared", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                try {
                    for (Object o : args) {
                        JSONObject obj = (JSONObject) o;
                        String videoID = obj.getString("videoID");
                        String videoURL = obj.getString("videoURL");
                        String videoTitle = obj.getString("videoTitle");
                        String thumbnailURL = obj.getString("thumbnailURL");
                        String description = obj.getString("description");
                        String duration = obj.getString("duration");
                        String channalTitle = obj.getString("channalTitle");
                        Video video = new Video(videoID, videoURL, videoTitle, thumbnailURL, description, duration, channalTitle);
                        event_new_video.execute(video);
                    }
                } catch (JSONException e) {
                    Master.getInstance().showError(e);
                }
            }
        });
        client.on("remove_video", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                event_remove_video.execute(args);
            }
        });
        client.on("error_message", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                event_error_message.execute(args);
            }
        });
        client.open();
    }

    public void setEvent(Event event_check_client, Event event_new_client, Event event_error_message, Event event_remove_video, EventConnecting eventOnlineOffline, EventNewVideo event_new_video) {
        this.event_check_client = event_check_client;
        this.event_new_client = event_new_client;
        this.event_error_message = event_error_message;
        this.event_remove_video = event_remove_video;
        this.eventOnlineOffline = eventOnlineOffline;
        this.event_new_video = event_new_video;
    }

    public void setEventClientDeconnect(Event event_client_deconnect) {
        this.event_client_deconnect = event_client_deconnect;
    }

    public void sendMessage(String messageType, Object data) {
        client.emit(messageType, data);
    }

    public void sendVideo(Video video) throws JSONException {
        JSONObject json = new JSONObject();
        json.put("videoID", video.getVideoID());
        json.put("videoURL", video.getVideoURL());
        json.put("videoTitle", video.getVideoTitle());
        json.put("thumbnailURL", video.getThumbnailURL());
        json.put("description", video.getDescription());
        json.put("duration", video.getDuration());
        json.put("channalTitle", "Shared by : " + clientName);
        client.emit("new_video", json);
    }

    public void removeVideo(Video video) throws JSONException {
        JSONObject json = new JSONObject();
        json.put("videoID", video.getVideoID());
        json.put("videoURL", video.getVideoURL());
        json.put("videoTitle", video.getVideoTitle());
        json.put("thumbnailURL", video.getThumbnailURL());
        json.put("description", video.getDescription());
        json.put("duration", video.getDuration());
        json.put("channalTitle", "Shared by : " + clientName);
        client.emit("remove_video", json);
    }

    public void closeClient() {
        if (client != null) {
            connect = false;
            client.close();
            client = null;
        }
    }

    public boolean isWorking() {
        return connect;
    }

    public boolean isHasName() {
        return clientName != null;
    }

    public String getClientName() {
        return clientName;
    }

    private void removeClient(String clientName) {
        for (String m : members) {
            if (m.equals(clientName)) {
                members.remove(m);
                break;
            }
        }
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public int getMemberCount() {
        return members.size();
    }

    public ArrayList<String> getMembers() {
        return members;
    }

    public boolean isConnecting() {
        return client.connected();
    }
}
