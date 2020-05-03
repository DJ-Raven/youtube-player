package com.raven.swing;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicSliderUI;

public class Slider extends JSlider {

    public Slider() {
        this.setOrientation(SwingConstants.VERTICAL);
        this.setUI(new BasicSliderUI(this) {
            @Override
            public void paintThumb(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                if (isFocusOwner()) {
                    g2.setColor(new Color(20, 149, 213));
                } else {
                    g2.setColor(new Color(95, 188, 235));
                }
                g2.fillRoundRect(thumbRect.x, thumbRect.y, 20, 10, 5, 5);
            }

            @Override
            public void paintTrack(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(153, 153, 153));
                g2.fillRoundRect(Slider.this.getWidth() / 2 - 2, 2, 4, getHeight() - 5, 1, 1);
            }

            @Override
            public void paintFocus(Graphics grphcs) {
            }
        });
        addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent evt) {
                int notches = evt.getWheelRotation();
                if (notches < 0) {
                    Slider.this.setValue(Slider.this.getValue() + 1);
                } else {
                    Slider.this.setValue(Slider.this.getValue() - 1);
                }
            }
        });
    }

}
