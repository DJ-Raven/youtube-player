package com.raven.swing;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class WrapComponent extends JPanel {

    private ArrayList<Component> components;
    private ArrayList<ActionComponent> event;
    private Cursor cursor;
    private int horizontalGap = 5;
    private int verticalGap = 5;
    private final WrapLayout layout;

    public WrapComponent() {
        layout = new WrapLayout();
        execute();
    }

    private void execute() {
        components = new ArrayList();
        event = new ArrayList();
        layout.setHgap(horizontalGap);
        layout.setVgap(verticalGap);
        layout.setAlignment(WrapLayout.LEADING);
        setLayout(layout);
    }

    public void addComponent(Component com) {
        components.add(com);
        add(com);
        prepareComponent(com);
        repaint();
        revalidate();
    }

    public void insertComponent(Component com, int index) {
        components.add(index, com);
        add(com, index);
        prepareComponent(com);
        repaint();
        revalidate();
    }

    public void addComponentAt(Component com, int index) {
        components.add(index, com);
        prepareComponent(com);
    }

    private void prepareComponent(Component com) {
        com.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if (SwingUtilities.isLeftMouseButton(evt)) {
                    for (ActionComponent e : event) {
                        e.execute(com, evt);
                    }
                }
            }
        });
        if (cursor != null) {
            com.setCursor(cursor);
        }
    }

    public void runEven(int index) {
        for (ActionComponent e : event) {
            e.execute((Component) components.get(index), null);
        }
    }

    public int count() {
        return components.size();
    }

    public void removeAt(int index) {
        components.remove(index);
        remove(index);
        repaint();
        revalidate();
    }

    public void removeAt(Component com) {
        components.remove(com);
        remove(com);
        repaint();
        revalidate();
    }

    @Override
    public void removeAll() {
        components.clear();
        super.removeAll();
        repaint();
        revalidate();
    }

    public void removeEvent() {
        event.clear();
    }

    @Override
    public void setCursor(Cursor cursor) {
        super.setCursor(cursor);
        this.cursor = cursor;
    }

    public ArrayList<Component> getComponent() {
        return components;
    }

    public void setComponent(ArrayList<Component> components) {
        this.components = components;
    }

    public void addActionComponent(ActionComponent action) {
        event.add(action);
    }

    public int getHorizontalGap() {
        return horizontalGap;
    }

    public int getVerticalGap() {
        return verticalGap;
    }

    public void setHorizontalGap(int horizontalGap) {
        this.horizontalGap = horizontalGap;
        layout.setHgap(horizontalGap);
    }

    public void setVerticalGap(int verticalGap) {
        this.verticalGap = verticalGap;
        layout.setVgap(verticalGap);
    }
}
