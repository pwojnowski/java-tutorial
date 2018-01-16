package com.farenda.java.nio;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

import static java.lang.System.out;
import static java.nio.file.LinkOption.NOFOLLOW_LINKS;

public class PathCheatSheet {

    public static void main(String[] args) throws IOException {

        constructPath();
        pathConversion();
        resolveSymlink();
        pathNormalization();
        filenameFromFilepath();
        getParentDirectory();
        comparePaths();
        findingRelativePaths();

        // Taking relative parts:
        //Path relative = path.subpath(1,2);
//        System.out.println("'/proc/version'.subpath(1,2): " + relative);
//
//        out.println("path.isAbsolute(): " + relative.isAbsolute());
//        out.println("path.toAbsolutePath(): " + relative.toAbsolutePath());


//        System.out.printf("join %s to %s: %s%n",
//                path1, path2, path1.resolve(path2));
//        System.out.printf("join %s to %s: %s%n",
//                path1, "sibling", path1.resolve("sibling"));
//
//        System.out.printf("%s as sibling to %s: %s%n",
//                path2, path1, path1.resolveSibling(path2));
//        System.out.printf("%s as sibling to %s: %s%n",
//                path2, path1, path1.resolveSibling("user2"));
    }

    private static void findingRelativePaths() {
        Path path1 = Paths.get("/home/user1/bin");
        Path path2 = Paths.get("/home/user2/bin");
        System.out.printf("Path from %s to %s: %s%n",
                path1, path2, path1.relativize(path2));

        path1 = Paths.get("bin");
        path2 = Paths.get("doc");
        System.out.printf("Path from %s to %s: %s%n",
                path1, path2, path1.relativize(path2));

        try {
            path1 = Paths.get("/user1/bin");
            path2 = Paths.get("doc");
            path1.relativize(path2);
        } catch (IllegalArgumentException e) {
            System.out.printf("Path from %s to %s: %s%n",
                    path1, path2, e);
        }
    }

    private static void getParentDirectory() {
        Path path = Paths.get("/proc/nested/version");
        out.println("root: " + path.getRoot());
        out.println("parent: " + path.getParent());

        System.out.println("Number of parts: " + path.getNameCount());
        System.out.println("top parent: " + path.getName(0));
        System.out.println("direct parent: " + path.getName(1));
        System.out.println("filename: " + path.getName(2));

        // Iteration over parts of path:
        List<Path> parts = new LinkedList<>();
        path.iterator().forEachRemaining(parts::add);
        if (parts.size() > 1) {
            System.out.println("Parent using iterator: "
                    + parts.get(parts.size()-2));
        }
    }

    private static void filenameFromFilepath() {
        // Getting parts of path:
        Path path = Paths.get("/proc/version");
        out.println("path.getFileName(): " + path.getFileName().toString());

        out.println("number of name parts: " + path.getNameCount());
        out.println("path.getName(0): " + path.getName(0));
        out.println("path.getName(1): " + path.getName(1));
    }

    private static void comparePaths() {
        Path path = Paths.get("/proc/version");

        Path symlink = Paths.get("/vmlinuz");
        out.printf("%s.compareTo(%s): %d%n",
                path, symlink, path.compareTo(symlink));

        Path other = Paths.get("/proc/./version");
        out.printf("%s.compareTo(%s): %d%n",
                path, other, path.compareTo(other));

        other = Paths.get("/proc/version");
        out.printf("%s.compareTo(%s): %d%n",
                path, other, path.compareTo(other));

        Path version = Paths.get("version");
        out.printf("%s.endsWith(%s): %s%n",
                path, version, path.endsWith(version));
        out.printf("%s.endsWith(\"version\"): %s%n",
                path, path.endsWith("version"));
        out.printf("%s.endsWith(\"something\"): %s%n",
                path, path.endsWith("something"));

        other = Paths.get("/proc");
        out.printf("%s.startsWith(%s): %s%n",
                path, other, path.startsWith(other));
        out.printf("%s.startsWith(\"/proc\"): %s%n",
                path, path.startsWith("/proc"));
    }

    private static void constructPath() {
        Path path = Paths.get("/proc/version");
        out.println("Path from absolute name: " + path);

        // from URI:
        path = Paths.get(URI.create("file:///proc/version"));
        out.println("Path from URI: " + path);

        path = Paths.get("/proc", "version");
        out.println("Path from parts: " + path);

        // The same as above:
        path = FileSystems.getDefault().getPath("/proc", "version");
        out.println("Path from FileSystem: " + path);
    }

    private static void pathConversion() {
        // conversion from/to File:
        Path path = new File("/proc/version").toPath();
        out.println("Path from File: " + path);
        out.println("path.toFile(): " + path.toFile());

        // conversion from/to URI:
        out.println("path.toUri(): " + path.toUri());
        path = Paths.get(URI.create("file:///proc/version"));
        out.println("Path from URI: " + path);
    }

    private static void pathNormalization() throws IOException {
        // Normalization - removal of redundant elements:
        Path path = Paths.get("/proc/../proc/./version");
        out.printf("'%s'.normalize(): %s%n", path, path.normalize());

        // Protects from going to far:
        path = Paths.get("/proc/../../..");
        out.printf("'%s'.normalize(): %s%n", path, path.normalize());

        // Watch out for non existing path:
        path = Paths.get("/proc/version/..");
        out.printf("'%s'.normalize(): %s%n", path, path.normalize());

        path = Paths.get("/proc/version/..");
        out.printf("'%s'.toRealPath(): %s%n", path, path.toRealPath());
    }

    private static void resolveSymlink() throws IOException {
        Path path = Paths.get("/usr/bin/java");
        System.out.printf("'%s'.toRealPath(): %s%n",
                path, path.toRealPath());

        System.out.printf("'%s'.toRealPath(NOFOLLOW_LINKS): %s%n",
                path, path.toRealPath(NOFOLLOW_LINKS));

        path = Paths.get("/usr/./bin/java");
        System.out.printf("'%s'.toRealPath(NOFOLLOW_LINKS): %s%n",
                path, path.toRealPath(NOFOLLOW_LINKS));
    }
}
