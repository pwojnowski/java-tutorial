package com.farenda.java.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CollectionsRotate {

    public static void main(String[] args) {
        List<String> items = Arrays.asList("a", "b", "c", "d", "e");
        System.out.println("Items: " + items);

        forwardBy(items, 1);

        forwardBy(items, 1);

        backwardBy(items, 2);

        moveXbyY(items, 1, 3);
    }

    private static void moveXbyY(List<String> items, int x, int y) {
        String e = items.get(x);
        // +1 because index 'to' is exclusive
        Collections.rotate(items.subList(x, x+y+1), -1);
        System.out.printf("Move '%s' by %d: %s%n", e, y, items);
    }

    private static void backwardBy(List<String> items, int distance) {
        Collections.rotate(items, -distance);
        System.out.printf("Backward %d: %s%n", distance, items);
    }

    private static void forwardBy(List<String> items, int distance) {
        Collections.rotate(items, distance);
        System.out.printf("Forward %d: %s%n", distance, items);
    }
}
