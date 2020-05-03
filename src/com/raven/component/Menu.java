package com.raven.component;

public class Menu extends javax.swing.JLayeredPane {

    public Menu() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rButton1 = new com.raven.swing.RButton();

        rButton1.setBackground(new java.awt.Color(255, 255, 255));
        rButton1.setBorder(null);
        rButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/youtube/icon/server.png"))); // NOI18N
        rButton1.setColorHover(new java.awt.Color(255, 255, 255));
        rButton1.setRippleColor(new java.awt.Color(236, 236, 236));

        setLayer(rButton1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(rButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 347, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(rButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.raven.swing.RButton rButton1;
    // End of variables declaration//GEN-END:variables
}
