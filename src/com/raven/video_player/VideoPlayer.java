package com.raven.video_player;

import com.sun.jna.NativeLibrary;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import uk.co.caprica.vlcj.binding.RuntimeUtil;
import uk.co.caprica.vlcj.factory.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.base.Equalizer;
import uk.co.caprica.vlcj.player.base.MediaPlayer;
import uk.co.caprica.vlcj.player.base.MediaPlayerEventAdapter;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import uk.co.caprica.vlcj.player.embedded.fullscreen.windows.Win32FullScreenStrategy;
import com.raven.swing.Event;

public class VideoPlayer extends javax.swing.JPanel {

    private MediaPlayerFactory factory;
    private EmbeddedMediaPlayer mediaPlayer;
    private final String NATIVE_LIBRARY_SEARCH_PATH = "VLC";
    private final String DUMP_NATIVE_MEMORY = "false";
    private String time = "0:00";
    private int lastVolum = 0;
    private boolean mouseOver = false;
    private Snapshot snapshot;
    private boolean isFinish = true;
    private Event finishEvent;
    private boolean finisOK;
    private EventTransferHandler eventDropFile;

    public VideoPlayer() {
        initComponents();
    }

    public MediaPlayerFactory getMediaPlayerFactory() {
        return factory;
    }

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    public Equalizer getEqualizer() {
        return factory.equalizer().newEqualizer();
    }

