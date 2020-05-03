package com.raven.socket.data;

public interface EventConnecting {

    public static enum ConnectType {
        ERROR, SUCCESS
    }

    public void execute(ConnectType type);
}
