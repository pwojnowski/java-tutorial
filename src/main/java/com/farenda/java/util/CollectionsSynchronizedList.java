package com.farenda.java.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class CollectionsSynchronizedList {

    public static void main(String[] args) {
        List<String> list = Collections.synchronizedList(
                new LinkedList<>(Arrays.asList("a", "b", "c")));
        System.out.println("thread-safe list: " + list);

        // single operations are atomic:
        list.add("d");

        // multiple operations have to be synchronized:
        synchronized (list) {
            for (Iterator<String> it = list.iterator(); it.hasNext(); ) {
                System.out.println("item: " + it.next());
            }
        }
    }
}
