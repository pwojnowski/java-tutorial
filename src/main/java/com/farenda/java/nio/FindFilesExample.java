package com.farenda.java.nio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class FindFilesExample {

    // Accepts search directory as parameter:
    public static void main(final String[] args) throws IOException {

        // Search in current directory or
        // the one given as program param:
        Path dir;
        if (args.length == 0) {
            dir = Paths.get(".");
        } else {
            dir = Paths.get(args[0]);
        }

        System.out.printf("First 5 '.txt' files in %s:%n",
                dir.toAbsolutePath());

        try (Stream<Path> paths = Files.find(
                dir, Integer.MAX_VALUE,
                (path,attrs) -> attrs.isRegularFile()
                        && path.toString().endsWith(".txt"))) {
            // Consume only the first 5 from the stream:
            paths.limit(5).forEach(System.out::println);
        }
    }
}
