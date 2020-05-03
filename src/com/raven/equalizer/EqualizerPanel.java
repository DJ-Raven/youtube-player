package com.raven.equalizer;

import uk.co.caprica.vlcj.factory.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.base.Equalizer;
import uk.co.caprica.vlcj.player.base.MediaPlayer;

public class EqualizerPanel extends javax.swing.JPanel {

    private MediaPlayerFactory mediaPlayerFactory;
    private MediaPlayer mediaPlayer;
    private Equalizer equalizer;

    public EqualizerPanel() {
        initComponents();
    }

    public void init(MediaPlayerFactory mediaPlayerFactory, MediaPlayer mediaPlayer, Equalizer equalizer) {
        this.mediaPlayerFactory = mediaPlayerFactory;
        this.mediaPlayer = mediaPlayer;
        this.equalizer = equalizer;
        com.raven.equalizer.ChangeEvent event = new com.raven.equalizer.ChangeEvent() {
            @Override
            public void change(int value, int index) {
                if (cmdEnable.isOn()) {
                    equalizer.setAmp(index, value / 100f);
                    mediaPlayer.audio().setEqualizer(equalizer);
                }
            }
        };
        slider1.addStateChangedEvent(new com.raven.equalizer.ChangeEvent() {
            @Override
            public void change(int values, int index) {
                if (cmdEnable.isOn()) {
                    equalizer.setPreamp(values / 100f);
                    mediaPlayer.audio().setEqualizer(equalizer);
                }
            }
        });
        slider2.addStateChangedEvent(event);
        slider3.addStateChangedEvent(event);
        slider4.addStateChangedEvent(event);
        slider5.addStateChangedEvent(event);
        slider6.addStateChangedEvent(event);
        slider7.addStateChangedEvent(event);
        slider8.addStateChangedEvent(event);
        slider9.addStateChangedEvent(event);
        slider10.addStateChangedEvent(event);
        slider11.addStateChangedEvent(event);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        slider1 = new com.raven.equalizer.SliderControl();
        slider2 = new com.raven.equalizer.SliderControl();
        slider3 = new com.raven.equalizer.SliderControl();
        slider4 = new com.raven.equalizer.SliderControl();
        slider5 = new com.raven.equalizer.SliderControl();
        slider6 = new com.raven.equalizer.SliderControl();
        slider7 = new com.raven.equalizer.SliderControl();
        slider8 = new com.raven.equalizer.SliderControl();
        slider9 = new com.raven.equalizer.SliderControl();
        slider10 = new com.raven.equalizer.SliderControl();
        slider11 = new com.raven.equalizer.SliderControl();
        comboPreset = new com.raven.swing.RComboBox();
        jLabel1 = new javax.swing.JLabel();
        cmdEnable = new com.raven.swing.Switch();
        jLabel2 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(242, 242, 242));

        slider1.setLabeName("Preamp");

        slider2.setLabeName("31 Hz");

        slider3.setIndex(1);
        slider3.setLabeName("63 Hz");

        slider4.setIndex(2);
        slider4.setLabeName("125 Hz");

        slider5.setIndex(3);
        slider5.setLabeName("250 Hz");

        slider6.setIndex(4);
        slider6.setLabeName("500 Hz");

        slider7.setIndex(5);
        slider7.setLabeName("1 kHz");

        slider8.setIndex(6);
        slider8.setLabeName("2 kHz");

        slider9.setIndex(7);
        slider9.setLabeName("4 kHz");

        slider10.setIndex(8);
        slider10.setLabeName("8 kHz");

        slider11.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        slider11.setIndex(9);
        slider11.setLabeName("16 kHz");

        comboPreset.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--- Default ---", "Flat", "Classical", "Club", "Dance", "Full bass", "Full bass and treble", "Full treble", "Headphones", "Large Hall", "Live", "Party", "Pop", "Reggae", "Rock", "Ska", "Soft", "Soft rock", "Techno" }));
        comboPreset.setFont(new java.awt.Font("Khmer SBBIC Serif", 0, 12)); // NOI18N
        comboPreset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboPresetActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Khmer SBBIC Serif", 0, 12)); // NOI18N
        jLabel1.setText("Preset :");

