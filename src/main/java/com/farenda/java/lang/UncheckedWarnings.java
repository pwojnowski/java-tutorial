package com.farenda.java.lang;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Possible, but very bad practice:
//@SuppressWarnings("unchecked")
public class UncheckedWarnings {

    public static void main(String[] args) {
        // Unchecked assignment: 'java.util.List' to 'java.util.List<java.lang.String>'

        @SuppressWarnings("unchecked")
        List<String> names = namesFromLibrary();
        System.out.println(names);

        List<String> moreNames = moreNames();
        System.out.println(moreNames);

        List<String> evenMoreNames = evenMoreNames();
        System.out.println(evenMoreNames);
    }

    private static List namesFromLibrary() {
        return Arrays.asList("Java", "Clojure");
    }

    @SuppressWarnings("unchecked")
    private static List<String> moreNames() {
        return new ArrayList(Arrays.asList("Bernard", "Witold"));
    }

    private static List<String> evenMoreNames() {
        @SuppressWarnings("unchecked")
        ArrayList names = new ArrayList(Arrays.asList("Jon", "Snow"));
        return names;
    }
}
