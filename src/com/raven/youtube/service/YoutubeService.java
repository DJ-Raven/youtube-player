package com.raven.youtube.service;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.services.samples.youtube.cmdline.Auth;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.PlaylistItem;
import com.google.api.services.youtube.model.PlaylistItemListResponse;
import com.google.api.services.youtube.model.ResourceId;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.VideoListResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import com.raven.models.Video;

public class YoutubeService {

    private static YoutubeService instance;
    private final long NUMBER_OF_VIDEOS_RETURNED = 10;
    private final YouTube youtube;
    private final YouTube.Search.List search;
    private final YouTube.Videos.List video_detail;

    private YoutubeService() throws IOException {
        youtube = new YouTube.Builder(Auth.HTTP_TRANSPORT, Auth.JSON_FACTORY, new HttpRequestInitializer() {
            @Override
            public void initialize(HttpRequest request) throws IOException {
            }
        }).setApplicationName("youtube-cmdline-search-sample").build();
        search = youtube.search().list("id,snippet");
        video_detail = youtube.videos().list("contentDetails");
        String apiKey = "AIzaSyA-_WBbCPC25UegJ3nEzzpeSjGHjdB3hCs";
        search.setKey(apiKey);
        video_detail.setKey(apiKey);
        search.setType("video");
        search.setFields("items(id/kind,id/videoId,snippet/title,snippet/channelTitle)");
        video_detail.setFields("items(contentDetails/duration)");
    }

    public static YoutubeService getInstance() throws IOException {
        if (instance == null) {
            instance = new YoutubeService();
        }
        return instance;
    }

    public List<Video> search(String query) throws IOException {
        List<Video> datas = new ArrayList<>();
        search.setMaxResults(NUMBER_OF_VIDEOS_RETURNED);
        search.setQ(query);
        SearchListResponse searchResponse = search.execute();
        List<SearchResult> searchResultList = searchResponse.getItems();
        if (searchResultList != null) {
            Iterator<SearchResult> iteratorSearchResults = searchResultList.iterator();
            while (iteratorSearchResults.hasNext()) {
                SearchResult singleVideo = iteratorSearchResults.next();
                ResourceId rId = singleVideo.getId();
                if (rId.getKind().equals("youtube#video")) {
                    String videoID = rId.getVideoId();
                    String videoTitle = singleVideo.getSnippet().getTitle();
                    String thumbnailURL = "https://i.ytimg.com/vi/" + videoID + "/mqdefault.jpg";
                    String description = "";
                    String channalTitle = singleVideo.getSnippet().getChannelTitle();
                    String videoURL = "https://www.youtube.com/watch?v=" + videoID;
                    datas.add(new Video(videoID, videoURL, videoTitle, thumbnailURL, description, getDurationVideo(videoID), channalTitle));
                }
            }
        }
        return datas;
    }

    public List<Video> getRelateVideo(String video) throws IOException {
        List<Video> datas = new ArrayList<>();
        search.setMaxResults(5l);
        search.setRelatedToVideoId(video);
        SearchListResponse searchResponse = search.execute();
        List<SearchResult> searchResultList = searchResponse.getItems();
        if (searchResultList != null) {
            Iterator<SearchResult> iteratorSearchResults = searchResultList.iterator();
            while (iteratorSearchResults.hasNext()) {
                SearchResult singleVideo = iteratorSearchResults.next();
                ResourceId rId = singleVideo.getId();
                if (rId.getKind().equals("youtube#video")) {
                    String videoID = rId.getVideoId();
                    String videoTitle = singleVideo.getSnippet().getTitle();
                    String thumbnailURL = "https://i.ytimg.com/vi/" + videoID + "/mqdefault.jpg";
                    String description = "";
                    String channalTitle = singleVideo.getSnippet().getChannelTitle();
                    String videoURL = "https://www.youtube.com/watch?v=" + videoID;
                    datas.add(new Video(videoID, videoURL, videoTitle, thumbnailURL, description, getDurationVideo(videoID), channalTitle));
                }
            }
        }
        return datas;
    }

    public List<Video> getPlaylistVideo(String playlistID, long maxResult) throws IOException {
        List<Video> datas = new ArrayList<>();
        YouTube.PlaylistItems.List playlist = youtube.playlistItems().list("snippet");
        playlist.setFields("items(snippet/resourceId/videoId,snippet/title,snippet/channelTitle)");
        playlist.setMaxResults(maxResult);
        playlist.setPlaylistId(playlistID);
        String apiKey = "AIzaSyA-_WBbCPC25UegJ3nEzzpeSjGHjdB3hCs";
        playlist.setKey(apiKey);
        PlaylistItemListResponse searchResponse = playlist.execute();
        List<PlaylistItem> searchResultList = searchResponse.getItems();
        if (searchResultList != null) {
            Iterator<PlaylistItem> iteratorSearchResults = searchResultList.iterator();
            while (iteratorSearchResults.hasNext()) {
                PlaylistItem singleVideo = iteratorSearchResults.next();
                String videoID = singleVideo.getSnippet().getResourceId().getVideoId();
                String videoTitle = singleVideo.getSnippet().getTitle();
                String thumbnailURL = "https://i.ytimg.com/vi/" + videoID + "/mqdefault.jpg";
                String description = "";
                String channalTitle = singleVideo.getSnippet().getChannelTitle();
                String videoURL = "https://www.youtube.com/watch?v=" + videoID;
                datas.add(new Video(videoID, videoURL, videoTitle, thumbnailURL, description, getDurationVideo(videoID), channalTitle));
            }
        }
        return datas;
    }

    public String[] searchSuggestion(String search) throws MalformedURLException, IOException, ParseException {
        JSONParser parser = new JSONParser();
        search = search.replace(" ", "+");
        URL oracle = new URL("http://suggestqueries.google.com/complete/search?q=" + search + "&client=firefox&hl=fr");
        URLConnection yc = oracle.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
        String inputLine;
        String val = "";
        while ((inputLine = in.readLine()) != null) {
            JSONArray a = (JSONArray) parser.parse(inputLine);
            for (Object o : a) {
                val = o.toString();
            }
        }
        in.close();
        String v[] = val.replace("[", "").replace("]", "").replace("\"", "").split(",");
        if ((v.length == 1 && v[0].equals(""))) {
            return new String[0];
        } else {
            if (v.length == 1 && v[0].equals("{google:fieldtrialtriggered:true}")) {
                return searchSuggestion(search);
            } else {
                return v;
            }
        }
    }

    public String getDurationVideo(String videoID) throws IOException {
        video_detail.setId(videoID);
        VideoListResponse searchResponse = video_detail.execute();
        List<com.google.api.services.youtube.model.Video> searchResultList = searchResponse.getItems();
        if (searchResultList != null) {
            com.google.api.services.youtube.model.Video singleVideo = searchResultList.get(0);
            String duration = singleVideo.getContentDetails().getDuration();
            return convertYoutubeDuration(duration);
        }
        return "0:00";
    }

    public String convertYoutubeDuration(String duration) {
        long seconds = Duration.parse(duration).toMillis();
        String hms;
        if (seconds > 3600000) {
            hms = String.format("%01d:%02d:%02d", seconds / (3600 * 1000), seconds / (60 * 1000) % 60, seconds / 1000 % 60);
        } else {
            hms = String.format("%01d:%02d", seconds / (60 * 1000) % 60, seconds / 1000 % 60);
        }
        return hms;
    }
}