    public void init(JFrame fram) {
        if (null != NATIVE_LIBRARY_SEARCH_PATH) {
            NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(), NATIVE_LIBRARY_SEARCH_PATH);
        }
        System.setProperty("jna.dump_memory", DUMP_NATIVE_MEMORY);
        factory = new MediaPlayerFactory();
        mediaPlayer = factory.mediaPlayers().newEmbeddedMediaPlayer();
        mediaPlayer.fullScreen().strategy(new Win32FullScreenStrategy(fram));
        mediaPlayer.input().enableKeyInputHandling(false);
        mediaPlayer.input().enableMouseInputHandling(false);
        mediaPlayer.events().addMediaPlayerEventListener(new MediaPlayerEventAdapter() {
            @Override
            public void buffering(MediaPlayer mediaPlayer, float newCache) {
                //      System.out.println("Buffering " + newCache);
            }

            @Override
            public void finished(MediaPlayer mediaPlayer) {
                if (finisOK) {
                    isFinish = true;
                    progressBar.setMaximum(0);
                    time = "0:00";
                    showTime("0:00");
                    cmdPlay.setIcon(new ImageIcon(getClass().getResource("/com/raven/video_player/play.png")));
                    cmdPlay.setName("Stop");
                    if (finishEvent != null) {
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    Thread.sleep(2000);
                                } catch (InterruptedException e) {
                                    System.err.println(e.getMessage());
                                }
                                finishEvent.finish();
                            }
                        }).start();
                    }
                }
            }

            @Override
            public void lengthChanged(MediaPlayer media, long newLength) {
                time = convertSecondsToHMmSs(newLength);
                progressBar.setMaximum((int) newLength);
                showTime("0:00");
                cmdPlay.setIcon(new ImageIcon(getClass().getResource("/com/raven/video_player/pause.png")));
                cmdPlay.setName("Play");
            }

            @Override
            public void mediaPlayerReady(MediaPlayer mediaPlayer) {
                isFinish = false;
                pbVolum.setValue(mediaPlayer.audio().volume());
                lastVolum = pbVolum.getValue();
                finisOK = true;
            }

            @Override
            public void timeChanged(MediaPlayer mediaPlayer, long newTime) {
                showTime(convertSecondsToHMmSs(newTime));
                if (!progressBar.isDragged()) {
                    progressBar.setValue((int) newTime);
                }
            }
        });
        mediaPlayer.videoSurface().set(factory.videoSurfaces().newVideoSurface(canvas));
        snapshot = new Snapshot(fram, false);
        try {
            MyTransferHandler obj = new MyTransferHandler(mediaPlayer, eventDropFile);
            border.setTransferHandler(obj);
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }

    public void prepare(String url) {
        mediaPlayer.media().prepare(url);
    }

    public void play() {
        finisOK = false;
        mediaPlayer.controls().play();
        cmdPlay.setIcon(new ImageIcon(getClass().getResource("/com/raven/video_player/pause.png")));
        cmdPlay.setName("Play");
    }

    public void pause() {
        if (!isFinish || !mediaPlayer.media().isValid()) {
            mediaPlayer.controls().pause();
            if (cmdPlay.getName().equals("Play")) {
                cmdPlay.setIcon(new ImageIcon(getClass().getResource("/com/raven/video_player/play.png")));
                cmdPlay.setName("Stop");
            } else {
                cmdPlay.setIcon(new ImageIcon(getClass().getResource("/com/raven/video_player/pause.png")));
                cmdPlay.setName("Play");
            }
        } else {
            prepare(mediaPlayer.media().info().mrl());
            play();
        }
    }

    private void volumnIcon() {
        String status;
        int val = pbVolum.getValue();
        if (val > 60) {
            status = "3";
        } else if (val > 30) {
            status = "2";
        } else if (val > 0) {
            status = "1";
        } else {
            status = "0";
        }
        String imageName = "volum_" + status + ".png";
        if (!cmdVolum.getName().equals(status)) {
            cmdVolum.setIcon(new ImageIcon(getClass().getResource("/com/raven/video_player/" + imageName)));
            cmdVolum.setName(status);
            if (status.equals("0")) {
                pbVolum.setForeground(new Color(148, 148, 148));
            } else {
                pbVolum.setForeground(Color.WHITE);
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        border = new javax.swing.JPanel();
        canvas = new java.awt.Panel();
        panel = new javax.swing.JPanel();
        progressBar = new com.raven.video_player.ProgressBar();
        cmdPlay = new javax.swing.JButton();
        cmdVolum = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        pbVolum = new com.raven.video_player.ProgressBar();
        lbTime = new javax.swing.JLabel();
        cmdFullScreen = new javax.swing.JButton();

        setMinimumSize(new java.awt.Dimension(390, 204));

        border.setBackground(new java.awt.Color(0, 0, 0));
        border.setLayout(new java.awt.BorderLayout());

        canvas.setBackground(new java.awt.Color(0, 0, 0));
        canvas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                canvasMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                canvasMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                canvasMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout canvasLayout = new javax.swing.GroupLayout(canvas);
        canvas.setLayout(canvasLayout);
        canvasLayout.setHorizontalGroup(
            canvasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 390, Short.MAX_VALUE)
        );
        canvasLayout.setVerticalGroup(
            canvasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 167, Short.MAX_VALUE)
        );

        border.add(canvas, java.awt.BorderLayout.CENTER);

        panel.setBackground(new java.awt.Color(0, 0, 0));

        progressBar.setBackground(new java.awt.Color(71, 71, 71));
        progressBar.setForeground(new java.awt.Color(198, 36, 36));
        progressBar.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                progressBarMouseDragged(evt);
            }
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                progressBarMouseMoved(evt);
            }
        });
        progressBar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                progressBarMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                progressBarMouseReleased(evt);
            }
        });

        cmdPlay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/video_player/play.png"))); // NOI18N
        cmdPlay.setBorder(null);
        cmdPlay.setContentAreaFilled(false);
        cmdPlay.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cmdPlay.setName("Stop"); // NOI18N
        cmdPlay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdPlayActionPerformed(evt);
            }
        });

        cmdVolum.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/video_player/volum_2.png"))); // NOI18N
        cmdVolum.setBorder(null);
        cmdVolum.setContentAreaFilled(false);
        cmdVolum.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cmdVolum.setName("2"); // NOI18N
        cmdVolum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdVolumActionPerformed(evt);
            }
        });

        jPanel1.setOpaque(false);

        pbVolum.setBackground(new java.awt.Color(99, 99, 99));
        pbVolum.setForeground(new java.awt.Color(255, 255, 255));
        pbVolum.setValue(50);
        pbVolum.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                pbVolumStateChanged(evt);
            }
        });
        pbVolum.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pbVolumMousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pbVolum, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(pbVolum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lbTime.setForeground(new java.awt.Color(255, 255, 255));
        lbTime.setText("0:00 / 00:00");
        lbTime.setBorder(null);

        cmdFullScreen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/video_player/full_ screen.png"))); // NOI18N
        cmdFullScreen.setBorder(null);
        cmdFullScreen.setContentAreaFilled(false);
        cmdFullScreen.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cmdFullScreen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdFullScreenActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cmdPlay, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdVolum, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbTime, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cmdFullScreen, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(progressBar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(cmdPlay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmdVolum, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbTime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmdFullScreen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(border, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(border, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void progressBarMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_progressBarMouseReleased
        mediaPlayer.controls().setTime(progressBar.getValue());
        if (mouseDragged) {
            snapshot.setVisible(false);
        }
        mouseDragged = false;
    }//GEN-LAST:event_progressBarMouseReleased

    private void cmdPlayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdPlayActionPerformed
        pause();
    }//GEN-LAST:event_cmdPlayActionPerformed

    private void cmdVolumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdVolumActionPerformed
        if (cmdVolum.getName().equals("0")) {
            if (pbVolum.getValue() == 0) {
                if (lastVolum == 0) {
                    lastVolum = 50;
                }
                pbVolum.setValue(lastVolum);
            }
            pbVolumStateChanged(null);
        } else {
            cmdVolum.setName("0");
            cmdVolum.setIcon(new ImageIcon(getClass().getResource("/com/raven/video_player/volum_0.png")));
            mediaPlayer.audio().setVolume(0);
            pbVolum.setForeground(new Color(148, 148, 148));
        }
    }//GEN-LAST:event_cmdVolumActionPerformed
    public void refresh() {
        mediaPlayer.controls().play();
    }
    private void pbVolumStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_pbVolumStateChanged
        mediaPlayer.audio().setVolume(pbVolum.getValue());
        volumnIcon();
    }//GEN-LAST:event_pbVolumStateChanged

    private void cmdFullScreenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdFullScreenActionPerformed
        mediaPlayer.fullScreen().toggle();
    }//GEN-LAST:event_cmdFullScreenActionPerformed

    private void pbVolumMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pbVolumMousePressed
        if (pbVolum.getValue() != 0) {
            lastVolum = pbVolum.getValue();
        }
    }//GEN-LAST:event_pbVolumMousePressed

    private void canvasMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_canvasMouseEntered
        mouseOver = true;
    }//GEN-LAST:event_canvasMouseEntered

    private void canvasMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_canvasMouseExited
        mouseOver = false;
    }//GEN-LAST:event_canvasMouseExited

    private void canvasMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_canvasMouseReleased
        if (mouseOver) {
            if (SwingUtilities.isLeftMouseButton(evt)) {
                if (evt.getClickCount() == 2) {
                    cmdFullScreenActionPerformed(null);
                }
                pause();
            }
        }
    }//GEN-LAST:event_canvasMouseReleased

    private void progressBarMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_progressBarMouseMoved
        int mouseX = evt.getX();
        int progressBarVal = (int) Math.round(((double) mouseX / (double) progressBar.getWidth()) * progressBar.getMaximum());
        snapshot.setVisible(true);
        int startX = (int) this.getLocationOnScreen().getX() + 1;
        int endX = startX + this.getWidth() - 2;
        int y = (int) this.getLocationOnScreen().getY() + this.getHeight() - 60;
        int x = evt.getXOnScreen() - 25;
        if (x < startX) {
            x = startX;
        }
        if (x + 50 > endX) {
            x = endX - 50;
        }
        snapshot.setLocation(x, y);
        snapshot.setTime(convertSecondsToHMmSs(progressBarVal));
    }//GEN-LAST:event_progressBarMouseMoved

    private void progressBarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_progressBarMouseExited
        if (!mouseDragged) {
            snapshot.setVisible(false);
        }
    }//GEN-LAST:event_progressBarMouseExited

    private boolean mouseDragged = false;
    private void progressBarMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_progressBarMouseDragged
        mouseDragged = true;
        snapshot.setVisible(true);
        int startX = (int) this.getLocationOnScreen().getX();
        int endX = startX + this.getWidth();
        int y = (int) this.getLocationOnScreen().getY() + this.getHeight() - 60;
        int x = evt.getXOnScreen() - 25;
        if (x < startX) {
            x = startX;
        }
        if (x + 50 > endX) {
            x = endX - 50;
        }
        snapshot.setLocation(x, y);
        snapshot.setTime(convertSecondsToHMmSs(progressBar.getValue()));
    }//GEN-LAST:event_progressBarMouseDragged

    public String convertSecondsToHMmSs(long seconds) {
        String hms;
        if (seconds > 3600000) {
            hms = String.format("%01d:%02d:%02d", seconds / (3600 * 1000), seconds / (60 * 1000) % 60, seconds / 1000 % 60);
        } else {
            hms = String.format("%01d:%02d", seconds / (60 * 1000) % 60, seconds / 1000 % 60);
        }
        return hms;
    }

    public void showTime(String start) {
        lbTime.setText(start + " / " + time);
    }

    public void close() {
        mediaPlayer.controls().stop();
        mediaPlayer.release();
        factory.release();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel border;
    private java.awt.Panel canvas;
    private javax.swing.JButton cmdFullScreen;
    private javax.swing.JButton cmdPlay;
    private javax.swing.JButton cmdVolum;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbTime;
    private javax.swing.JPanel panel;
    private com.raven.video_player.ProgressBar pbVolum;
    private com.raven.video_player.ProgressBar progressBar;
    // End of variables declaration//GEN-END:variables

    public void setFinishEvent(Event finishEvent) {
        this.finishEvent = finishEvent;
    }

    public void setEventDropFile(EventTransferHandler eventDropFile) {
        this.eventDropFile = eventDropFile;
    }
}
