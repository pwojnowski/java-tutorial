package com.farenda.java.util;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.BinaryOperator;

import static java.util.Arrays.asList;

public class ReduceExamples {

    public static void main(String[] args) {
        sumToOptional();
        sumToEmptyOptional();
        sumToInt();

        reduceToNoCombiner();
        reduceToCombiner();
    }

    private static void sumToOptional() {
        List<Integer> numbers = asList(1, 2, 3, 4, 5);
        Optional<Integer> result = numbers.stream()
                .reduce(Integer::sum);
        int sum = result.orElse(0);
        System.out.println("Sum: " + sum);
    }

    private static void sumToEmptyOptional() {
        List<Integer> numbers = Collections.emptyList();
        Optional<Integer> result = numbers.stream()
                .reduce(Integer::sum);
        int sum = result.orElse(0);
        System.out.println("Sum: " + sum);
    }

    private static void sumToInt() {
        List<Integer> numbers = asList(1, 2, 3, 4, 5);
        int sum = numbers.stream().reduce(0, (a, b) -> {
            System.out.printf("summing(%s, %s)%n", a, b);
            return Integer.sum(a, b);
        });
        System.out.println("Sum: " + sum);
    }

    static final BinaryOperator<Integer> COMBINER = (a, b) -> {
        System.out.printf("combining(%s, %s)%n", a, b);
        return Integer.sum(a, b);
    };

    private static void reduceToNoCombiner() {
        List<Integer> numbers = asList(1, 2, 3, 4, 5);
        int sum = numbers.stream()
                // init, accumulator, combiner
                .reduce(0, Integer::sum, COMBINER);
        System.out.println("Sum: " + sum);
    }

    private static void reduceToCombiner() {
        List<Integer> numbers = asList(1, 2, 3, 4, 5);
        int sum = numbers.parallelStream()
                // init, accumulator, combiner
                .reduce(0, Integer::sum, COMBINER);
        System.out.println("Sum: " + sum);
    }
}
