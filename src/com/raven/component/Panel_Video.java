package com.raven.component;

import com.raven.message.Message_Error;
import com.raven.message.D_Shared;
import com.raven.glasspane.GlassPane;
import com.raven.master.Master;
import java.awt.Component;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.raven.program_data.ProgramData;
import com.raven.socket.client.Client;
import com.raven.socket.data.Event;
import com.raven.socket.data.EventConnecting;
import com.raven.socket.data.EventNewVideo;
import com.raven.socket.server.Server;
import com.raven.models.Video;
import com.raven.program_data.PropertiesManagement;
import com.raven.swing.ActionComponent;
import com.raven.swing.EventVideoOption;

public class Panel_Video extends javax.swing.JPanel {

    private int playingOn = 1;

    public Panel_Video() {
        initComponents();
        list_video.setDropTarget(new DropTarget() {
            @Override
            public synchronized void drop(DropTargetDropEvent evt) {
                try {
                    evt.acceptDrop(DnDConstants.ACTION_COPY);
                    List<File> droppedFiles = (List<File>) evt.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);
                    for (File file : droppedFiles) {
                        Video v = new Video("", file.getAbsolutePath(), file.getName(), "", "", "", "");
                        list_video.addVideo(v);
                    }
                } catch (UnsupportedFlavorException | IOException e) {
                    System.err.println(e.getMessage());
                }
            }
        });
        saved_video.setOption(2);
        chAutoPlay.setOn(PropertiesManagement.getInstance().getData().isAutoPlay());
    }

    public void addListVideo(List<Video> datas) {
        list_video.setVideo(datas);
    }

    public void addSavedVideo(List<Video> datas) {
        saved_video.setVideo(datas);
    }

    public void initVideoSaved() {
        saved_video.setVideo(ProgramData.videoSaved().getVideo());
        EventVideoOption event = new EventVideoOption() {
            @Override
            public void execute(Video video) {
                saved_video.addVideo(video);
            }
        };
        list_video.setEventSaveVideo(event);
        shared_video.setEventSaveVideo(event);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        header = new javax.swing.JPanel();
        chAutoPlay = new com.raven.swing.Switch();
        jLabel3 = new javax.swing.JLabel();
        cmdListVideo = new com.raven.swing.RButton();
        cmdSaveVideo = new com.raven.swing.RButton();
        l1 = new javax.swing.JLabel();
        l2 = new javax.swing.JLabel();
        cmdShared = new com.raven.swing.RButton();
        l3 = new javax.swing.JLabel();
        line = new javax.swing.JLabel();
        body = new javax.swing.JLayeredPane();
        list_video = new com.raven.component.List_Video();
        saved_video = new com.raven.component.List_Video();
        shared_video = new com.raven.component.Shared_Video();

        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(206, 206, 206)));

        header.setBackground(new java.awt.Color(255, 255, 255));

        chAutoPlay.setBackgroundColor(new java.awt.Color(71, 173, 255));
        chAutoPlay.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                chAutoPlayMouseClicked(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Khmer SBBIC Serif", 0, 11)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(77, 77, 77));
        jLabel3.setText("AUTOPLAY");

        cmdListVideo.setBackground(new java.awt.Color(255, 255, 255));
        cmdListVideo.setForeground(new java.awt.Color(51, 51, 51));
        cmdListVideo.setText("List Video");
        cmdListVideo.setColorHover(new java.awt.Color(255, 255, 255));
        cmdListVideo.setColorTextHover(new java.awt.Color(51, 51, 51));
        cmdListVideo.setFocusable(false);
        cmdListVideo.setFont(new java.awt.Font("Khmer SBBIC Serif", 0, 13)); // NOI18N
        cmdListVideo.setPaintLine(true);
        cmdListVideo.setRippleColor(new java.awt.Color(236, 234, 234));
        cmdListVideo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdListVideoActionPerformed(evt);
            }
        });

        cmdSaveVideo.setBackground(new java.awt.Color(255, 255, 255));
        cmdSaveVideo.setForeground(new java.awt.Color(51, 51, 51));
        cmdSaveVideo.setText("Saved Video");
        cmdSaveVideo.setColorHover(new java.awt.Color(255, 255, 255));
        cmdSaveVideo.setColorTextHover(new java.awt.Color(51, 51, 51));
        cmdSaveVideo.setFocusable(false);
        cmdSaveVideo.setFont(new java.awt.Font("Khmer SBBIC Serif", 0, 13)); // NOI18N
        cmdSaveVideo.setRippleColor(new java.awt.Color(236, 234, 234));
        cmdSaveVideo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdSaveVideoActionPerformed(evt);
            }
        });

        l1.setBackground(new java.awt.Color(206, 206, 206));
        l1.setOpaque(true);

        l2.setBackground(new java.awt.Color(206, 206, 206));
        l2.setOpaque(true);

        cmdShared.setBackground(new java.awt.Color(255, 255, 255));
        cmdShared.setForeground(new java.awt.Color(51, 51, 51));
        cmdShared.setText("Shared");
        cmdShared.setColorHover(new java.awt.Color(255, 255, 255));
        cmdShared.setColorTextHover(new java.awt.Color(51, 51, 51));
        cmdShared.setFocusable(false);
        cmdShared.setFont(new java.awt.Font("Khmer SBBIC Serif", 0, 13)); // NOI18N
        cmdShared.setRippleColor(new java.awt.Color(236, 234, 234));
        cmdShared.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdSharedActionPerformed(evt);
            }
        });

        l3.setBackground(new java.awt.Color(206, 206, 206));
        l3.setOpaque(true);

        javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
        header.setLayout(headerLayout);
        headerLayout.setHorizontalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, headerLayout.createSequentialGroup()
                .addComponent(cmdListVideo, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(l1, javax.swing.GroupLayout.PREFERRED_SIZE, 1, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(cmdSaveVideo, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(l2, javax.swing.GroupLayout.PREFERRED_SIZE, 1, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(cmdShared, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(l3, javax.swing.GroupLayout.PREFERRED_SIZE, 1, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(3, 3, 3)
                .addComponent(chAutoPlay, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        headerLayout.setVerticalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(chAutoPlay, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addComponent(l2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                .addComponent(cmdListVideo, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addComponent(cmdSaveVideo, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addComponent(cmdShared, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
            .addComponent(l1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(l3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        line.setBackground(new java.awt.Color(206, 206, 206));
        line.setOpaque(true);

        body.setLayout(new java.awt.CardLayout());
        body.add(list_video, "card2");
        body.add(saved_video, "card3");
        body.add(shared_video, "card4");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(header, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(276, 276, 276)
                .addComponent(line, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(body, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(line, javax.swing.GroupLayout.PREFERRED_SIZE, 1, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(body, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents

    public void setShow(int option) {
        if (option == 1) {
            cmdListVideoActionPerformed(null);
        } else if (option == 2) {
            cmdSaveVideoActionPerformed(null);
        } else if (option == 3) {
            cmdSharedActionPerformed(null);
        }
    }
    private void cmdListVideoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdListVideoActionPerformed
        list_video.setVisible(true);
        saved_video.setVisible(false);
        shared_video.setVisible(false);
        cmdListVideo.setPaintLine(true);
        cmdSaveVideo.setPaintLine(false);
        cmdShared.setPaintLine(false);
    }//GEN-LAST:event_cmdListVideoActionPerformed

    private void cmdSaveVideoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdSaveVideoActionPerformed
        list_video.setVisible(false);
        saved_video.setVisible(true);
        shared_video.setVisible(false);
        cmdListVideo.setPaintLine(false);
        cmdSaveVideo.setPaintLine(true);
        cmdShared.setPaintLine(false);
    }//GEN-LAST:event_cmdSaveVideoActionPerformed

    private D_Shared message;

    private void createClientEvent() {
        Client.getInstance().setEventClientDeconnect(new Event() {
            @Override
            public void execute(Object... data) {
                shared_video.setMemberCount(Client.getInstance().getMemberCount());
            }
        });
    }

    private void createMessage() {
        message = new D_Shared();
        message.setEventCreateServer(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Server.getInstance().setEvent(new Event() {
                    @Override
                    public void execute(Object... data) {
                        shared_video.setMemberCount(Server.getInstance().getMemberCount());
                    }
                }, new Event() {
                    @Override
                    public void execute(Object... data) {
                        shared_video.setMemberCount(Server.getInstance().getMemberCount());
                    }
                }, new Event() {
                    @Override
                    public void execute(Object... data) {
                        shared_video.removeVideo(data[0].toString());
                    }
                }, new EventNewVideo() {
                    @Override
                    public void execute(Video video) {
                        shared_video.addVideo(video);
                    }
                });
                shared_video.setVideo(ProgramData.videoShared().getVideo());
                GlassPane.getInstance().close();
                list_video.setVisible(false);
                saved_video.setVisible(false);
                shared_video.setVisible(true);
                cmdListVideo.setPaintLine(false);
                cmdSaveVideo.setPaintLine(false);
                cmdShared.setPaintLine(true);
                shared_video.setServerName(Server.getInstance().getServerName());
            }
        });
        message.setEventClientConnect(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                shared_video.setServerName(Client.getInstance().getServerName());
                GlassPane.getInstance().close();
                list_video.setVisible(false);
                saved_video.setVisible(false);
                shared_video.setVisible(true);
                cmdListVideo.setPaintLine(false);
                cmdSaveVideo.setPaintLine(false);
                cmdShared.setPaintLine(true);
            }
        });
        message.setEventNewClient(new Event() {
            @Override
            public void execute(Object... data) {
                shared_video.setMemberCount(Client.getInstance().getMemberCount());
            }
        });
        message.setEvent_error_message(new Event() {
            @Override
            public void execute(Object... data) {
                GlassPane.getInstance().show(new Message_Error(data[0].toString()));
            }
        });
        message.setEvent_remove_video(new Event() {
            @Override
            public void execute(Object... data) {
                shared_video.removeVideo(data[0].toString());
            }
        });
        message.setEventOnlineOffline(new EventConnecting() {
            @Override
            public void execute(EventConnecting.ConnectType type) {
                shared_video.setOnline(type == ConnectType.SUCCESS);
            }
        });
        message.setEvent_new_video(new EventNewVideo() {
            @Override
            public void execute(Video video) {
                shared_video.addVideo(video);
            }
        });
        createClientEvent();
    }
    private void cmdSharedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdSharedActionPerformed
        if (Server.getInstance().isWorking() || (Client.getInstance().isWorking() && Client.getInstance().isHasName())) {
            list_video.setVisible(false);
            saved_video.setVisible(false);
            shared_video.setVisible(true);
            cmdListVideo.setPaintLine(false);
            cmdSaveVideo.setPaintLine(false);
            cmdShared.setPaintLine(true);
        } else {
            if (message == null) {
                createMessage();
            }
            GlassPane.getInstance().show(message);
            message.open();
        }
    }//GEN-LAST:event_cmdSharedActionPerformed

    private void chAutoPlayMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chAutoPlayMouseClicked
        try {
            PropertiesManagement.getInstance().store("auto_play", chAutoPlay.isOn() + "");
            PropertiesManagement.getInstance().getData().setAutoPlay(chAutoPlay.isOn());
        } catch (IOException e) {
            Master.getInstance().showError(e);
        }
    }//GEN-LAST:event_chAutoPlayMouseClicked
    public void addEvent(ActionComponent event) {
        list_video.addActionComponent(event);
        saved_video.addActionComponent(event);
        shared_video.addActionComponent(event);
        list_video.addActionComponent(new ActionComponent() {
            @Override
            public void execute(Component component, MouseEvent me) {
                playingOn = 1;
            }
        });
        saved_video.addActionComponent(new ActionComponent() {
            @Override
            public void execute(Component component, MouseEvent me) {
                playingOn = 2;
            }
        });
        shared_video.addActionComponent(new ActionComponent() {
            @Override
            public void execute(Component component, MouseEvent me) {
                playingOn = 3;
            }
        });
    }

    public Video getVideoNext() {
        if (chAutoPlay.isOn()) {
            ArrayList<Component> items;
            if (playingOn == 1) {
                items = list_video.getItems();
            } else if (playingOn == 2) {
                items = saved_video.getItems();
            } else {
                items = shared_video.getItems();
            }
            int indexPlay = -1;
            int i = -1;
            for (Component com : items) {
                i++;
                Video_Item item = (Video_Item) com;
                if (item.isStatusPlay()) {
                    indexPlay = i;
                    break;
                }
            }
            if (indexPlay >= 0) {
                if (indexPlay == items.size() - 1) {
                    indexPlay = 0;
                } else {
                    indexPlay++;
                }
                Video_Item item = (Video_Item) items.get(indexPlay);
                return item.getVideo();
            }
            return null;
        } else {
            return null;
        }
    }

    public void initStatusPlaying(Video video) {
        list_video.initStatusPlaying(video);
        saved_video.initStatusPlaying(video);
        shared_video.initStatusPlaying(video);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane body;
    private com.raven.swing.Switch chAutoPlay;
    private com.raven.swing.RButton cmdListVideo;
    private com.raven.swing.RButton cmdSaveVideo;
    private com.raven.swing.RButton cmdShared;
    private javax.swing.JPanel header;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel l1;
    private javax.swing.JLabel l2;
    private javax.swing.JLabel l3;
    private javax.swing.JLabel line;
    private com.raven.component.List_Video list_video;
    private com.raven.component.List_Video saved_video;
    private com.raven.component.Shared_Video shared_video;
    // End of variables declaration//GEN-END:variables
}
