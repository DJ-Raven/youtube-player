package com.raven.program_data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import com.raven.models.Video;

public class ProgramData {

    private static ProgramData videoSaved;
    private static ProgramData videoShared;
    private final String rootDirectory = "C:\\ProgramData\\youtuble player";
    private List<Video> file_video;
    private final String fileName;

    private ProgramData(String fileName) {
        file_video = new ArrayList<>();
        this.fileName = fileName;
    }

    public static ProgramData videoSaved() {
        if (videoSaved == null) {
            videoSaved = new ProgramData("video_save.rv");
        }
        return videoSaved;
    }

    public static ProgramData videoShared() {
        if (videoShared == null) {
            videoShared = new ProgramData("video_share.rv");
        }
        return videoShared;
    }

    public boolean init() throws ClassNotFoundException, IOException {
        boolean isOld;
        File file = new File(rootDirectory);
        if (file.exists()) {
            isOld = true;
            loadVideo();
        } else {
            isOld = false;
            file.mkdir();
        }
        removeNoExitFile();
        return isOld;
    }

    private void loadVideo() throws ClassNotFoundException, IOException {
        File file = new File(rootDirectory + "\\" + fileName);
        if (file.exists()) {
            FileInputStream in = new FileInputStream(file);
            ObjectInputStream oin = new ObjectInputStream(in);
            file_video = (List<Video>) oin.readObject();
            oin.close();
            in.close();
        }
    }

    private void removeNoExitFile() throws ClassNotFoundException, IOException {
        boolean remove = false;
        for (int i = 0; i < file_video.size(); i++) {
            Video v = file_video.get(i);
            if (v.getThumbnailURL().equals("")) {
                if (!new File(v.getVideoURL()).exists()) {
                    remove = true;
                    file_video.remove(v);
                    i--;
                }
            }
        }
        if (remove) {
            saveFile(file_video);
        }
    }

    private void saveFile(List<Video> data) throws ClassNotFoundException, IOException {
        File file = new File(rootDirectory + "\\" + fileName);
        FileOutputStream out = new FileOutputStream(file);
        ObjectOutputStream oout = new ObjectOutputStream(out);
        oout.writeObject(data);
        oout.close();
        out.close();
    }

    public void saveVideo(Video video) throws ClassNotFoundException, IOException {
        File file = new File(rootDirectory + "\\" + fileName);
        if (!file.exists()) {
            file_video = new ArrayList<>();
        }
        file_video.add(video);
        saveFile(file_video);
    }

    public void removeVideo(Video video) throws ClassNotFoundException, IOException {
        for (Video v : file_video) {
            if (v.getVideoURL().equals(video.getVideoURL())) {
                file_video.remove(v);
                saveFile(file_video);
                break;
            }
        }
    }

    public List<Video> getVideo() {
        return file_video;
    }

    public boolean isExit(Video video) {
        boolean saved = false;
        if (video.getThumbnailURL().equals("")) {
            for (Video v : file_video) {
                if (video.getVideoURL().equals(v.getVideoURL())) {
                    saved = true;
                }
            }
        } else {
            for (Video v : file_video) {
                if (video.getVideoID().equals(v.getVideoID())) {
                    saved = true;
                }
            }
        }
        return saved;
    }
}
