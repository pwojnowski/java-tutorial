package com.farenda.java.nio;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFileAttributes;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.List;

import static java.util.Arrays.asList;

public class CheckFilePermissionsExample {

    public static void main(String[] args) throws IOException {
        List<String> names = asList("/tmp", "/root", "/bin/ls");

        System.out.println("Pre Java 7 way - File.canR/W/E");
        for (String name : names) {
            preJava7Way(new File(name));
        }

        System.out.println("\nJava 7 Files.isR/W/E");
        for (String name : names) {
            usingJava7Files(name);
        }

        System.out.println("\nJava 7 using attributes from metadata");
        for (String name : names) {
            usingJava7Metadata(name);
        }
    }

    private static void preJava7Way(File file) {
        // For legacy code:
        StringBuilder perms = new StringBuilder();
        perms.append(file.canRead() ? 'r' : '-');
        perms.append(file.canWrite() ? 'w' : '-');
        perms.append(file.canExecute() ? 'x' : '-');
        System.out.printf("Permissions of %s: %s%n",
                file.getAbsolutePath(), perms);
    }

    private static void usingJava7Files(String name) {
        // Simple permissions using Files from Java 7:
        Path path = Paths.get(name);
        StringBuilder perms = new StringBuilder();
        perms.append(Files.isReadable(path) ? 'r' : '-');
        perms.append(Files.isWritable(path) ? 'w' : '-');
        perms.append(Files.isExecutable(path) ? 'x' : '-');
        System.out.printf("Permissions of %s: %s%n",
                path.toString(), perms);
    }

    private static void usingJava7Metadata(String name) throws IOException {
        Path path = Paths.get(name);
        // For DOS/Windows use DosFileAttributes.class:
        PosixFileAttributes attrs = Files.readAttributes(
                path, PosixFileAttributes.class);
        // convert objects to 'rwx' style strings:
        String asString = PosixFilePermissions.toString(attrs.permissions());
        System.out.printf("Posix permissions of %s: %s%n", name, asString);
    }
}
