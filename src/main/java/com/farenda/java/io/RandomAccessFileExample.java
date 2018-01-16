package com.farenda.java.io;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

public class RandomAccessFileExample {

    // 4 chars on 2 bytes each:
    private static final int USERNAME_SIZE = 4 * 2;
    private static final int SCORE_SIZE = 4;
    private static final int RECORD_SIZE
            = USERNAME_SIZE + SCORE_SIZE;

    private final String filename;

    public RandomAccessFileExample(String filename) {
        this.filename = filename;
    }

    private void changeRecord(int record, String name, int points)
            throws IOException {
        try (RandomAccessFile file = new RandomAccessFile(filename, "rw")) {
            // "-1" because the first record is at byte 0:
            file.seek(RECORD_SIZE * (record-1));
            file.writeChars(name);
            file.writeInt(points);
        }
    }

    private void printContent() throws IOException {
        try (RandomAccessFile file = new RandomAccessFile(filename, "r")) {
            long nrecords = file.length() / RECORD_SIZE;
            System.out.printf(
                    "Total number of records in \"%s\": %d%n",
                    filename, nrecords);

            while (nrecords-- > 0) {
                System.out.printf("%s: %d%n",
                        readUser(file), file.readInt());
            }
        }
    }

    private String readUser(RandomAccessFile file) throws IOException {
        return new String(new char[] {
                file.readChar(), file.readChar(),
                file.readChar(), file.readChar()
        });
    }

    private void writeRecords(Map<String, Integer> scores)
            throws IOException {
        try (RandomAccessFile file = new RandomAccessFile(filename, "rw")) {
            for (Map.Entry<String,Integer> userScore : scores.entrySet()) {
                file.writeChars(userScore.getKey());
                file.writeInt(userScore.getValue());
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Map<String, Integer> highScores = generateHighScores();
        RandomAccessFileExample app = new RandomAccessFileExample("user.data");
        app.writeRecords(highScores);
        app.printContent();
        app.changeRecord(3, "Mike", 9999);
        app.printContent();
    }

    private static Map<String, Integer> generateHighScores() {
        Random random = new Random();
        Map<String,Integer> highScores = new LinkedHashMap<>(5);
        for (int i = 1; i <= 5; ++i) {
            highScores.put("usr"+i, random.nextInt(1000));
        }
        return highScores;
    }
}
