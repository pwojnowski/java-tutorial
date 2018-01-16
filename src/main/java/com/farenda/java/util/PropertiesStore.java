package com.farenda.java.util;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public class PropertiesStore {

    public static void main(String[] args) throws IOException {
        Properties config = createAppProperties();

        printToSystemOutput(config);

        Path configFile = saveToFile(config);

        displayFile(configFile);
    }

    private static Properties createAppProperties() {
        Properties config = new Properties();
        config.setProperty("minThreads", "5");
        config.setProperty("maxThreads", "40");
        return config;
    }

    private static void displayFile(Path path) throws IOException {
        System.out.printf("%nContents of %s:%n", path.toString());
        Files.readAllLines(path).forEach(System.out::println);
    }

    private static void printToSystemOutput(Properties config) throws IOException {
        config.store(System.out, "Writing to System.out:");
    }

    private static Path saveToFile(Properties config) throws IOException {
        Path file = Files.createTempFile("app-config", ".properties");
        // To use store(Writer) change to Files.newBufferedWriter(file)
        try (OutputStream stream = Files.newOutputStream(file)) {
            config.store(stream, "Writing to a file using OutputStream:");
        }
        return file;
    }
}
