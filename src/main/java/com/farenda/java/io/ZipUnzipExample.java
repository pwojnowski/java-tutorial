package com.farenda.java.io;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ZipUnzipExample {

    public static void main(String[] args) throws IOException {
        String filename = "/tmp/sample-file.zip";

        createZipFile(filename, "This data will be zipped.");

        System.out.println("Unzipped content: " + readZipFile(filename));
    }

    private static void createZipFile(String filename, String content) throws IOException {
        // try-with-resources will close everything automatically
        try (Writer writer = createWriter(filename)) {
            System.out.println("Zipping: " + content);
            writer.write(content);
        }
    }

    private static Writer createWriter(String filename) throws IOException {
        OutputStream os = createOutputStream(filename);
        return new OutputStreamWriter(os);
    }

    private static OutputStream createOutputStream(String filename) throws IOException {
        OutputStream fos = Files.newOutputStream(Paths.get(filename));
        ZipOutputStream zos = new ZipOutputStream(fos);
        // Sample zip entry, here: a filename
        ZipEntry entry = new ZipEntry("sample-file.txt");
        zos.putNextEntry(entry);
        return new BufferedOutputStream(zos);
    }

    private static String readZipFile(String filename) throws IOException {
        // try-with-resources will close everything automatically
        try (BufferedReader reader = createReader(filename)) {
            // use Java 8 Streams to load all lines:
            return reader.lines().collect(Collectors.joining());
        }
    }

    private static BufferedReader createReader(String filename) throws IOException {
        InputStream zis = createInputStream(filename);
        return new BufferedReader(new InputStreamReader(zis));
    }

    private static InputStream createInputStream(String filename) throws IOException {
        InputStream is = Files.newInputStream(Paths.get(filename));
        ZipInputStream zis = new ZipInputStream(is);
        // for many entries read one by one
        ZipEntry entry = zis.getNextEntry();
        System.out.println("Zip entry: " + entry.getName());
        return zis;
    }
}
