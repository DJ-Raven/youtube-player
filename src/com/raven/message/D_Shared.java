package com.raven.message;

import com.raven.glasspane.GlassPane;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.net.URISyntaxException;
import com.raven.master.Master;
import com.raven.program_data.PropertiesData;
import com.raven.program_data.PropertiesManagement;
import com.raven.socket.client.Client;
import com.raven.socket.data.Event;
import com.raven.socket.data.EventConnecting;
import com.raven.socket.data.EventNewVideo;
import com.raven.socket.server.Server;
import java.io.IOException;

public class D_Shared extends javax.swing.JPanel {

    public EventNewVideo getEvent_new_video() {
        return event_new_video;
    }

    public void setEvent_new_video(EventNewVideo event_new_video) {
        this.event_new_video = event_new_video;
    }

    public void setEventNewClient(Event eventNewClient) {
        this.eventNewClient = eventNewClient;
    }

    public void setEventClientConnect(ActionListener eventClientConnect) {
        this.eventClientConnect = eventClientConnect;
    }

    public void setEventCreateServer(ActionListener eventCreateServer) {
        this.eventCreateServer = eventCreateServer;
    }

    private ActionListener eventCreateServer;
    private ActionListener eventClientConnect;
    private EventConnecting eventOnlineOffline;
    private Event eventNewClient;
    private Event event_error_message;
    private Event event_remove_video;
    private EventNewVideo event_new_video;

    public D_Shared() {
        initComponents();
        setBackground(new Color(255, 255, 255, 100));
        option.setVisible(true);
        anotherServer.setVisible(false);
        ownServer.setVisible(false);
        error.setVisible(false);
        loading.setVisible(false);
        cmdOwnServer.grabFocus();
        PropertiesData data = PropertiesManagement.getInstance().getData();
        txtServerName.setText(data.getServerName());
        txtClientName.setText(data.getClientName());
        txtIP.setText(data.getIpServer());
    }

