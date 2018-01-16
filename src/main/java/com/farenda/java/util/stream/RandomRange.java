package com.farenda.java.util.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class RandomRange {

    public static void main(String[] args) {
        int limit = 10;

        System.out.println(forLoopRange(limit, 20, 30));
        System.out.println(randomInts(limit, 10, 20));
        System.out.println(streamRandomRange(limit, 80, 90));
    }

    private static List<Integer> forLoopRange(int limit, int from, int to) {
        Random random = new Random();
        int bound = to-from;
        List<Integer> numbers = new ArrayList<>(limit);
        for (int i = 1; i <= limit; i++) {
            numbers.add(random.nextInt(bound)+from);
        }
        return numbers;
    }

    private static List<Integer> randomInts(int limit, int from, int to) {
        Random rand = new Random();
        return rand.ints(limit, from, to).boxed().collect(toList());
    }

    private static List<Integer> streamRandomRange(int limit, int from, int to) {
        Random rand = new Random();
        int bound = to - from;
        return IntStream.generate(() -> rand.nextInt(bound)+from)
                .limit(limit)
                .boxed()
                .collect(toList());
    }
}
