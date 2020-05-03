package com.raven.swing;

import com.raven.effect.Effect;
import com.raven.effect.MaterialColor;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.FocusEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;

public class RButton extends JButton {

    private Effect ripple;
    private Color rippleColor = Color.WHITE;
    private Cursor cursor = super.getCursor();
    private Color colorHover = MaterialColor.BLUE_400;
    private Color colorTextHover = new Color(255, 255, 255);
    private Color colorText = new Color(255, 255, 255);
    private boolean isHover = false;
    private int borderRadius = 0;
    private boolean paintLine = false;

    public RButton() {
        execute();
    }

    private void execute() {
        setPreferredSize(new Dimension(200, 40));
        setBackground(MaterialColor.BLUE_500);
        setForeground(this.colorText);
        setCursor(new Cursor(12));
        setContentAreaFilled(false);
        this.ripple = Effect.applyTo(this);
        ripple.setFcolor(rippleColor);
    }

    @Override
    public void setForeground(Color color) {
        super.setForeground(color);
        this.colorText = color;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        if (isEnabled()) {
            g2.setColor(getBackground());
            g2.fill(new RoundRectangle2D.Float(0.0F, 0.0F, getWidth(), getHeight(), borderRadius, borderRadius));
            g2.setColor(new Color(this.rippleColor.getRed() / 255.0F, this.rippleColor.getBlue() / 255.0F, this.rippleColor.getBlue() / 255.0F, 0.12F));
        } else {
            Color bg = getBackground();
            g2.setColor(new Color(bg.getRed() / 255.0F, bg.getGreen() / 255.0F, bg.getBlue() / 255.0F, 0.6F));
            g2.fill(new RoundRectangle2D.Float(0.0F, 0.0F, getWidth(), getHeight(), borderRadius, borderRadius));
        }
        if (getModel().isSelected()) {
            g2.setColor(getColorHover());
        } else if (getModel().isRollover()) {
            g2.setColor(getColorHover());
            this.isHover = true;
        } else {
            g2.setColor(getBackground());
            this.isHover = false;
        }
        if (this.isHover) {
            super.setForeground(getColorTextHover());
        } else {
            super.setForeground(getColorText());
        }
        g.fillRoundRect(0, 0, getSize().width, getSize().height, borderRadius, borderRadius);
        if (isEnabled()) {
            //  g2.setColor(rippleColor);
            g2.setClip(new RoundRectangle2D.Float(0.0F, 0.0F, getWidth(), getHeight(), borderRadius, borderRadius));
            g2.setColor(this.rippleColor);
            this.ripple.paint(g2);
        }
        super.paintComponent(g2);
        if (paintLine) {
            g.setColor(new Color(71, 173, 255));
            g.fillRoundRect(0, getSize().height - 3, getSize().width, getSize().height, borderRadius, borderRadius);
        }
    }

    public void setPaintLine(boolean paintLine) {
        this.paintLine = paintLine;
        repaint();
    }

    public Color getRippleColor() {
        return this.rippleColor;
    }

    public void setRippleColor(Color rippleColor) {
        this.rippleColor = rippleColor;
        ripple.setFcolor(rippleColor);
    }

    @Override
    public void setEnabled(boolean b) {
        super.setEnabled(b);
        super.setCursor(b ? this.cursor : Cursor.getPredefinedCursor(0));
    }

    @Override
    public void setCursor(Cursor cursor) {
        super.setCursor(cursor);
        this.cursor = cursor;
    }

    @Override
    protected void processFocusEvent(FocusEvent focusEvent) {
        super.processFocusEvent(focusEvent);
    }

    @Override
    protected void processMouseEvent(MouseEvent mouseEvent) {
        super.processMouseEvent(mouseEvent);
    }

    public Color getColorHover() {
        return this.colorHover;
    }

    public void setColorHover(Color colorHover) {
        this.colorHover = colorHover;
    }

    public Color getColorTextHover() {
        return this.colorTextHover;
    }

    public void setColorTextHover(Color colorTextHover) {
        this.colorTextHover = colorTextHover;
    }

    public Color getColorText() {
        return this.colorText;
    }

    public void setColorText(Color colorText) {
        this.colorText = colorText;
    }

    public int getBorderRadius() {
        return borderRadius;
    }

    public void setBorderRadius(int borderRadius) {
        this.borderRadius = borderRadius;
    }
}
