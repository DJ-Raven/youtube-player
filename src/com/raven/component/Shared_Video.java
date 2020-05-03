package com.raven.component;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import com.raven.master.Master;
import org.json.JSONException;
import com.raven.socket.client.Client;
import com.raven.socket.server.Server;
import com.raven.models.Video;
import com.raven.swing.ActionComponent;
import com.raven.swing.EventVideoOption;

public class Shared_Video extends javax.swing.JPanel {

    public void setEventSaveVideo(EventVideoOption eventSaveVideo) {
        list_video.setEventSaveVideo(eventSaveVideo);
    }

    public Shared_Video() {
        initComponents();
        menu.add(sp);
        list_video.setOption(3);
    }

    public void setServerName(String serverName) {
        lbServerName.setText(serverName);
    }

    public void setMemberCount(int count) {
        DecimalFormat df = new DecimalFormat("#,##0");
        cmdMember.setText(df.format(count));
    }

    public void setOnline(boolean act) {
        lbServerName.setIcon(new ImageIcon(getClass().getResource(act ? "/com/raven/icon/online.png" : "/com/raven/icon/offline.png")));
    }

    public void addVideo(Video video) {
        list_video.addVideo(video);
    }

    public void setVideo(List<Video> data) {
        list_video.setVideo(data);
    }

    public void removeVideo(String videoID) {
        for (Component com : list_video.getItems()) {
            Video_Item item = (Video_Item) com;
            if (item.getVideo().getVideoID().equals(videoID)) {
                list_video.removeVideo(item);
            }
        }
    }

    public ArrayList<Component> getItems() {
        return list_video.getItems();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menu = new javax.swing.JPopupMenu(){
            @Override
            protected void paintComponent(Graphics grphcs) {
                grphcs.setColor(new Color(206,206,206));
                grphcs.fillRect(0, 0, getWidth(), getHeight());
                grphcs.setColor(new Color(255,255,255));
                grphcs.fillRect(1,1, getWidth()-2, getHeight()-2);
            }
        };
        sp = new com.raven.swing.ScrollPane_Custom(5);
        listMember = new javax.swing.JList<>();
        lbServerName = new javax.swing.JLabel();
        line = new javax.swing.JLabel();
        list_video = new com.raven.component.List_Video();
        cmdMember = new javax.swing.JButton();

        menu.setFocusable(false);

        sp.setBorder(null);
        sp.setPreferredSize(new java.awt.Dimension(150, 200));

        listMember.setFont(new java.awt.Font("Khmer SBBIC Serif", 0, 12)); // NOI18N
        listMember.setFocusable(false);
        listMember.setSelectionBackground(new java.awt.Color(144, 202, 249));
        sp.setViewportView(listMember);

        setBackground(new java.awt.Color(255, 255, 255));

        lbServerName.setFont(new java.awt.Font("Khmer SBBIC Serif", 1, 12)); // NOI18N
        lbServerName.setForeground(new java.awt.Color(73, 139, 232));
        lbServerName.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/server.gif"))); // NOI18N
        lbServerName.setText("Server Name");

        line.setBackground(new java.awt.Color(92, 197, 253));
        line.setOpaque(true);

        list_video.setOption(3);

        cmdMember.setFont(new java.awt.Font("Khmer SBBIC Serif", 0, 11)); // NOI18N
        cmdMember.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/member.png"))); // NOI18N
        cmdMember.setText("0");
        cmdMember.setBorder(null);
        cmdMember.setContentAreaFilled(false);
        cmdMember.setFocusable(false);
        cmdMember.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/member_over.png"))); // NOI18N
        cmdMember.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdMemberActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(line, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(lbServerName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cmdMember)
                .addContainerGap())
            .addComponent(list_video, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbServerName, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdMember, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(line, javax.swing.GroupLayout.PREFERRED_SIZE, 1, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(list_video, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cmdMemberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdMemberActionPerformed
        if (Server.getInstance().isWorking()) {
            try {
                if (Server.getInstance().getMemberCount() > 0) {
                    DefaultListModel mod = new DefaultListModel();
                    for (Object m : Server.getInstance().getAllMember()) {
                        mod.addElement(m.toString());
                    }
                    listMember.setModel(mod);
                    menu.show(cmdMember, -115, 27);
                }
            } catch (JSONException e) {
                Master.getInstance().showError(e);
            }
        } else {
            if (Client.getInstance().getMemberCount() > 0) {
                DefaultListModel mod = new DefaultListModel();
                for (Object m : Client.getInstance().getMembers()) {
                    mod.addElement(m.toString());
                }
                listMember.setModel(mod);
                menu.show(cmdMember, -115, 27);
            }
        }
    }//GEN-LAST:event_cmdMemberActionPerformed

    public void addActionComponent(ActionComponent event) {
        list_video.addActionComponent(event);
    }

    public void initStatusPlaying(Video video) {
        list_video.initStatusPlaying(video);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmdMember;
    private javax.swing.JLabel lbServerName;
    private javax.swing.JLabel line;
    private javax.swing.JList<String> listMember;
    private com.raven.component.List_Video list_video;
    private javax.swing.JPopupMenu menu;
    private javax.swing.JScrollPane sp;
    // End of variables declaration//GEN-END:variables
}