    public void open() {
        cmdOwnServer.grabFocus();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel = new com.raven.swing.RPanelShadow();
        body = new javax.swing.JLayeredPane();
        option = new javax.swing.JLayeredPane();
        cmdOwnServer = new com.raven.swing.RButton();
        cmdAnotherServer = new com.raven.swing.RButton();
        anotherServer = new javax.swing.JLayeredPane();
        cmdConnect = new com.raven.swing.RButton();
        cmdBack = new com.raven.swing.RButton();
        jLabel1 = new javax.swing.JLabel();
        txtIP = new com.raven.swing.RTextField();
        ownServer = new javax.swing.JLayeredPane();
        cmdServer = new com.raven.swing.RButton();
        cmdBack1 = new com.raven.swing.RButton();
        jLabel2 = new javax.swing.JLabel();
        txtServerName = new com.raven.swing.RTextField();
        error = new javax.swing.JLayeredPane();
        cmdOK1 = new com.raven.swing.RButton();
        lbMessageError = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        loading = new javax.swing.JLayeredPane();
        cmdOK2 = new com.raven.swing.RButton();
        lbMessageLoading = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        enterClientName = new javax.swing.JLayeredPane();
        cmdOKAnotherServer = new com.raven.swing.RButton();
        jLabel3 = new javax.swing.JLabel();
        txtClientName = new com.raven.swing.RTextField();

        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
        });

        panel.setBackground(new java.awt.Color(255, 255, 255));
        panel.setShadowOpacity(0.1F);
        panel.setShowLeftShadow(true);
        panel.setShowTopShadow(true);
        panel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                panelMousePressed(evt);
            }
        });

        body.setLayout(new java.awt.CardLayout());

        cmdOwnServer.setBackground(new java.awt.Color(232, 232, 232));
        cmdOwnServer.setForeground(new java.awt.Color(51, 51, 51));
        cmdOwnServer.setText("Create Own Server");
        cmdOwnServer.setColorHover(new java.awt.Color(237, 237, 237));
        cmdOwnServer.setColorTextHover(new java.awt.Color(51, 51, 51));
        cmdOwnServer.setFont(new java.awt.Font("Khmer SBBIC Serif", 0, 14)); // NOI18N
        cmdOwnServer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdOwnServerActionPerformed(evt);
            }
        });

        cmdAnotherServer.setBackground(new java.awt.Color(232, 232, 232));
        cmdAnotherServer.setForeground(new java.awt.Color(51, 51, 51));
        cmdAnotherServer.setText("Connect to another Server");
        cmdAnotherServer.setColorHover(new java.awt.Color(237, 237, 237));
        cmdAnotherServer.setColorTextHover(new java.awt.Color(51, 51, 51));
        cmdAnotherServer.setFont(new java.awt.Font("Khmer SBBIC Serif", 0, 14)); // NOI18N
        cmdAnotherServer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdAnotherServerActionPerformed(evt);
            }
        });

        option.setLayer(cmdOwnServer, javax.swing.JLayeredPane.DEFAULT_LAYER);
        option.setLayer(cmdAnotherServer, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout optionLayout = new javax.swing.GroupLayout(option);
        option.setLayout(optionLayout);
        optionLayout.setHorizontalGroup(
            optionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, optionLayout.createSequentialGroup()
                .addContainerGap(76, Short.MAX_VALUE)
                .addGroup(optionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cmdAnotherServer, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
                    .addComponent(cmdOwnServer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(64, 64, 64))
        );
        optionLayout.setVerticalGroup(
            optionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(optionLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(cmdOwnServer, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cmdAnotherServer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        body.add(option, "card2");

        cmdConnect.setBackground(new java.awt.Color(232, 232, 232));
        cmdConnect.setForeground(new java.awt.Color(51, 51, 51));
        cmdConnect.setText("Connect");
        cmdConnect.setColorHover(new java.awt.Color(237, 237, 237));
        cmdConnect.setColorTextHover(new java.awt.Color(51, 51, 51));
        cmdConnect.setFont(new java.awt.Font("Khmer SBBIC Serif", 0, 14)); // NOI18N
        cmdConnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdConnectActionPerformed(evt);
            }
        });

        cmdBack.setBackground(new java.awt.Color(232, 232, 232));
        cmdBack.setForeground(new java.awt.Color(51, 51, 51));
        cmdBack.setText("Back");
        cmdBack.setColorHover(new java.awt.Color(237, 237, 237));
        cmdBack.setColorTextHover(new java.awt.Color(51, 51, 51));
        cmdBack.setFont(new java.awt.Font("Khmer SBBIC Serif", 0, 14)); // NOI18N
        cmdBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdBackActionPerformed(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(126, 166, 246));
        jLabel1.setFont(new java.awt.Font("Khmer SBBIC Serif", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(55, 139, 243));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Enter Server IP Address");

        txtIP.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtIP.setText("localhost");
        txtIP.setLength(20);
        txtIP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtIPKeyTyped(evt);
            }
        });

        anotherServer.setLayer(cmdConnect, javax.swing.JLayeredPane.DEFAULT_LAYER);
        anotherServer.setLayer(cmdBack, javax.swing.JLayeredPane.DEFAULT_LAYER);
        anotherServer.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        anotherServer.setLayer(txtIP, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout anotherServerLayout = new javax.swing.GroupLayout(anotherServer);
        anotherServer.setLayout(anotherServerLayout);
        anotherServerLayout.setHorizontalGroup(
            anotherServerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, anotherServerLayout.createSequentialGroup()
                .addContainerGap(76, Short.MAX_VALUE)
                .addGroup(anotherServerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtIP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(anotherServerLayout.createSequentialGroup()
                        .addComponent(cmdConnect, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(cmdBack, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(64, 64, 64))
        );
        anotherServerLayout.setVerticalGroup(
            anotherServerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(anotherServerLayout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtIP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(anotherServerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmdConnect, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdBack, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        body.add(anotherServer, "card2");

        cmdServer.setBackground(new java.awt.Color(232, 232, 232));
        cmdServer.setForeground(new java.awt.Color(51, 51, 51));
        cmdServer.setText("Ok");
        cmdServer.setColorHover(new java.awt.Color(237, 237, 237));
        cmdServer.setColorTextHover(new java.awt.Color(51, 51, 51));
        cmdServer.setFont(new java.awt.Font("Khmer SBBIC Serif", 0, 14)); // NOI18N
        cmdServer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdServerActionPerformed(evt);
            }
        });

        cmdBack1.setBackground(new java.awt.Color(232, 232, 232));
        cmdBack1.setForeground(new java.awt.Color(51, 51, 51));
        cmdBack1.setText("Back");
        cmdBack1.setColorHover(new java.awt.Color(237, 237, 237));
        cmdBack1.setColorTextHover(new java.awt.Color(51, 51, 51));
        cmdBack1.setFont(new java.awt.Font("Khmer SBBIC Serif", 0, 14)); // NOI18N
        cmdBack1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdBack1ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Khmer SBBIC Serif", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(55, 139, 243));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Enter Your Server Name");

        txtServerName.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtServerName.setLength(20);
        txtServerName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtServerNameKeyTyped(evt);
            }
        });

        ownServer.setLayer(cmdServer, javax.swing.JLayeredPane.DEFAULT_LAYER);
        ownServer.setLayer(cmdBack1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        ownServer.setLayer(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        ownServer.setLayer(txtServerName, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout ownServerLayout = new javax.swing.GroupLayout(ownServer);
        ownServer.setLayout(ownServerLayout);
        ownServerLayout.setHorizontalGroup(
            ownServerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ownServerLayout.createSequentialGroup()
                .addContainerGap(76, Short.MAX_VALUE)
                .addGroup(ownServerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtServerName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(ownServerLayout.createSequentialGroup()
                        .addComponent(cmdServer, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(cmdBack1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(64, 64, 64))
        );
        ownServerLayout.setVerticalGroup(
            ownServerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ownServerLayout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtServerName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(ownServerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmdServer, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdBack1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        body.add(ownServer, "card2");

        cmdOK1.setBackground(new java.awt.Color(232, 232, 232));
        cmdOK1.setForeground(new java.awt.Color(51, 51, 51));
        cmdOK1.setText("Back");
        cmdOK1.setColorHover(new java.awt.Color(237, 237, 237));
        cmdOK1.setColorTextHover(new java.awt.Color(51, 51, 51));
        cmdOK1.setFont(new java.awt.Font("Khmer SBBIC Serif", 0, 14)); // NOI18N
        cmdOK1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdOK1ActionPerformed(evt);
            }
        });

        lbMessageError.setBackground(new java.awt.Color(126, 166, 246));
        lbMessageError.setFont(new java.awt.Font("Khmer SBBIC Serif", 0, 18)); // NOI18N
        lbMessageError.setForeground(new java.awt.Color(243, 54, 54));
        lbMessageError.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbMessageError.setText("Can't create server");

        jLabel4.setBackground(new java.awt.Color(126, 166, 246));
        jLabel4.setFont(new java.awt.Font("Khmer SBBIC Serif", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(243, 54, 54));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/error.png"))); // NOI18N
        jLabel4.setBorder(null);

        error.setLayer(cmdOK1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        error.setLayer(lbMessageError, javax.swing.JLayeredPane.DEFAULT_LAYER);
        error.setLayer(jLabel4, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout errorLayout = new javax.swing.GroupLayout(error);
        error.setLayout(errorLayout);
        errorLayout.setHorizontalGroup(
            errorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbMessageError, javax.swing.GroupLayout.DEFAULT_SIZE, 358, Short.MAX_VALUE)
            .addGroup(errorLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(errorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
                    .addComponent(cmdOK1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        errorLayout.setVerticalGroup(
            errorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(errorLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbMessageError, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cmdOK1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        body.add(error, "card2");

        cmdOK2.setBackground(new java.awt.Color(232, 232, 232));
        cmdOK2.setForeground(new java.awt.Color(51, 51, 51));
        cmdOK2.setText("Back");
        cmdOK2.setColorHover(new java.awt.Color(237, 237, 237));
        cmdOK2.setColorTextHover(new java.awt.Color(51, 51, 51));
        cmdOK2.setFont(new java.awt.Font("Khmer SBBIC Serif", 0, 14)); // NOI18N
        cmdOK2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdOK2ActionPerformed(evt);
            }
        });

        lbMessageLoading.setFont(new java.awt.Font("Khmer SBBIC Serif", 0, 18)); // NOI18N
        lbMessageLoading.setForeground(new java.awt.Color(55, 139, 243));
        lbMessageLoading.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbMessageLoading.setText("Connecting ...");

        jLabel5.setBackground(new java.awt.Color(126, 166, 246));
        jLabel5.setFont(new java.awt.Font("Khmer SBBIC Serif", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(243, 54, 54));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/loading.gif"))); // NOI18N
        jLabel5.setBorder(null);

        loading.setLayer(cmdOK2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        loading.setLayer(lbMessageLoading, javax.swing.JLayeredPane.DEFAULT_LAYER);
        loading.setLayer(jLabel5, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout loadingLayout = new javax.swing.GroupLayout(loading);
        loading.setLayout(loadingLayout);
        loadingLayout.setHorizontalGroup(
            loadingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbMessageLoading, javax.swing.GroupLayout.DEFAULT_SIZE, 358, Short.MAX_VALUE)
            .addGroup(loadingLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(loadingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cmdOK2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        loadingLayout.setVerticalGroup(
            loadingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loadingLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbMessageLoading, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cmdOK2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        body.add(loading, "card2");

        cmdOKAnotherServer.setBackground(new java.awt.Color(232, 232, 232));
        cmdOKAnotherServer.setForeground(new java.awt.Color(51, 51, 51));
        cmdOKAnotherServer.setText("Ok");
        cmdOKAnotherServer.setColorHover(new java.awt.Color(237, 237, 237));
        cmdOKAnotherServer.setColorTextHover(new java.awt.Color(51, 51, 51));
        cmdOKAnotherServer.setFont(new java.awt.Font("Khmer SBBIC Serif", 0, 14)); // NOI18N
        cmdOKAnotherServer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdOKAnotherServerActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Khmer SBBIC Serif", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(55, 139, 243));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Enter Your Name");

        txtClientName.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtClientName.setLength(20);
        txtClientName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtClientNameKeyTyped(evt);
            }
        });

        enterClientName.setLayer(cmdOKAnotherServer, javax.swing.JLayeredPane.DEFAULT_LAYER);
        enterClientName.setLayer(jLabel3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        enterClientName.setLayer(txtClientName, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout enterClientNameLayout = new javax.swing.GroupLayout(enterClientName);
        enterClientName.setLayout(enterClientNameLayout);
        enterClientNameLayout.setHorizontalGroup(
            enterClientNameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, enterClientNameLayout.createSequentialGroup()
                .addContainerGap(76, Short.MAX_VALUE)
                .addGroup(enterClientNameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, enterClientNameLayout.createSequentialGroup()
                        .addComponent(txtClientName, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(64, 64, 64))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, enterClientNameLayout.createSequentialGroup()
                        .addComponent(cmdOKAnotherServer, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(121, 121, 121))))
        );
        enterClientNameLayout.setVerticalGroup(
            enterClientNameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(enterClientNameLayout.createSequentialGroup()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtClientName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cmdOKAnotherServer, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        body.add(enterClientName, "card2");

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(body)
                .addContainerGap())
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(body)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(94, Short.MAX_VALUE)
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(94, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(50, Short.MAX_VALUE)
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(50, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void panelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelMousePressed

    }//GEN-LAST:event_panelMousePressed

    private void cmdOwnServerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdOwnServerActionPerformed
        option.setVisible(false);
        ownServer.setVisible(true);
        anotherServer.setVisible(false);
        txtServerName.grabFocus();
    }//GEN-LAST:event_cmdOwnServerActionPerformed

    private void cmdAnotherServerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdAnotherServerActionPerformed
        option.setVisible(false);
        ownServer.setVisible(false);
        anotherServer.setVisible(true);
        txtIP.grabFocus();
    }//GEN-LAST:event_cmdAnotherServerActionPerformed

    private void cmdConnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdConnectActionPerformed
        String ip = txtIP.getText().trim();
        if (!ip.equals("")) {
            try {
                PropertiesManagement.getInstance().store("ip_server", ip);
                PropertiesManagement.getInstance().getData().setIpServer(ip);
            } catch (IOException e) {
                Master.getInstance().showError(e);
            }
            try {
                EventConnecting event = new EventConnecting() {
                    @Override
                    public void execute(EventConnecting.ConnectType type) {
                        if (type == EventConnecting.ConnectType.SUCCESS) {
                            Client.getInstance().setEvent(new Event() {
                                @Override
                                public void execute(Object... data) {
                                    if (data[0].toString().equals("")) {
                                        txtClientName.grabFocus();
                                    } else {
                                        eventClientConnect.actionPerformed(evt);
                                        eventNewClient.execute(data);
                                    }
                                }
                            }, eventNewClient, event_error_message, event_remove_video, eventOnlineOffline, event_new_video);
                            loading.setVisible(false);
                            enterClientName.setVisible(true);
                            txtClientName.grabFocus();
                        } else {
                            lbMessageError.setText("Can't connect to server");
                            error.setVisible(true);
                            loading.setVisible(false);
                        }
                    }
                };
                Client.getInstance().connect(ip, event);
                anotherServer.setVisible(false);
                loading.setVisible(true);
            } catch (URISyntaxException e) {
                lbMessageError.setText("Can't connect to server");
                ownServer.setVisible(false);
                error.setVisible(true);
                Master.getInstance().showError(e);
            }
        } else {
            txtIP.grabFocus();
        }
    }//GEN-LAST:event_cmdConnectActionPerformed

    private void cmdBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdBackActionPerformed
        option.setVisible(true);
        anotherServer.setVisible(false);
        cmdOwnServer.grabFocus();
    }//GEN-LAST:event_cmdBackActionPerformed

    private void cmdServerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdServerActionPerformed
        String serverName = txtServerName.getText().trim();
        if (!serverName.equals("")) {
            try {
                PropertiesManagement.getInstance().store("server_name", serverName);
                PropertiesManagement.getInstance().getData().setServerName(serverName);
            } catch (IOException e) {
                Master.getInstance().showError(e);
            }
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Server.getInstance().createServer(txtServerName.getText());
                        eventCreateServer.actionPerformed(evt);
                    } catch (Exception e) {
                        lbMessageError.setText("Can't create server");
                        loading.setVisible(false);
                        ownServer.setVisible(false);
                        error.setVisible(true);
                        Master.getInstance().showError(e);
                    }
                }
            }).start();
            lbMessageLoading.setText("Creating Server ...");
            loading.setVisible(true);
            ownServer.setVisible(false);
        } else {
            txtServerName.grabFocus();
        }
    }//GEN-LAST:event_cmdServerActionPerformed

    private void cmdBack1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdBack1ActionPerformed
        ownServer.setVisible(false);
        option.setVisible(true);
        cmdOwnServer.grabFocus();
    }//GEN-LAST:event_cmdBack1ActionPerformed

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        GlassPane.getInstance().close();
    }//GEN-LAST:event_formMousePressed

    private void cmdOK1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdOK1ActionPerformed
        if (lbMessageError.getText().equals("Can't create server")) {
            error.setVisible(false);
            ownServer.setVisible(true);
        } else if (lbMessageError.getText().equals("Can't connect to server")) {
            error.setVisible(false);
            anotherServer.setVisible(true);
        }
    }//GEN-LAST:event_cmdOK1ActionPerformed

    private void cmdOK2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdOK2ActionPerformed
        if (lbMessageLoading.getText().equals("Connecting ...")) {
            Client.getInstance().closeClient();
            loading.setVisible(false);
            anotherServer.setVisible(true);
        }
    }//GEN-LAST:event_cmdOK2ActionPerformed

    private void cmdOKAnotherServerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdOKAnotherServerActionPerformed
        String clientName = txtClientName.getText().trim();
        if (!clientName.equals("")) {
            try {
                PropertiesManagement.getInstance().store("client_name", clientName);
                PropertiesManagement.getInstance().getData().setServerName(clientName);
            } catch (IOException e) {
                Master.getInstance().showError(e);
            }
            Client.getInstance().sendMessage("new_client", clientName);
        } else {
            txtClientName.grabFocus();
        }
    }//GEN-LAST:event_cmdOKAnotherServerActionPerformed

    private void txtClientNameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtClientNameKeyTyped
        if (evt.getKeyChar() == 10) {
            cmdOKAnotherServerActionPerformed(null);
        }
    }//GEN-LAST:event_txtClientNameKeyTyped

    private void txtServerNameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtServerNameKeyTyped
        if (evt.getKeyChar() == 10) {
            cmdServerActionPerformed(null);
        }
    }//GEN-LAST:event_txtServerNameKeyTyped

    private void txtIPKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIPKeyTyped
        if (evt.getKeyChar() == 10) {
            cmdConnectActionPerformed(null);
        }
    }//GEN-LAST:event_txtIPKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane anotherServer;
    private javax.swing.JLayeredPane body;
    private com.raven.swing.RButton cmdAnotherServer;
    private com.raven.swing.RButton cmdBack;
    private com.raven.swing.RButton cmdBack1;
    private com.raven.swing.RButton cmdConnect;
    private com.raven.swing.RButton cmdOK1;
    private com.raven.swing.RButton cmdOK2;
    private com.raven.swing.RButton cmdOKAnotherServer;
    private com.raven.swing.RButton cmdOwnServer;
    private com.raven.swing.RButton cmdServer;
    private javax.swing.JLayeredPane enterClientName;
    private javax.swing.JLayeredPane error;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel lbMessageError;
    private javax.swing.JLabel lbMessageLoading;
    private javax.swing.JLayeredPane loading;
    private javax.swing.JLayeredPane option;
    private javax.swing.JLayeredPane ownServer;
    private com.raven.swing.RPanelShadow panel;
    private com.raven.swing.RTextField txtClientName;
    private com.raven.swing.RTextField txtIP;
    private com.raven.swing.RTextField txtServerName;
    // End of variables declaration//GEN-END:variables

    public EventConnecting getEventOnlineOffline() {
        return eventOnlineOffline;
    }

    public void setEventOnlineOffline(EventConnecting eventOnlineOffline) {
        this.eventOnlineOffline = eventOnlineOffline;
    }

    public void setEvent_error_message(Event event_error_message) {
        this.event_error_message = event_error_message;
    }

    public void setEvent_remove_video(Event event_remove_video) {
        this.event_remove_video = event_remove_video;
    }
}
