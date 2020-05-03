package com.raven.component;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import com.raven.master.Master;
import org.json.JSONException;
import com.raven.program_data.ProgramData;
import com.raven.socket.client.Client;
import com.raven.socket.server.Server;
import com.raven.video_player.Video_File;
import com.raven.models.Video;
import com.raven.swing.DragScrollListener;
import com.raven.swing.EventVideoOption;
import com.raven.swing.EventVideoRemove;

public class Video_Item extends javax.swing.JPanel {

    public void setEventSaveVideo(EventVideoOption eventSaveVideo) {
        this.eventSaveVideo = eventSaveVideo;
    }

    public void setEventRemoveVideo(EventVideoRemove eventRemoveVideo) {
        this.eventRemoveVideo = eventRemoveVideo;
    }

    public boolean isStatusPlay() {
        return statusPlay;
    }

    public Video getVideo() {
        return video;
    }
    private EventVideoOption eventSaveVideo;
    private EventVideoRemove eventRemoveVideo;
    private final Video video;
    private boolean statusPlay;
    private final int option;

    public void setStatusPlay(boolean play) {
        statusPlay = play;
        repaint();
    }

    public Video_Item(Video video, int option) {
        initComponents();
        menu_list_Video.add(panel);
        this.video = video;
        this.option = option;
        title.setText(video.getVideoTitle());
        channel.setText(video.getChannalTitle() + " ");
        lbTime.setText(video.getDuration());
        setImage();
    }

    private void setImage() {
        if (video.getThumbnailURL().equals("")) {
            Icon img = Video_File.getInstance().getThumbs(video.getVideoURL());
            lbTime.setText(Video_File.getInstance().getTime());
            if (img != null) {
                lbImage.setImage(img);
            }
        } else {
            try {
                URL url = new URL(video.getThumbnailURL());
                Image image = ImageIO.read(url);
                lbImage.setImage(new ImageIcon(image));
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menu_list_Video = new javax.swing.JPopupMenu(){
            @Override
            protected void paintComponent(Graphics grphcs) {
                grphcs.setColor(new Color(206,206,206));
                grphcs.fillRect(0, 0, getWidth(), getHeight());
                grphcs.setColor(new Color(255,255,255));
                grphcs.fillRect(1,1, getWidth()-2, getHeight()-2);
            }
        };
        panel = new javax.swing.JPanel();
        cmdSave = new com.raven.swing.RButton();
        cmdRemove = new com.raven.swing.RButton();
        cmdShare = new com.raven.swing.RButton();
        channel = new javax.swing.JLabel();
        title = new javax.swing.JLabel();
        cmdOption = new javax.swing.JButton();
        lbImage = new com.raven.swing.PictureBox();
        lbTime = new javax.swing.JLabel();

        menu_list_Video.setFocusable(false);

        panel.setBackground(new java.awt.Color(255, 255, 255));
        panel.setLayout(new javax.swing.BoxLayout(panel, javax.swing.BoxLayout.PAGE_AXIS));

        cmdSave.setBackground(new java.awt.Color(255, 255, 255));
        cmdSave.setForeground(new java.awt.Color(51, 51, 51));
        cmdSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/save.png"))); // NOI18N
        cmdSave.setText("Save");
        cmdSave.setColorHover(new java.awt.Color(243, 243, 243));
        cmdSave.setColorTextHover(new java.awt.Color(51, 51, 51));
        cmdSave.setFocusable(false);
        cmdSave.setFont(new java.awt.Font("Khmer SBBIC Serif", 0, 12)); // NOI18N
        cmdSave.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        cmdSave.setMaximumSize(new java.awt.Dimension(135, 26));
        cmdSave.setMinimumSize(new java.awt.Dimension(135, 26));
        cmdSave.setPreferredSize(new java.awt.Dimension(135, 26));
        cmdSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdSaveActionPerformed(evt);
            }
        });
        panel.add(cmdSave);

