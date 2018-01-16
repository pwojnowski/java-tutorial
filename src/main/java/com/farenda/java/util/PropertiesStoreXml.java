package com.farenda.java.util;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public class PropertiesStoreXml {

    public static void main(String[] args) throws IOException {
        Properties config = createAppProperties();

        Path configFile = saveAsXml(config);

        displayFile(configFile);
    }

    private static Properties createAppProperties() {
        Properties config = new Properties();
        config.setProperty("connectionTimeMillis", "1000");
        config.setProperty("databaseURL", "localhost:2097");
        return config;
    }

    private static void displayFile(Path path) throws IOException {
        System.out.printf("%nContents of %s:%n", path.toString());
        Files.readAllLines(path).forEach(System.out::println);
    }

    private static Path saveAsXml(Properties config) throws IOException {
        Path file = Files.createTempFile("app-config", ".xml");
        try (OutputStream stream = Files.newOutputStream(file)) {
            config.storeToXML(stream, "Application properties as XML");
        }
        return file;
    }
}
