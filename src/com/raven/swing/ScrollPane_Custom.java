package com.raven.swing;

import java.awt.Adjustable;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class ScrollPane_Custom extends JScrollPane {

    public ScrollPane_Custom(boolean act) {
        this(0, act);
    }

    public ScrollPane_Custom(int size) {
        this(size, false);
    }

    public ScrollPane_Custom() {
        this(0, false);
    }

    public ScrollPane_Custom(int size, boolean act) {
        setBackground(new Color(255, 255, 255));
        setVerticalScrollBar(new com.raven.swing.ScrollBar(this, Adjustable.VERTICAL, size, act));
        setHorizontalScrollBar(new com.raven.swing.ScrollBar(this, Adjustable.HORIZONTAL, size, act));
    }

    public void fixCorner(Color color) {
        JPanel panel = new JPanel();
        panel.setBackground(color);
        JPanel panel1 = new JPanel();
        panel1.setBackground(color);
        this.setCorner(JScrollPane.UPPER_RIGHT_CORNER, panel);
        this.setCorner(JScrollPane.LOWER_RIGHT_CORNER, panel1);
    }
}
