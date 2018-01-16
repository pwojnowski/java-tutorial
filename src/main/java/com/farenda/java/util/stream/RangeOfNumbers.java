package com.farenda.java.util.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class RangeOfNumbers {

    public static void main(String[] args) {

        System.out.println(forLoopRange(20, 10));
        System.out.println(streamRange(20, 10));
        System.out.println(inclusiveRange(20, 10));
        System.out.println(iterateStream(20, 2, 10));
    }

    private static List<Integer> forLoopRange(int from, int limit) {
        List<Integer> numbers = new ArrayList<>(limit);
        for (int to = from+limit; from < to; ++from) {
            numbers.add(from);
        }
        return numbers;
    }

    private static List<Integer> streamRange(int from, int limit) {
        return IntStream.range(from, from+limit)
                .boxed()
                .collect(toList());
    }

    private static List<Integer> inclusiveRange(int from, int limit) {
        return IntStream.rangeClosed(from, from+limit)
                .boxed()
                .collect(toList());
    }

    private static List<Integer> iterateStream(int from, int step, int limit) {
        return IntStream.iterate(from, i -> i+ step)
                .limit(limit/step)
                .boxed()
                .collect(toList());
    }
}
