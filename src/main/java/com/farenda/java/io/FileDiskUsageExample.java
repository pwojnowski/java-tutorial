package com.farenda.java.io;

import java.io.File;

public class FileDiskUsageExample {

    private static final int GB = 1024 * 1024 * 1024;

    public static final void main(final String[] args) {
        File file = new File(".");
        System.out.printf("    Total: %d GB%n", file.getTotalSpace() / GB);
        System.out.printf("     Free: %d GB%n", file.getFreeSpace() / GB);
        System.out.printf("Available: %d GB%n", file.getUsableSpace() / GB);
    }

}
