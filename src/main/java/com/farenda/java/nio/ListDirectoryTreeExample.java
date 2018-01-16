package com.farenda.java.nio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static java.lang.System.out;

public class ListDirectoryTreeExample {

    public static void main(String[] args) throws IOException {
        String dirName = null;
        int searchDepth = 20;

        switch (args.length) {
            case 2: searchDepth = Integer.parseInt(args[1]);
                // fallthrough to set also dirName:
            case 1: dirName = args[0];
                break;
            default:
                out.println("Usage: ListDirectoryTreeExample dir [depth]");
                System.exit(1);
        }

        Path path = Paths.get(dirName);
        out.printf("Content of %s with depth %d:%n",
                dirName, searchDepth);

        // Use try-clause to close the stream immediately:
        try (Stream<Path> paths = Files.walk(path, searchDepth)) {
            paths.forEachOrdered(out::println);
        }
    }
}
