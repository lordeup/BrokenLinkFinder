package com.files.property;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Property {
    private Properties properties = new Properties();

    public Property() throws IOException {
        String pathToProperties = "src/main/resources/config.properties";
        FileInputStream fileInputStream = new FileInputStream(pathToProperties);
        properties.load(fileInputStream);
    }

    public int getConnectionTimeout() {
        try {
            return Integer.parseInt(properties.getProperty("connectionTimeout"));
        } catch (NumberFormatException e) {
            return 10000;
        }
    }
}
