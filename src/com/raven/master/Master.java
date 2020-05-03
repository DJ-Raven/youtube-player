package com.raven.master;

import java.util.List;
import javax.swing.JFrame;
import com.raven.models.Video;

public class Master {

    private static Master instance;
    private JFrame fram;
    private List<Video> playlist;

    private Master() {
    }

    public static Master getInstance() {
        if (instance == null) {
            instance = new Master();
        }
        return instance;
    }

    public void showError(Exception e) {
        e.printStackTrace();
    }

    public void setFram(JFrame fram) {
        this.fram = fram;
    }

    public JFrame getFram() {
        return fram;
    }

    public List<Video> getPlaylist() {
        return playlist;
    }

    public void setPlaylist(List<Video> playlist) {
        this.playlist = playlist;
    }
}
