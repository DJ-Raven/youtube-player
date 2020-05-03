package com.raven.equalizer;

import javax.swing.event.ChangeListener;
import uk.co.caprica.vlcj.player.base.LibVlcConst;

public class SliderControl extends javax.swing.JLayeredPane {

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    private int index;
    private final float factor = 100f;
    private final String dbFormat = "%.2fdB";
    private ChangeEvent event;

    public SliderControl() {
        initComponents();
        init();
    }

    private void init() {
        int min = (int) LibVlcConst.MIN_GAIN;
        int max = (int) LibVlcConst.MAX_GAIN;
        int value = 0;
        int modelMin = (int) (min * factor);
        int modelMax = (int) (max * factor);
        int modelValue = (int) (value * factor);
        modelValue = Math.min(modelValue, modelMax);
        modelValue = Math.max(modelValue, modelMin);
        slider.getModel().setMinimum(modelMin);
        slider.getModel().setMaximum(modelMax);
        slider.getModel().setValue(modelValue);
        slider.getModel().addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(javax.swing.event.ChangeEvent ce) {
                updateValue();
                event.change(slider.getValue(), index);
            }
        });

        updateValue();
    }

    public void setLabeName(String name) {
        lbName.setText(name);
    }

    public void setLabelValue(String val) {
        lbValues.setText(val);
    }

    public int getSliderValue() {
        return slider.getValue();
    }

    public void addStateChangedEvent(ChangeEvent event) {
        this.event = event;
    }

    public void setValue(int val) {
        slider.setValue(val);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbValues = new javax.swing.JLabel();
        lbName = new javax.swing.JLabel();
        slider = new com.raven.swing.Slider();

        setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        lbValues.setFont(new java.awt.Font("Khmer SBBIC Serif", 0, 12)); // NOI18N
        lbValues.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbValues.setText("0.00dB");

        lbName.setFont(new java.awt.Font("Khmer SBBIC Serif", 0, 12)); // NOI18N
        lbName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbName.setText("Name");

        slider.setOpaque(false);

        setLayer(lbValues, javax.swing.JLayeredPane.DEFAULT_LAYER);
        setLayer(lbName, javax.swing.JLayeredPane.DEFAULT_LAYER);
        setLayer(slider, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbValues, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lbName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(slider, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(21, 21, 21))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(lbName, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(slider, javax.swing.GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(lbValues, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void updateValue() {
        int value = slider.getValue();
        lbValues.setText(String.format(dbFormat, value / factor));
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lbName;
    private javax.swing.JLabel lbValues;
    private com.raven.swing.Slider slider;
    // End of variables declaration//GEN-END:variables
}
