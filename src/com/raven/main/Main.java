package com.raven.main;

import com.raven.glasspane.GlassPane;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import com.raven.master.Master;
import org.json.simple.parser.ParseException;
import com.raven.splashscreen.Loading;
import com.raven.video_player.EventTransferHandler;
import com.raven.swing.TextPrompt;
import com.raven.models.Video;
import com.raven.youtube.service.YoutubeService;
import com.raven.swing.ActionComponent;
import com.raven.component.Video_Item;
import com.raven.swing.Event;

public class Main extends javax.swing.JFrame {

    private final DefaultListModel mod;

    public Main() {
        initComponents();
        setIconImage(new ImageIcon(getClass().getResource("/com/raven/icon/logo.png")).getImage());
        mod = new DefaultListModel();
        list.setModel(mod);
        menu.add(panel);
        TextPrompt obj = new TextPrompt("Search Here ...", txtSearch, TextPrompt.Show.ALWAYS);
        Master.getInstance().setFram(Main.this);
        GlassPane.getInstance().apply(Main.this);
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
        panel = new javax.swing.JPanel();
        list = new javax.swing.JList<>();
        home = new javax.swing.JPanel();
        layout = new javax.swing.JLayeredPane();
        player = new com.raven.video_player.VideoPlayer();
        panel_video = new com.raven.component.Panel_Video();
        equalizer = new com.raven.equalizer.EqualizerPanel();
        rPanelShadow1 = new com.raven.swing.RPanelShadow();
        txtSearch = new com.raven.swing.RTextField();
        cmdSearch = new com.raven.swing.RButton();

        menu.setFocusable(false);

        list.setFont(new java.awt.Font("Khmer SBBIC Serif", 0, 12)); // NOI18N
        list.setFocusable(false);
        list.setSelectionBackground(new java.awt.Color(204, 204, 204));
        list.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(list, javax.swing.GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE)
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(list, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Youtube Video Player");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        home.setBackground(new java.awt.Color(255, 255, 255));

        player.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(206, 206, 206)));
        player.setMinimumSize(new java.awt.Dimension(392, 206));

