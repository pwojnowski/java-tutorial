package com.farenda.java.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class ReadTextFileExample {

    public static void main(String[] args) throws IOException {
        Map<String,Integer> highScores = new LinkedHashMap<>();

        String scoreFile = "highscores.csv";

        try (BufferedReader input = openReader(scoreFile)) {
            System.out.println("File header: " + readHeader(input));

            String row;
            while ((row = input.readLine()) != null) {
                parseRow(row, highScores);
            }
        }

        System.out.println("Read high scores:\n" + highScores);
    }

    private static BufferedReader openReader(String fileName)
            throws IOException {
        // Don't forget to add buffering to have better performance!
        return new BufferedReader(new FileReader(fileName));
    }

    private static String readHeader(BufferedReader input)
            throws IOException {
        return input.readLine();
    }

    private static void parseRow(String row, Map<String, Integer> highScores) {
        String[] columns = row.split(",");
        highScores.put(columns[0], Integer.valueOf(columns[1]));
    }
}
