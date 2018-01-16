package com.farenda.java.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CollectionsSwap {

    public static void main(String[] args) {
        List<Integer> items = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Collections.shuffle(items);
        System.out.println("Ordered randomly: " + items);

        for (int i = 0, n = items.size(); i < n; ++i) {
            for (int j = i+1; j < n; ++j) {
                if (items.get(i) > items.get(j)) {
                    System.out.printf("Swap(%d,%d) = %2d <-> %2d%n",
                            i, j, items.get(i), items.get(j));
                    Collections.swap(items, i, j);
                }
            }
        }

        System.out.println("Sorted: " + items);
    }
}
