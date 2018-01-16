package com.farenda.java.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class CollectionsUnmodifiableList {

    public static void main(String[] args) {
        List<Integer> arrayList = Arrays.asList(1, 2, 3);
        List<Integer> scores = new LinkedList<>(arrayList);

        System.out.println("scores: " + scores);

        arrayList.set(0, 7);
        System.out.println("updated array list: " + arrayList);

        scores.add(42);
        System.out.println("update scores: " + scores);

        scores = Collections.unmodifiableList(scores);

        try {
            scores.add(7);
        } catch (UnsupportedOperationException e) {
            System.out.println("While adding: " + e.getClass().getSimpleName());
        }

        try {
            scores.set(0, 7);
        } catch (UnsupportedOperationException e) {
            System.out.println("While setting: " + e.getClass().getSimpleName());
        }
    }
}
