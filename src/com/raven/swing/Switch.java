package com.raven.swing;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JComponent;

public class Switch extends JComponent implements MouseListener {

    private boolean On = true;
    private final int MARGIN = 6;
    private final int BORDER = 4;
    private Color backgroundColor;
    private Color buttonColor;
    private final Color DISABLED_COMPONENT_COLOR = new Color(131, 131, 131);

    public Switch() {
        super();
        Switch.this.setSize(new Dimension(60, 40));
        Switch.this.setPreferredSize(new Dimension(60, 40));
        Switch.this.setMinimumSize(new Dimension(60, 40));
        Switch.this.setVisible(true);
        Switch.this.setCursor(new Cursor(Cursor.HAND_CURSOR));
        Switch.this.addMouseListener(Switch.this);
        Switch.this.setBackgroundColor(new Color(75, 216, 101));
        Switch.this.setButtonColor(new Color(255, 255, 255));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if (isOpaque()) {
            g2.setColor(getBackground());
            g2.fill(new Rectangle2D.Double(0, 0, getWidth(), getHeight()));
        }

        if (isEnabled()) {
            g2.setColor(((On) ? getBackgroundColor() : new Color(216, 217, 219)));
            g2.fill(new RoundRectangle2D.Double((float) MARGIN, (float) MARGIN, (float) getWidth() - MARGIN * 2, (float) getHeight() - MARGIN * 2, getHeight() - MARGIN * 2, getHeight() - MARGIN * 2));
        } else {
            g2.setColor(DISABLED_COMPONENT_COLOR);
            g2.draw(new RoundRectangle2D.Double((float) MARGIN, (float) MARGIN, (float) getWidth() - MARGIN * 2, (float) getHeight() - MARGIN * 2, getHeight() - MARGIN * 2, getHeight() - MARGIN * 2));
        }

        g2.setColor((isEnabled()) ? getButtonColor() : DISABLED_COMPONENT_COLOR);
        if (On) {
            g2.fillOval(MARGIN + BORDER / 2, MARGIN + BORDER / 2, getHeight() - MARGIN * 2 - BORDER, getHeight() - MARGIN * 2 - BORDER);
        } else {
            g2.fillOval(getWidth() - getHeight() + MARGIN + BORDER / 2, MARGIN + BORDER / 2, getHeight() - MARGIN * 2 - BORDER, getHeight() - MARGIN * 2 - BORDER);
        }
    }

    public boolean isOn() {
        return On;
    }

    public void setOn(boolean On) {
        this.On = On;
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public Color getButtonColor() {
        return buttonColor;
    }

    public void setButtonColor(Color buttonColor) {
        this.buttonColor = buttonColor;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (isEnabled()) {
            On = !On;
            repaint();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        /*...*/ }

    @Override
    public void mouseReleased(MouseEvent e) {
        /*...*/ }

    @Override
    public void mouseEntered(MouseEvent e) {
        /*...*/ }

    @Override
    public void mouseExited(MouseEvent e) {
        /*...*/ }

}
