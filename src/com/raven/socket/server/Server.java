package com.raven.socket.server;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;
import java.io.IOException;
import java.util.ArrayList;
import com.raven.master.Master;
import org.json.JSONException;
import com.raven.program_data.ProgramData;
import com.raven.socket.data.Event;
import com.raven.socket.data.EventNewVideo;
import com.raven.models.Video;

public class Server {

    private static Server instance;
    private static final int DEFAULT_PORT = 9999;
    private SocketIOServer server;
    private String serverName;
    private Event event_new_client;
    private Event event_client_diconnect;
    private Event event_remove_video;
    private EventNewVideo event_new_video;

    private Server() {
    }

    public static Server getInstance() {
        if (instance == null) {
            instance = new Server();
        }
        return instance;
    }

    public void createServer(String serverName) throws Exception {
        this.serverName = serverName;
        Configuration configuration = new Configuration();
        configuration.setPort(DEFAULT_PORT);
        server = new SocketIOServer(configuration);
        server.addEventListener("new_client", Object.class, new DataListener<Object>() {
            @Override
            public void onData(SocketIOClient client, Object data, AckRequest ackRequest) {
                try {
                    String name = data.toString();
                    if (checkMember(name)) {
                        client.set("name", name);
                        client.sendEvent("check_client", name, serverName);
                        client.sendEvent("get_all_client", getAllMember());
                        client.sendEvent("get_all_video_shared", getAllVideoShared());
                        server.getBroadcastOperations().sendEvent("new_client", name);
                        event_new_client.execute(name);
                    } else {
                        client.sendEvent("check_client", "");
                    }
                } catch (JSONException e) {
                    Master.getInstance().showError(e);
                }
            }
        });
        server.addEventListener("remove_video", Video.class, new DataListener<Video>() {
            @Override
            public void onData(SocketIOClient client, Video data, AckRequest ackRequest) {
                try {
                    ProgramData.videoShared().removeVideo(data);
                    event_remove_video.execute(data.getVideoID());
                    removeVideo(data.getVideoID());
                } catch (ClassNotFoundException | IOException e) {
                    Master.getInstance().showError(e);
                }
            }
        });
        server.addEventListener("new_video", Video.class, new DataListener<Video>() {
            @Override
            public void onData(SocketIOClient client, Video data, AckRequest ackRequest) {
                if (ProgramData.videoShared().isExit(data)) {
                    client.sendEvent("error_message", "This video has already");
                } else {
                    server.getBroadcastOperations().sendEvent("new_video", data);
                    event_new_video.execute(data);
                    try {
                        ProgramData.videoShared().saveVideo(data);
                    } catch (IOException | ClassNotFoundException e) {
                        Master.getInstance().showError(e);
                    }
                }
            }
        });
        server.addConnectListener(new ConnectListener() {
            @Override
            public void onConnect(SocketIOClient sioc) {
                sioc.set("name", "");
            }
        });
        server.addDisconnectListener(new DisconnectListener() {
            @Override
            public void onDisconnect(SocketIOClient sioc) {
                String name = sioc.get("name");
                if (!name.equals("")) {
                    event_client_diconnect.execute(sioc.get("name").toString());
                    server.getBroadcastOperations().sendEvent("client_deconnect", name);
                }
            }
        });
        server.start();
    }

    public void closeServer() {
        if (server != null) {
            server.stop();
            server = null;
        }
    }

    public void sendVideo(Video video) {
        Video v = video.copy();
        v.setChannalTitle("Server");
        server.getBroadcastOperations().sendEvent("new_video", v);
        event_new_video.execute(v);
        try {
            ProgramData.videoShared().saveVideo(video);
        } catch (IOException | ClassNotFoundException e) {
            Master.getInstance().showError(e);
        }
    }

    public void removeVideo(String videoID) {
        server.getBroadcastOperations().sendEvent("remove_video", videoID);
    }

    public void setEvent(Event event_new_client, Event event_client_diconnect, Event event_remove_video, EventNewVideo event_new_video) {
        this.event_new_client = event_new_client;
        this.event_client_diconnect = event_client_diconnect;
        this.event_remove_video = event_remove_video;
        this.event_new_video = event_new_video;
    }

    public boolean isWorking() {
        return server != null;
    }

    public String getServerName() {
        return serverName;
    }

    public int getMemberCount() {
        int count = 0;
        for (SocketIOClient client : server.getAllClients()) {
            if (!client.get("name").toString().equals("")) {
                count++;
            }
        }
        return count;
    }

    private boolean checkMember(String name) {
        for (SocketIOClient client : server.getAllClients()) {
            if (client.get("name").toString().equals(name)) {
                return false;
            }
        }
        return true;
    }

    public Object[] getAllMember() throws JSONException {
        ArrayList<String> m = new ArrayList<>();
        for (SocketIOClient client : server.getAllClients()) {
            String name = client.get("name").toString();
            if (!name.equals("")) {
                m.add(name);
            }
        }
        return m.toArray();
    }

    private Object[] getAllVideoShared() {
        return ProgramData.videoShared().getVideo().toArray();
    }
}
