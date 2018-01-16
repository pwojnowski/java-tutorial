package com.farenda.java.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class CollectionsSingleton {

    public static void main(String[] args) {
        showImmutability();

        System.out.println();

        showUseCase();
    }

    private static void showImmutability() {
        List<Integer> singleton = Collections.singletonList(42);

        System.out.println("singleton List: " + singleton);

        try {
            singleton.add(3);
        } catch (UnsupportedOperationException e) {
            System.out.println("While adding: " + e);
        }

        try {
            singleton.remove(0);
        } catch (UnsupportedOperationException e) {
            System.out.println("While removing: " + e);
        }

        try {
            singleton.set(0, 3);
        } catch (UnsupportedOperationException e) {
            System.out.println("While setting: " + e);
        }
    }

    private static void showUseCase() {
        List<String> scores = new LinkedList<>(Arrays.asList("1", "2", "3", "1", "4"));
        System.out.println("Scores: " + scores);
        scores.remove("1");
        System.out.println("After scores.remove(1): " + scores);

        scores = new LinkedList<>(Arrays.asList("1", "2", "3", "1", "4"));
        System.out.println("Scores: " + scores);
        scores.removeAll(Collections.singleton("1"));
        System.out.println("After scores.removeAll(singleton(1)): " + scores);
    }
}
