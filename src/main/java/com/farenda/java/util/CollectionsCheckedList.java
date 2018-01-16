package com.farenda.java.util;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class CollectionsCheckedList {

    public static void main(String[] args) {
        List<String> items = new LinkedList<>();
        Collections.addAll(items, "java", "clojure", "python", "scala");
        System.out.println("Items: " + items);

        addItemInsecurely(items);
        System.out.println("Insecure items: " + items);

        List<String> typeSafe = Collections.checkedList(items, String.class);
        addItemInsecurely(typeSafe);
        System.out.println("No new insecure items!");
    }

    // Add by reflection, 3rd party lib or other insecure way:
    private static void addItemInsecurely(List items) {
        int wrongTypeItem = items.size();
        System.out.println("Adding item: " + wrongTypeItem);
        items.add(wrongTypeItem);
    }
}
