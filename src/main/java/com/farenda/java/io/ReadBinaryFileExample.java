package com.farenda.java.io;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class ReadBinaryFileExample {

    public static void main(String[] args) throws IOException {
        String dataFile = "binary-file.dat";

        StringBuilder sb = new StringBuilder();
        try (BufferedReader input = openFile(dataFile)) {
            String line;
            while ((line = input.readLine()) != null) {
                sb.append(line);
            }
        }

        System.out.println("Data from file:\n" + sb);
    }

    private static BufferedReader openFile(String fileName)
            throws IOException {
        // Don't forget to add buffering to have better performance!
        return new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(fileName),
                        StandardCharsets.UTF_8));
    }
}