        cmdRemove.setBackground(new java.awt.Color(255, 255, 255));
        cmdRemove.setForeground(new java.awt.Color(206, 25, 25));
        cmdRemove.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/delete.png"))); // NOI18N
        cmdRemove.setText("Remove");
        cmdRemove.setColorHover(new java.awt.Color(243, 243, 243));
        cmdRemove.setColorTextHover(new java.awt.Color(206, 25, 25));
        cmdRemove.setFocusable(false);
        cmdRemove.setFont(new java.awt.Font("Khmer SBBIC Serif", 0, 12)); // NOI18N
        cmdRemove.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        cmdRemove.setMaximumSize(new java.awt.Dimension(135, 26));
        cmdRemove.setMinimumSize(new java.awt.Dimension(135, 26));
        cmdRemove.setPreferredSize(new java.awt.Dimension(135, 26));
        cmdRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdRemoveActionPerformed(evt);
            }
        });
        panel.add(cmdRemove);

        cmdShare.setBackground(new java.awt.Color(255, 255, 255));
        cmdShare.setForeground(new java.awt.Color(51, 51, 51));
        cmdShare.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/shared.png"))); // NOI18N
        cmdShare.setText("Share to server");
        cmdShare.setColorHover(new java.awt.Color(243, 243, 243));
        cmdShare.setColorTextHover(new java.awt.Color(51, 51, 51));
        cmdShare.setFocusable(false);
        cmdShare.setFont(new java.awt.Font("Khmer SBBIC Serif", 0, 12)); // NOI18N
        cmdShare.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        cmdShare.setMaximumSize(new java.awt.Dimension(135, 26));
        cmdShare.setMinimumSize(new java.awt.Dimension(135, 26));
        cmdShare.setPreferredSize(new java.awt.Dimension(135, 26));
        cmdShare.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdShareActionPerformed(evt);
            }
        });
        panel.add(cmdShare);

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(221, 221, 221)));

        channel.setFont(new java.awt.Font("Khmer SBBIC Serif", 2, 11)); // NOI18N
        channel.setText("channel");

        title.setFont(new java.awt.Font("Khmer SBBIC Serif", 1, 12)); // NOI18N
        title.setForeground(new java.awt.Color(37, 37, 37));
        title.setText("Title");

        cmdOption.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/more.png"))); // NOI18N
        cmdOption.setBorder(null);
        cmdOption.setContentAreaFilled(false);
        cmdOption.setFocusable(false);
        cmdOption.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/more_over.png"))); // NOI18N
        cmdOption.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdOptionActionPerformed(evt);
            }
        });

        lbImage.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(221, 221, 221)));
        lbImage.setFill(true);
        lbImage.setImage(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/play.png"))); // NOI18N
        lbImage.setMaximumSize(new java.awt.Dimension(128, 25));
        lbImage.setMinimumSize(new java.awt.Dimension(128, 25));

        lbTime.setBackground(new java.awt.Color(0,0,0,150));
        lbTime.setForeground(new java.awt.Color(255, 255, 255));
        lbTime.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTime.setText("0:00");
        lbTime.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 5, 1, 5));
        lbTime.setOpaque(true);

        lbImage.setLayer(lbTime, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout lbImageLayout = new javax.swing.GroupLayout(lbImage);
        lbImage.setLayout(lbImageLayout);
        lbImageLayout.setHorizontalGroup(
            lbImageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, lbImageLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbTime)
                .addGap(3, 3, 3))
        );
        lbImageLayout.setVerticalGroup(
            lbImageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, lbImageLayout.createSequentialGroup()
                .addContainerGap(49, Short.MAX_VALUE)
                .addComponent(lbTime)
                .addGap(3, 3, 3))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(lbImage, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cmdOption))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(channel, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(cmdOption, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(title, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(1, 1, 1)
                        .addComponent(channel))
                    .addComponent(lbImage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cmdOptionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdOptionActionPerformed
        if (option == 1) {
            if (ProgramData.videoSaved().isExit(video)) {
                cmdSave.setVisible(false);
            }
        } else if (option == 2) {
            cmdSave.setVisible(false);
        } else if (option == 3) {
            if (ProgramData.videoSaved().isExit(video)) {
                cmdSave.setVisible(false);
            }
            cmdShare.setVisible(false);
        }
        if (option != 3) {
            if (video.getVideoID().equals("")) {
                cmdShare.setVisible(false);
            } else {
                if (Server.getInstance().isWorking() || (Client.getInstance().isWorking() && Client.getInstance().isHasName() && Client.getInstance().isConnecting())) {
                    cmdShare.setVisible(true);
                } else {
                    cmdShare.setVisible(false);
                }
            }
        }
        if (option == 3 && !Server.getInstance().isWorking()) {
            if (Client.getInstance().isWorking() && Client.getInstance().isHasName() && Client.getInstance().isConnecting()) {
                if (video.getChannalTitle().equals("Shared by : " + Client.getInstance().getClientName())) {
                    cmdRemove.setVisible(true);
                } else {
                    cmdRemove.setVisible(false);
                }
            } else {
                cmdRemove.setVisible(false);
            }
        }
        if (cmdSave.isVisible() || cmdRemove.isVisible() || cmdShare.isVisible()) {
            menu_list_Video.show(cmdOption, -112, 20);
        }
    }//GEN-LAST:event_cmdOptionActionPerformed

    private void cmdSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdSaveActionPerformed
        try {
            Video v = video.copy();
            ProgramData.videoSaved().saveVideo(v);
            menu_list_Video.setVisible(false);
            eventSaveVideo.execute(v);
            cmdSave.setVisible(false);
        } catch (IOException | ClassNotFoundException e) {
            Master.getInstance().showError(e);
        }
    }//GEN-LAST:event_cmdSaveActionPerformed

    private void cmdRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdRemoveActionPerformed
        try {
            if (option == 2) {
                ProgramData.videoSaved().removeVideo(video);
            } else if (option == 3) {
                if (Server.getInstance().isWorking()) {
                    ProgramData.videoShared().removeVideo(video);
                    Server.getInstance().removeVideo(video.getVideoID());
                } else {
                    Client.getInstance().removeVideo(video);
                }
            }
            menu_list_Video.setVisible(false);
            eventRemoveVideo.execute(this);
        } catch (IOException | ClassNotFoundException | JSONException e) {
            Master.getInstance().showError(e);
        }
    }//GEN-LAST:event_cmdRemoveActionPerformed

    private void cmdShareActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdShareActionPerformed
        try {
            if (Server.getInstance().isWorking()) {
                Server.getInstance().sendVideo(video);
            } else if (Client.getInstance().isWorking() && Client.getInstance().isConnecting() && Client.getInstance().isHasName()) {
                Client.getInstance().sendVideo(video);
            }
            menu_list_Video.setVisible(false);
        } catch (JSONException e) {
            Master.getInstance().showError(e);
        }
    }//GEN-LAST:event_cmdShareActionPerformed

    public void initEvent(DragScrollListener scroll) {
        lbImage.addMouseListener(scroll);
        lbImage.addMouseMotionListener(scroll);
    }

    @Override
    public synchronized void addMouseListener(MouseListener ml) {
        super.addMouseListener(ml);
        lbImage.addMouseListener(ml);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (statusPlay) {
            g.setColor(new Color(245, 109, 109));
            g.fillRoundRect(0, getSize().height - 3, getSize().width, getSize().height, 0, 0);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel channel;
    private javax.swing.JButton cmdOption;
    private com.raven.swing.RButton cmdRemove;
    private com.raven.swing.RButton cmdSave;
    private com.raven.swing.RButton cmdShare;
    private com.raven.swing.PictureBox lbImage;
    private javax.swing.JLabel lbTime;
    private javax.swing.JPopupMenu menu_list_Video;
    private javax.swing.JPanel panel;
    private javax.swing.JLabel title;
    // End of variables declaration//GEN-END:variables
}
