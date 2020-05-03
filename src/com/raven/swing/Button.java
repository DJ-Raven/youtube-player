package com.raven.swing;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;

public class Button extends JButton {

    private Color color;
    private Color colorClick;
    private Color colorOver;
    private Color curColor;
    private boolean mouseOver;
    private boolean mouseClick;
    private int fillBorder;
    private boolean red = false;

    public Button() {
        execute();
    }

    private void execute() {
        setPreferredSize(new Dimension(100, 31));
        setFont(new java.awt.Font("Khmer SBBIC Serif", 0, 15));
        setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 10));
        setContentAreaFilled(false);
        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        mouseClick = false;
        mouseOver = false;
        fillBorder = 15;
        setBackground(new Color(28, 201, 237));
        setColorClick(new Color(16, 178, 212));
        setColorOver(new java.awt.Color(80, 213, 241));
        setForeground(new Color(255, 255, 255));
        curColor = color;
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                formMousePressed(me);
            }

            @Override
            public void mouseReleased(MouseEvent me) {
                formMouseReleased(me);
            }

            @Override
            public void mouseEntered(MouseEvent me) {
                formMouseEnter(me);
            }

            @Override
            public void mouseExited(MouseEvent me) {
                formMouseExited(me);
            }
        });
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setColor(curColor);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), fillBorder, fillBorder);
        this.setBorder(getBorder());
        super.paintComponent(grphcs);
    }

    private void formMousePressed(MouseEvent me) {
        mouseClick = true;
        if (colorClick != null) {
            curColor = colorClick;
        }
    }

    private void formMouseReleased(MouseEvent me) {
        mouseClick = false;
        if (color != null) {
            if (mouseOver && colorOver != null) {
                curColor = colorOver;
            } else {
                curColor = color;
            }
        }
    }

    private void formMouseEnter(MouseEvent me) {
        mouseOver = true;
        if (colorOver != null && mouseClick == false) {
            curColor = colorOver;
        }
    }

    private void formMouseExited(MouseEvent me) {
        mouseOver = false;
        if (colorOver != null && mouseClick == false) {
            curColor = color;
        }
    }

    @Override
    public void setCursor(Cursor cursor) {
        super.setCursor(cursor);
    }

    @Override
    public void setBackground(Color color) {
        this.color = color;
        curColor = color;
        super.setBackground(color);
    }

    public Color getColorClick() {
        return colorClick;
    }

    public void setColorClick(Color colorClick) {
        this.colorClick = colorClick;
    }

    public Color getColorOver() {
        return colorOver;
    }

    public void setColorOver(Color colorOver) {
        this.colorOver = colorOver;
    }

    public int getFillBorder() {
        return fillBorder;
    }

    public void setFillBorder(int fillBorder) {
        this.fillBorder = fillBorder;
    }

    public boolean isRed() {
        return red;
    }

    public void setRed(boolean red) {
        this.red = red;
        if (red) {
            setBackground(new Color(207, 65, 65));
            setColorOver(new Color(241, 145, 145));
            setColorClick(new Color(201, 50, 50));
            color = getBackground();
        }
    }

    public void toUpdate(boolean red) {
        this.red = red;
        if (red) {
            setBackground(new Color(207, 124, 65));
            setColorOver(new Color(241, 185, 145));
            setColorClick(new Color(201, 112, 50));
            color = getBackground();
        } else {
            setBackground(new Color(28, 201, 237));
            color = getBackground();
            colorOver = new Color(145, 231, 241);
            colorClick = new Color(51, 186, 201);
        }
    }
}
