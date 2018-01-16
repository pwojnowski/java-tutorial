package com.farenda.java.nio;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.LinkedList;
import java.util.Queue;

import static java.util.Collections.asLifoQueue;

public class WalkFileTreeExample {

    public static class FileSizeCalculator extends SimpleFileVisitor<Path> {

        private Queue<Long> sizesOfParents
                = asLifoQueue(new LinkedList<>());
        private long currentDirSize;

        @Override
        public FileVisitResult preVisitDirectory(
                Path dir, BasicFileAttributes attrs) {
            sizesOfParents.add(currentDirSize);
            currentDirSize = 0;
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult visitFile(
                Path file, BasicFileAttributes attrs) {
            currentDirSize += attrs.size();
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult postVisitDirectory(
                Path dir, IOException e) {
            displaySize(dir, currentDirSize);
            currentDirSize += sizesOfParents.remove();
            return FileVisitResult.CONTINUE;
        }
    }

    public static void main(String[] args) throws IOException {
        Path path = Paths.get("/var/lib/mongodb");

        calculateSizeNaively(path);

        calculateSizeByWalkingTree(path);
    }

    private static void calculateSizeNaively(Path path) throws IOException {
        System.out.println("Naive Files.size(dir) version:");
        displaySize(path, Files.size(path));
    }

    private static void calculateSizeByWalkingTree(Path path) throws IOException {
        System.out.println("\nUsing FileVisitor:");
        FileSizeCalculator calculator = new FileSizeCalculator();
        Files.walkFileTree(path, calculator);
    }

    private static void displaySize(Path dir, long size) {
        System.out.printf("Directory %s size: %d KB%n",
                dir, (size / 1024));
    }
}