        layout.setLayer(player, javax.swing.JLayeredPane.DEFAULT_LAYER);
        layout.setLayer(panel_video, javax.swing.JLayeredPane.DEFAULT_LAYER);
        layout.setLayer(equalizer, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout layoutLayout = new javax.swing.GroupLayout(layout);
        layout.setLayout(layoutLayout);
        layoutLayout.setHorizontalGroup(
            layoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layoutLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(player, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(equalizer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel_video, javax.swing.GroupLayout.PREFERRED_SIZE, 432, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layoutLayout.setVerticalGroup(
            layoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layoutLayout.createSequentialGroup()
                .addGroup(layoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layoutLayout.createSequentialGroup()
                        .addComponent(player, javax.swing.GroupLayout.DEFAULT_SIZE, 335, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(equalizer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(panel_video, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(6, 6, 6))
        );

        rPanelShadow1.setBackground(new java.awt.Color(255, 255, 255));
        rPanelShadow1.setShowRightShadow(false);

        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSearchKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSearchKeyTyped(evt);
            }
        });

        cmdSearch.setText("Search");
        cmdSearch.setFocusable(false);
        cmdSearch.setFont(new java.awt.Font("Khmer SBBIC Serif", 0, 14)); // NOI18N
        cmdSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdSearchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout rPanelShadow1Layout = new javax.swing.GroupLayout(rPanelShadow1);
        rPanelShadow1.setLayout(rPanelShadow1Layout);
        rPanelShadow1Layout.setHorizontalGroup(
            rPanelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, rPanelShadow1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cmdSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        rPanelShadow1Layout.setVerticalGroup(
            rPanelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rPanelShadow1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(rPanelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(rPanelShadow1Layout.createSequentialGroup()
                        .addComponent(cmdSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGap(7, 7, 7))
                    .addComponent(txtSearch, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout homeLayout = new javax.swing.GroupLayout(home);
        home.setLayout(homeLayout);
        homeLayout.setHorizontalGroup(
            homeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(layout, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(rPanelShadow1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        homeLayout.setVerticalGroup(
            homeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, homeLayout.createSequentialGroup()
                .addComponent(rPanelShadow1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(layout))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(home, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(home, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setSize(new java.awt.Dimension(1257, 754));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        player.setEventDropFile(new EventTransferHandler() {
            @Override
            public void execute(String url) {
                panel_video.initStatusPlaying(null);
            }
        });
        player.init(this);
        equalizer.init(player.getMediaPlayerFactory(), player.getMediaPlayer(), player.getEqualizer());
        player.setFinishEvent(new Event() {
            @Override
            public void finish() {
                Video video = panel_video.getVideoNext();
                if (video != null) {
                    player.prepare(video.getVideoURL());
                    player.play();
                    panel_video.initStatusPlaying(video);
                }
            }
        });
        panel_video.addEvent(new ActionComponent() {
            @Override
            public void execute(Component component, MouseEvent me) {
                Video_Item item = (Video_Item) component;
                player.prepare(item.getVideo().getVideoURL());
                player.play();
                panel_video.initStatusPlaying(item.getVideo());
            }
        });
        panel_video.initVideoSaved();
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (Master.getInstance().getPlaylist() != null) {
                    panel_video.addListVideo(Master.getInstance().getPlaylist());
                }
            }
        }).start();
    }//GEN-LAST:event_formWindowOpened

    private void listMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listMouseClicked
        if (!list.isSelectionEmpty()) {
            txtSearch.setText(mod.getElementAt(list.getSelectedIndex()).toString());
            menu.setVisible(false);
            cmdSearchActionPerformed(null);
        }
    }//GEN-LAST:event_listMouseClicked

    private void txtSearchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyPressed
        if (menu.isVisible()) {
            switch (evt.getKeyCode()) {
                case KeyEvent.VK_UP:
                    if (list.isSelectionEmpty()) {
                        list.setSelectedIndex(mod.getSize() - 1);
                    } else {
                        int selected = list.getSelectedIndex();
                        if (selected == 0) {
                            list.setSelectedIndex(mod.getSize() - 1);
                        } else {
                            list.setSelectedIndex(selected - 1);
                        }
                    }
                    if (!list.isSelectionEmpty()) {
                        txtSearch.setText(mod.getElementAt(list.getSelectedIndex()).toString());
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if (list.isSelectionEmpty()) {
                        list.setSelectedIndex(0);
                    } else {
                        int selected = list.getSelectedIndex();
                        if (selected == mod.getSize() - 1) {
                            list.setSelectedIndex(0);
                        } else {
                            list.setSelectedIndex(selected + 1);
                        }
                    }
                    if (!list.isSelectionEmpty()) {
                        txtSearch.setText(mod.getElementAt(list.getSelectedIndex()).toString());
                    }
                    break;
                case KeyEvent.VK_ENTER:
                    menu.setVisible(false);
                    break;
            }
        }
    }//GEN-LAST:event_txtSearchKeyPressed

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        if (evt.getKeyCode() != KeyEvent.VK_UP && evt.getKeyCode() != KeyEvent.VK_DOWN && evt.getKeyCode() != KeyEvent.VK_ENTER) {
            String search = txtSearch.getText().trim();
            if (!search.equals("")) {
                try {
                    mod.removeAllElements();
                    for (String v : YoutubeService.getInstance().searchSuggestion(search)) {
                        mod.addElement(v);
                    }
                    if (mod.size() > 0) {
                        menu.show(txtSearch, -1, txtSearch.getHeight() - 5);
                        menu.repaint();
                        menu.revalidate();
                    } else {
                        menu.setVisible(false);
                    }
                } catch (IOException | ParseException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                menu.setVisible(false);
            }
        }

    }//GEN-LAST:event_txtSearchKeyReleased

    private void txtSearchKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyTyped
        if (evt.getKeyChar() == 10) {
            cmdSearchActionPerformed(null);
        }
    }//GEN-LAST:event_txtSearchKeyTyped

    private void cmdSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdSearchActionPerformed
        if (!txtSearch.getText().trim().equals("")) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        List<Video> datas = YoutubeService.getInstance().search(txtSearch.getText().trim());
                        panel_video.setShow(1);
                        panel_video.addListVideo(datas);
                    } catch (IOException e) {
                        Master.getInstance().showError(e);
                    }
                }
            }).start();
        }
    }//GEN-LAST:event_cmdSearchActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        player.close();
    }//GEN-LAST:event_formWindowClosing

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Loading(null, true).setVisible(true);
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.raven.swing.RButton cmdSearch;
    private com.raven.equalizer.EqualizerPanel equalizer;
    private javax.swing.JPanel home;
    private javax.swing.JLayeredPane layout;
    private javax.swing.JList<String> list;
    private javax.swing.JPopupMenu menu;
    private javax.swing.JPanel panel;
    private com.raven.component.Panel_Video panel_video;
    private com.raven.video_player.VideoPlayer player;
    private com.raven.swing.RPanelShadow rPanelShadow1;
    private com.raven.swing.RTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
