package com.gorest.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {
    private Properties properties;

    public PropertyReader(String filePath) {
        try {
            properties = new Properties();
            FileInputStream file = new FileInputStream(filePath);
            properties.load(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }
    
}
