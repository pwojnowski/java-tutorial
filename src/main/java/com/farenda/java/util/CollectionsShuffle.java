package com.farenda.java.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class CollectionsShuffle {

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        System.out.println("Numbers: " + numbers);

        Collections.shuffle(numbers);
        System.out.println("Shuffled: " + numbers);

        Collections.shuffle(numbers);
        System.out.println("Shuffled again: " + numbers);

        Collections.shuffle(numbers, new Random());
        System.out.println("Shuffled with Random: " + numbers);
    }
}
