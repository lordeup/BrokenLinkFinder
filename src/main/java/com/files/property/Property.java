package com.files.property;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Property {
    private Properties properties;

    public Property() throws IOException {
        properties = new Properties();
        String pathToProperties = "src/main/resources/config.properties";
        try (FileInputStream fileInputStream = new FileInputStream(pathToProperties)) {
            properties.load(fileInputStream);
        }
    }

    public int getConnectionTimeout() {
        try {
            return Integer.parseInt(properties.getProperty("connectionTimeout"));
        } catch (NumberFormatException e) {
            return 10000;
        }
    }

    public int getThreadNumber() {
        try {
            return Integer.parseInt(properties.getProperty("threadNumber"));
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
