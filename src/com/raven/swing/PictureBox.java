package com.raven.swing;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class PictureBox extends javax.swing.JDesktopPane {

    private Icon image;
    private boolean autoResize = true;
    private boolean fill = false;

    @Override
    protected void paintComponent(Graphics g) {
        if (isOpaque()) {
            g.setColor(getBackground());
            g.fillRect(0, 0, getWidth(), getHeight());
        }
        if (image != null) {
            int width = getWidth();
            int height = getHeight();
            int x = 0;
            int y = 0;
            if (autoResize) {
                int w = getWidth();
                int h = getHeight();
                int iw = image.getIconWidth();
                int ih = image.getIconHeight();
                double xScale = (double) w / iw;
                double yScale = (double) h / ih;
                double scale;
                if (fill) {
                    scale = Math.max(xScale, yScale);
                } else {
                    scale = Math.min(xScale, yScale);
                }
                width = (int) (scale * iw);
                height = (int) (scale * ih);
                x = (w - width) / 2;
                y = (h - height) / 2;
            }
            ImageIcon icon = (ImageIcon) image;
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.drawImage(icon.getImage(), x, y, width, height, this);
        }
    }

    public Icon getImage() {
        return image;
    }

    public void setImage(Icon image) {
        this.image = image;
        repaint();
    }

    public boolean isAutoResize() {
        return autoResize;
    }

    public void setAutoResize(boolean autoResize) {
        this.autoResize = autoResize;
    }

    public boolean isFill() {
        return fill;
    }

    public void setFill(boolean fill) {
        this.fill = fill;
    }
}
