package com.raven.glasspane;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class GlassPane extends javax.swing.JComponent {

    public GlassPane() {
        initComponents();
    }
    private static GlassPane instance;
    private JFrame fram;

    public static GlassPane getInstance() {
        if (instance == null) {
            instance = new GlassPane();
        }
        return instance;
    }

    public void apply(JFrame fram) {
        this.fram = fram;
        fram.setGlassPane(this);
        fram.getGlassPane().setVisible(true);
    }

    public void show(JComponent com) {
        this.removeAll();
        this.add(com);
        fram.repaint();
        fram.revalidate();
    }

    public void close() {
        this.removeAll();
        fram.repaint();
        fram.revalidate();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setLayout(new java.awt.BorderLayout());
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
