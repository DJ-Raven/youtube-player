package com.raven.component;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import com.raven.models.Video;
import com.raven.swing.ActionComponent;
import com.raven.swing.DragScrollListener;
import com.raven.swing.EventVideoOption;
import com.raven.swing.EventVideoRemove;

public class List_Video extends javax.swing.JPanel {

    public void setOption(int option) {
        this.option = option;
    }

    private final DragScrollListener scroll;
    private int option = 1;
    private EventVideoOption eventSaveVideo;

    public List_Video() {
        initComponents();
        scroll = new DragScrollListener(panel);
        scroll.setAnimationTiming(35);
        panel.addMouseListener(scroll);
        panel.addMouseMotionListener(scroll);
    }

    public void setVideo(List<Video> datas) {
        panel.removeAll();
        for (Video v : datas) {
            Video_Item item = new Video_Item(v, option);
            item.setEventSaveVideo(eventSaveVideo);
            item.addMouseListener(scroll);
            item.addMouseMotionListener(scroll);
            item.initEvent(scroll);
            item.setEventRemoveVideo(new EventVideoRemove() {
                @Override
                public void execute(Video_Item item) {
                    panel.removeAt(item);
                }
            });
            panel.addComponent(item);
        }
    }

    public void addVideo(Video v) {
        Video_Item item = new Video_Item(v, option);
        item.setEventSaveVideo(eventSaveVideo);
        item.addMouseListener(scroll);
        item.addMouseMotionListener(scroll);
        item.initEvent(scroll);
        item.setEventRemoveVideo(new EventVideoRemove() {
            @Override
            public void execute(Video_Item item) {
                panel.removeAt(item);
            }
        });
        panel.addComponent(item);
    }

    public void addActionComponent(ActionComponent action) {
        panel.addActionComponent(action);
    }

    public ArrayList<Component> getItems() {
        return panel.getComponent();
    }

    public void initStatusPlaying(Video video) {
        for (Component com : panel.getComponent()) {
            Video_Item item = (Video_Item) com;
            if (video != null) {
                item.setStatusPlay(video == item.getVideo());
            } else {
                item.setStatusPlay(false);
            }
        }
    }

    public void removeVideo(Video_Item item) {
        panel.removeAt(item);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sp =  new com.raven.swing.ScrollPane();
        panel = new com.raven.swing.WrapComponent();

        sp.setBorder(null);
        sp.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        panel.setBackground(new java.awt.Color(255, 255, 255));
        panel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        sp.setViewportView(panel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sp, javax.swing.GroupLayout.DEFAULT_SIZE, 326, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sp, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.raven.swing.WrapComponent panel;
    private javax.swing.JScrollPane sp;
    // End of variables declaration//GEN-END:variables

    public void setEventSaveVideo(EventVideoOption eventSaveVideo) {
        this.eventSaveVideo = eventSaveVideo;
    }
}
