package com.raven.program_data;

public class PropertiesData {

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getIpServer() {
        return ipServer;
    }

    public void setIpServer(String ipServer) {
        this.ipServer = ipServer;
    }

    public boolean isAutoPlay() {
        return autoPlay;
    }

    public void setAutoPlay(boolean autoPlay) {
        this.autoPlay = autoPlay;
    }

    public PropertiesData(String serverName, String clientName, String ipServer, boolean autoPlay) {
        this.serverName = serverName;
        this.clientName = clientName;
        this.ipServer = ipServer;
        this.autoPlay = autoPlay;
    }

    public PropertiesData() {
    }

    private String serverName;
    private String clientName;
    private String ipServer;
    private boolean autoPlay;
}
