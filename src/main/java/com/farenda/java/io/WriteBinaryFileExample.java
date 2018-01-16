package com.farenda.java.io;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class WriteBinaryFileExample {

    public static void main(String[] args) throws IOException {
        String targetFile = "binary-file.dat";

        // Create a new file and override when already exists:
        try (OutputStream output = openFile(targetFile)) {
            output.write(getUtf8Bytes("Saluton, mondo!"));
        }

        // Reopen the file but for appending:
        try (OutputStream output = openFile(targetFile, true)) {
            output.write(getUtf8Bytes("Some more data!"));
        }
    }

    private static byte[] getUtf8Bytes(String s) {
        // Always specify encoding and not rely on default!
        return s.getBytes(StandardCharsets.UTF_8);
    }

    private static BufferedOutputStream openFile(String fileName)
            throws IOException {
        return openFile(fileName, false);
    }

    private static BufferedOutputStream openFile(String fileName, boolean append)
            throws IOException {
        // Don't forget to add buffering to have better performance!
        return new BufferedOutputStream(new FileOutputStream(fileName, append));
    }
}
