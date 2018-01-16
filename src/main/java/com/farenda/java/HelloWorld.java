package com.farenda.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HelloWorld {

    public static void main(String[] args) {
        System.out.println("Hello, world!");

        getFromStream(new ArrayList<>());
        getFromStream(Arrays.asList("a", "b", "c"));

        getOldWay(new ArrayList<>());
        getOldWay(Arrays.asList("a", "b", "c"));
    }

    private static void getOldWay(List<String> list) {
        System.out.println("Item old way: " + (list.isEmpty() ? null : list.get(0)));
    }

    private static void getFromStream(List<String> list) {
        System.out.println("Item from stream: " + list.stream().findFirst().orElse(null));
    }
}
