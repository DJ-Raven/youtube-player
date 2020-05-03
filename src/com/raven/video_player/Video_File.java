package com.raven.video_player;

import java.awt.image.BufferedImage;
import java.util.concurrent.CountDownLatch;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import uk.co.caprica.vlcj.factory.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.base.MediaPlayer;
import uk.co.caprica.vlcj.player.base.MediaPlayerEventAdapter;

public class Video_File {

    private static Video_File instance;
    private static final String[] VLC_ARGS = {
        "--intf", "dummy", /* no interface */
        "--vout", "dummy", /* we don't want video (output) */
        "--no-audio", /* we don't want audio (decoding) */
        "--no-snapshot-preview", /* no blending in dummy vout */};

    private static final float VLC_THUMBNAIL_POSITION = 30.0f / 100.0f;
    private final MediaPlayer mediaPlayer;
    final CountDownLatch inPositionLatch;
    final CountDownLatch snapshotTakenLatch;
    private String time = "0:00";

    private Video_File() {
        MediaPlayerFactory factory = new MediaPlayerFactory(VLC_ARGS);
        mediaPlayer = factory.mediaPlayers().newMediaPlayer();
        inPositionLatch = new CountDownLatch(1);
        snapshotTakenLatch = new CountDownLatch(1);
        mediaPlayer.events().addMediaPlayerEventListener(new MediaPlayerEventAdapter() {
            @Override
            public void positionChanged(MediaPlayer mediaPlayer, float newPosition) {
                if (newPosition >= VLC_THUMBNAIL_POSITION * 0.9f) {
                    inPositionLatch.countDown();
                }
            }

            @Override
            public void lengthChanged(MediaPlayer mediaPlayer, long newLength) {
                time = convertSecondsToHMmSs(newLength);
            }

            @Override
            public void snapshotTaken(MediaPlayer mediaPlayer, String filename) {
                snapshotTakenLatch.countDown();
            }
        });
    }

    public static Video_File getInstance() {
        if (instance == null) {
            instance = new Video_File();
        }
        return instance;
    }

    public Icon getThumbs(String url) {
        Icon icon = null;
        try {
            mediaPlayer.media().start(url);
            mediaPlayer.controls().pause();
            Thread.sleep(100);
            mediaPlayer.controls().setTime(0);
            BufferedImage img = mediaPlayer.snapshots().get(110, 70);
            icon = new ImageIcon(img);
            mediaPlayer.controls().stop();
        } catch (InterruptedException | NullPointerException e) {
            System.err.println(e.getMessage());
        }
        return icon;
    }

    public String getTime() {
        return time;
    }

    public String convertSecondsToHMmSs(long seconds) {
        String hms;
        if (seconds > 3600000) {
            hms = String.format("%01d:%02d:%02d", seconds / (3600 * 1000), seconds / (60 * 1000) % 60, seconds / 1000 % 60);
        } else {
            hms = String.format("%01d:%02d", seconds / (60 * 1000) % 60, seconds / 1000 % 60);
        }
        return hms;
    }

}