        cmdEnable.setBackgroundColor(new java.awt.Color(71, 173, 255));
        cmdEnable.setOn(false);
        cmdEnable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmdEnableMouseClicked(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Khmer SBBIC Serif", 0, 12)); // NOI18N
        jLabel2.setText("Enable :");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(slider1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(2, 2, 2)
                .addComponent(slider2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(2, 2, 2)
                .addComponent(slider3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(2, 2, 2)
                .addComponent(slider4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(2, 2, 2)
                .addComponent(slider5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(2, 2, 2)
                .addComponent(slider6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(2, 2, 2)
                .addComponent(slider7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(2, 2, 2)
                .addComponent(slider8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(2, 2, 2)
                .addComponent(slider9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(2, 2, 2)
                .addComponent(slider10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(2, 2, 2)
                .addComponent(slider11, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdEnable, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboPreset, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(slider2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(slider1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(slider3, javax.swing.GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE)
                    .addComponent(slider4, javax.swing.GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE)
                    .addComponent(slider5, javax.swing.GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE)
                    .addComponent(slider6, javax.swing.GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE)
                    .addComponent(slider7, javax.swing.GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE)
                    .addComponent(slider8, javax.swing.GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE)
                    .addComponent(slider9, javax.swing.GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE)
                    .addComponent(slider10, javax.swing.GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE)
                    .addComponent(slider11, javax.swing.GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(comboPreset, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cmdEnable, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void comboPresetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboPresetActionPerformed
        if (comboPreset.getSelectedIndex() != 0) {
            String presetName = comboPreset.getSelectedItem().toString();
            Equalizer presetEqualizer = mediaPlayerFactory.equalizer().newEqualizer(presetName);
            slider1.setValue((int) (presetEqualizer.preamp() * 100f));
            float[] amps = presetEqualizer.amps();
            slider2.setValue((int) (amps[0] * 100f));
            slider3.setValue((int) (amps[1] * 100f));
            slider4.setValue((int) (amps[2] * 100f));
            slider5.setValue((int) (amps[3] * 100f));
            slider6.setValue((int) (amps[4] * 100f));
            slider7.setValue((int) (amps[5] * 100f));
            slider8.setValue((int) (amps[6] * 100f));
            slider9.setValue((int) (amps[7] * 100f));
            slider10.setValue((int) (amps[8] * 100f));
            slider11.setValue((int) (amps[9] * 100f));
        } else {
            slider1.setValue(0);
            slider2.setValue(0);
            slider3.setValue(0);
            slider4.setValue(0);
            slider5.setValue(0);
            slider6.setValue(0);
            slider7.setValue(0);
            slider8.setValue(0);
            slider9.setValue(0);
            slider10.setValue(0);
            slider11.setValue(0);
        }
    }//GEN-LAST:event_comboPresetActionPerformed
    public void change() {
        equalizer.setPreamp(slider1.getSliderValue() / 100f);
        equalizer.setAmp(0, slider2.getSliderValue() / 100f);
        equalizer.setAmp(1, slider3.getSliderValue() / 100f);
        equalizer.setAmp(2, slider4.getSliderValue() / 100f);
        equalizer.setAmp(3, slider5.getSliderValue() / 100f);
        equalizer.setAmp(4, slider6.getSliderValue() / 100f);
        equalizer.setAmp(5, slider7.getSliderValue() / 100f);
        equalizer.setAmp(6, slider8.getSliderValue() / 100f);
        equalizer.setAmp(7, slider9.getSliderValue() / 100f);
        equalizer.setAmp(8, slider10.getSliderValue() / 100f);
        equalizer.setAmp(9, slider11.getSliderValue() / 100f);
        mediaPlayer.audio().setEqualizer(equalizer);
    }
    private void cmdEnableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdEnableMouseClicked
        if (cmdEnable.isOn()) {
            change();
        } else {
            mediaPlayer.audio().setEqualizer(null);
        }
    }//GEN-LAST:event_cmdEnableMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.raven.swing.Switch cmdEnable;
    private com.raven.swing.RComboBox comboPreset;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private com.raven.equalizer.SliderControl slider1;
    private com.raven.equalizer.SliderControl slider10;
    private com.raven.equalizer.SliderControl slider11;
    private com.raven.equalizer.SliderControl slider2;
    private com.raven.equalizer.SliderControl slider3;
    private com.raven.equalizer.SliderControl slider4;
    private com.raven.equalizer.SliderControl slider5;
    private com.raven.equalizer.SliderControl slider6;
    private com.raven.equalizer.SliderControl slider7;
    private com.raven.equalizer.SliderControl slider8;
    private com.raven.equalizer.SliderControl slider9;
    // End of variables declaration//GEN-END:variables
}
