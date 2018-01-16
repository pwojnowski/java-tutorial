package com.farenda.java.io;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.regex.Pattern;

public class FileListFilesExample {

    private static class RegExpFilter implements FilenameFilter {

        private final Pattern pattern;

        public RegExpFilter(String pattern) {
            this.pattern = Pattern.compile(pattern);
        }

        @Override
        public final boolean accept(File file, String name) {
            return pattern.matcher(name).matches();
        }
    }

    // The first arg is dir, the second one (if any) files pattern.
    public static void main(final String[] args) {
        File dir = selectDirectory(args);

        String[] names = getFileNames(dir, args);

        System.out.println("Files and directories in "
                + dir.getAbsolutePath());
        for (String file : names) {
            System.out.println(file);
        }
    }

    private static File selectDirectory(String[] args) {
        // current or given as program param
        return (args.length == 0) ? new File(".") : new File(args[0]);
    }

    private static String[] getFileNames(File dir, String[] args) {
        String[] names;
        if (args.length > 1) {
            names = dir.list(new RegExpFilter(args[1]));
        } else {
            names = dir.list();
        }

        // Sort just for better readability
        Arrays.sort(names);

        return names;
    }
}
