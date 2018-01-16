package com.farenda.java.io;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

public class WriteTextFileExample {

    private static final Random rand = new Random();

    public static void main(String[] args) throws IOException {
        Map<String,Integer> playersPoints = generateSampleData();

        String scoreFile = "highscores.csv";

        // Create a new file and override when already exists:
        try (Writer output = openWriter(scoreFile)) {
            writeHeader(output);
            for (Entry<String, Integer> player : playersPoints.entrySet()) {
                output.write(formatRow(player));
            }
        }

        Entry<String, Integer> newUser =
                new SimpleImmutableEntry<>("newUser", generatePoints());
        // Reopen the file but for appending:
        try (Writer output = openWriter(scoreFile, true)) {
            output.write(formatRow(newUser));
        }
    }

    private static Map<String,Integer> generateSampleData() {
        Map<String,Integer> playersPoints = new LinkedHashMap<>();
        for (int i = 1; i <= 5; ++i) {
            playersPoints.put("user" + i, generatePoints());
        }
        return playersPoints;
    }

    private static int generatePoints() {
        return rand.nextInt(1000) + 1;
    }

    private static void writeHeader(Writer output) throws IOException {
        output.append("#name,points").append(System.lineSeparator());
    }

    private static BufferedWriter openWriter(String fileName)
            throws IOException {
        return openWriter(fileName, false);
    }

    private static BufferedWriter openWriter(String fileName, boolean append)
            throws IOException {
        // Don't forget to add buffering to have better performance!
        return new BufferedWriter(new FileWriter(fileName, append));
    }

    private static String formatRow(Entry<String, Integer> player) {
        String row = String.format("%s,%s%n",
                player.getKey(), player.getValue());
        System.out.print("Adding row: " + row);
        return row;
    }
}
