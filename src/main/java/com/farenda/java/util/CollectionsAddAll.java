package com.farenda.java.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CollectionsAddAll {

    public static void main(String[] args) {
        List<String> items = new ArrayList<>();

        // Too verbose:
        items.add("added");
        items.add("manually");
        System.out.println(items);

        // Sometimes better:
        items.addAll(Arrays.asList("as", "an", "array"));
        System.out.println(items);

        // The best way: reuse!
        Collections.addAll(items, "reusing", "Collections", "library");
        System.out.println(items);
    }
}
