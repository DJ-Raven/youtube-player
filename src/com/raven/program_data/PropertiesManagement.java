package com.raven.program_data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class PropertiesManagement {

    public static PropertiesManagement instance;
    private PropertiesData data;
    private final String rootDirectory = "C:\\ProgramData\\youtuble player";

    private PropertiesManagement() {
    }

    public static PropertiesManagement getInstance() {
        if (instance == null) {
            instance = new PropertiesManagement();
        }
        return instance;
    }

    public void init() throws IOException {
        File file = new File(rootDirectory + "\\data.properties");
        if (!file.exists()) {
            OutputStream out = new FileOutputStream(file);
            Properties pro = new Properties();
            pro.put("server_name", "Server");
            pro.put("client_name", "");
            pro.put("ip_server", "192.168.");
            pro.put("auto_play", "true");
            pro.store(out, null);
            out.close();
        }
        readProperty();
    }

    private void readProperty() throws IOException {
        File file = new File(rootDirectory + "\\data.properties");
        InputStream in = new FileInputStream(file);
        Properties pro = new Properties();
        pro.load(in);
        String serverName = pro.getProperty("server_name");
        String clientName = pro.getProperty("client_name");
        String ipServer = pro.getProperty("ip_server");
        boolean autoPlay = Boolean.valueOf(pro.getProperty("auto_play"));
        data = new PropertiesData(serverName, clientName, ipServer, autoPlay);
        in.close();
    }

    public void store(String key, String value) throws IOException {
        File file = new File(rootDirectory + "\\data.properties");
        InputStream in = new FileInputStream(file);
        Properties pro = new Properties();
        pro.load(in);
        pro.setProperty(key, value);
        pro.store(new FileOutputStream(file), null);
        in.close();
    }

    public PropertiesData getData() {
        return data;
    }
}
