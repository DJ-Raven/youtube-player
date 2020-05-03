package com.raven.swing;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class ScrollPane extends ScrollPane_Custom {

    private int borderType = 0;

    public ScrollPane() {
        super(true);
        execute();
    }

    private void execute() {
        setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(getBackground());
        g.fillRect(0, 0, getWidth() - 1, getHeight() - 1);
        switch (borderType) {
            case 1:
                g.setColor(java.awt.Color.RED);
                break;
            case 2:
                g.setColor(java.awt.Color.ORANGE);
                break;
            case 3:
                g.setColor(new Color(109, 109, 253));
                break;
            default:
                g.setColor(Color.GRAY);
        }
        g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
        super.paintComponent(g);
    }

    @Override
    public void setViewportView(Component com) {
        super.setViewportView(com);
        com.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent fe) {
                borderType = 3;
                repaint();
            }

            @Override
            public void focusLost(FocusEvent fe) {
                borderType = 0;
                repaint();
            }
        });
    }

    public void error() {
        borderType = 1;
        repaint();
    }

    public void warning() {
        borderType = 2;
        repaint();
    }
}
