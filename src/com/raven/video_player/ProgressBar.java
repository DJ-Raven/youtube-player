package com.raven.video_player;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JComponent;
import javax.swing.JProgressBar;
import javax.swing.plaf.basic.BasicProgressBarUI;

public class ProgressBar extends JProgressBar {

    private boolean dragged = false;

    public ProgressBar() {
        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        setPreferredSize(new Dimension(100, 5));
        setBackground(new Color(180, 180, 180));
        setForeground(new Color(255, 90, 90));
        setUI(new MyProgressUI());
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                dragged = true;
                int mouseX = me.getX();
                int progressBarVal = (int) Math.round(((double) mouseX / (double) getWidth()) * getMaximum());
                setValue(progressBarVal);
            }

            @Override
            public void mouseReleased(MouseEvent me) {
                dragged = false;
            }

        });
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent me) {
                dragged = true;
                int mouseX = me.getX();
                int progressBarVal = (int) Math.round(((double) mouseX / (double) getWidth()) * getMaximum());
                setValue(progressBarVal);
            }
        });
    }

    public boolean isDragged() {
        return dragged;
    }

    class MyProgressUI extends BasicProgressBarUI {

        private Rectangle r = new Rectangle();

        @Override
        protected void paintIndeterminate(Graphics g, JComponent c) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            r = getBox(r);
            g.setColor(progressBar.getForeground());
            g.fillOval(r.x, r.y, r.width, r.height);
        }
    }
}
