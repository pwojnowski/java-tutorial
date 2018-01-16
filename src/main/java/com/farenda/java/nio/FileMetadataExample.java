package com.farenda.java.nio;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFileAttributes;
import java.time.Instant;

import static java.lang.System.out;
import static java.nio.file.LinkOption.NOFOLLOW_LINKS;

public class FileMetadataExample {

    public static void main(String[] args) throws IOException {
        Path file = Paths.get("/tmp");

        getAttributesInLegacyWay(file);

        getAttributesOneByOne(file);

        getAttributesInBulkWay(file);
    }

    private static void getAttributesInLegacyWay(Path path)
            throws IOException {
        File file = path.toFile();
        out.printf("Pre Java 7 metadata of '%s':%n",
                file.getAbsolutePath());
        out.println("directory: " + file.isDirectory());
        out.println("hidden   : " + file.isHidden());
        out.println("regular  : " + file.isFile());
        out.println("symlink  : " +
                !file.getAbsolutePath().equals(file.getCanonicalPath()));
        out.println("size     : " + file.length());
        out.println("modification time: "
                // Instant point in time from Java 8:
                + Instant.ofEpochMilli(file.lastModified()));
    }

    private static void getAttributesOneByOne(Path file)
            throws IOException {
        out.printf("%nMetadata of '%s':%n", file.toString());
        out.println("directory: " + Files.isDirectory(file));
        out.println("hidden   : " + Files.isHidden(file));
        // follows symlinks by default:
        out.println("regular  : "
                + Files.isRegularFile(file, NOFOLLOW_LINKS));
        out.println("is same: : " + Files.isSameFile(file, file));
        out.println("symlink  : " + Files.isSymbolicLink(file));
        out.println("size     : " + Files.size(file));
        out.println("owner    : " + Files.getOwner(file).getName());
        out.println("modification time: "
                + Files.getLastModifiedTime(file));
    }

    private static void getAttributesInBulkWay(Path file)
            throws IOException {
        out.printf("%nBasic attributes of '%s':%n",
                file.toString());
        PosixFileAttributes attrs = Files.readAttributes(
                file, PosixFileAttributes.class);
        out.println("directory: " + attrs.isDirectory());
        out.println("is other : " + attrs.isOther());
        out.println("regular  : " + attrs.isRegularFile());
        out.println("symlink  : " + attrs.isSymbolicLink());
        out.println("size     : " + attrs.size());
        out.println("unique id: " + attrs.fileKey());
        out.println("access time  : " + attrs.lastAccessTime());
        out.println("creation time: " + attrs.creationTime());
        out.println("modified time: " + attrs.lastModifiedTime());
        out.println("owner: " + attrs.owner());
        out.println("group: " + attrs.group());
    }
}
