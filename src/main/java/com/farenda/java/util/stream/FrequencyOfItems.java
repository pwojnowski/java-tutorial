package com.farenda.java.util.stream;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

public class FrequencyOfItems {

    public static void main(String[] args) {
        frequencyOfNumbers();
    }

    private static void frequencyOfNumbers() {
        List<Integer> ids = randomNumbers(1000, 10);

        Map<Integer, Integer> frequencies = ids.stream()
                .collect(toMap(identity(), v -> 1, Integer::sum));
        System.out.println("Frequencies:\n" + frequencies);
    }

    private static List<Integer> randomNumbers(int n, int bound) {
        Random rand = new Random();
        return IntStream
                .generate(() -> rand.nextInt(bound))
                .limit(n)
                .boxed()
                .collect(Collectors.toList());
    }
}