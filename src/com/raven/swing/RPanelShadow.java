package com.raven.swing;

import com.raven.effect.DropShadowBorder;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class RPanelShadow extends JPanel {

    private static enum Position {
        TOP, TOP_LEFT, LEFT, BOTTOM_LEFT, BOTTOM, BOTTOM_RIGHT, RIGHT, TOP_RIGHT
    };
    DropShadowBorder border;
    private Color lineColor;
    private int lineWidth;
    private int shadowSize;
    private float shadowOpacity;
    private int cornerSize;
    private boolean showTopShadow;
    private boolean showLeftShadow;
    private boolean showBottomShadow;
    private boolean showRightShadow;

    public RPanelShadow() {
        setDefault();
        initBorder();
    }

    private void setDefault() {
        lineColor = UIManager.getColor("Control");
        lineWidth = 1;
        shadowSize = 5;
        shadowOpacity = 0.5f;
        cornerSize = 12;
        showTopShadow = false;
        showLeftShadow = false;
        showBottomShadow = true;
        showRightShadow = true;
    }

    private void initBorder() {
        border = new DropShadowBorder(lineColor, lineWidth, shadowSize, shadowOpacity, cornerSize, showTopShadow, showLeftShadow, showBottomShadow, showRightShadow);
        setBorder(border);
    }

    public int getShadowSize() {
        return shadowSize;
    }

    public void setShadowSize(int shadowSize) {
        this.shadowSize = shadowSize;
        initBorder();
    }

    public float getShadowOpacity() {
        return shadowOpacity;
    }

    public void setShadowOpacity(float shadowOpacity) {
        this.shadowOpacity = shadowOpacity;
        initBorder();
    }

    public boolean isShowTopShadow() {
        return showTopShadow;
    }

    public void setShowTopShadow(boolean showTopShadow) {
        this.showTopShadow = showTopShadow;
        initBorder();
    }

    public boolean isShowLeftShadow() {
        return showLeftShadow;
    }

    public void setShowLeftShadow(boolean showLeftShadow) {
        this.showLeftShadow = showLeftShadow;
        initBorder();
    }

    public boolean isShowBottomShadow() {
        return showBottomShadow;
    }

    public void setShowBottomShadow(boolean showBottomShadow) {
        this.showBottomShadow = showBottomShadow;
        initBorder();
    }

    public boolean isShowRightShadow() {
        return showRightShadow;
    }

    public void setShowRightShadow(boolean showRightShadow) {
        this.showRightShadow = showRightShadow;
        initBorder();
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (isOpaque()) {
            g.setColor(getBackground());
            g.fillRect(shadowSize, shadowSize, getWidth() - shadowSize * 2, getHeight() - shadowSize * 2);
        }
    }

}
