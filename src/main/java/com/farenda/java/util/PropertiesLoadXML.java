package com.farenda.java.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class PropertiesLoadXML {

    public static void main(String[] args) throws IOException {
        Properties config = loadFromFile("app-config.xml");

        printToSystemOutput(config);
    }

    private static void printToSystemOutput(Properties config) throws IOException {
        config.store(System.out, "Loaded properties:");
    }

    private static Properties loadFromFile(String filename) throws IOException {
        Path configLocation = Paths.get(filename);
        System.out.println("Config location: " + configLocation.toString());
        try (InputStream stream = Files.newInputStream(configLocation)) {
            Properties config = new Properties();
            config.loadFromXML(stream);
            return config;
        }
    }
}
