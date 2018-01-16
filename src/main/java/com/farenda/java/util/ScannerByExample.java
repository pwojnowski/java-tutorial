package com.farenda.java.util;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class ScannerByExample {

    public static void main(String[] args) throws IOException {

        currentDelimiter();

        readNumbersFromString();

        readFromInputStream();

        readFromFile();

    }

    private static void currentDelimiter() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Current delimiter: "
                    + scanner.delimiter());
        }
    }

    private static void readNumbersFromString() {
        try (Scanner scanner = new Scanner("1 2 3 5 8 13")) {
            int sum = 0;
            while (scanner.hasNextInt()) {
                sum += scanner.nextInt();
            }
            System.out.println("Sum of numbers: " + sum);
        }
    }

    private static void readFromInputStream() {
        byte[] data = "one,two,three".getBytes();
        InputStream source = new ByteArrayInputStream(data);
        try (Scanner scanner = new Scanner(source)) {
            scanner.useDelimiter(",");
            while (scanner.hasNext()) {
                System.out.println(scanner.next());
            }
        }
    }

    private static void readFromFile() throws IOException {
        Path source = Paths.get("/proc/meminfo");
        System.out.println("Reading from file: " + source);
        try (Scanner scanner = new Scanner(source)) {
            scanner.useDelimiter("\n");
            while (scanner.hasNext()) {
                System.out.println(scanner.next());
            }
        }
    }
}
