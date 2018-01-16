package com.farenda.java.lang;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class IntToString {

    public static void main(String[] args) {
        concatWithString();
        usingToString();
        integerToString();
        intMapToStream();

        toOtherNumericBase();
    }

    private static void concatWithString() {
        int x = 42;
        String s = "" + x;
        System.out.printf("Number: '%s'%n", s);
    }

    private static void usingToString() {
        int x = 42;
        String s = Integer.toString(x);
        System.out.printf("Number: '%s'%n", s);
    }

    private static void intMapToStream() {
        List<String> numbers = IntStream.of(1, 2, 3)
                .mapToObj(Integer::toString)
                .collect(Collectors.toList());
        System.out.printf("Numbers: '%s'%n", numbers);
    }

    private static void integerToString() {
        Integer x = 42;
        String s = x.toString();
        System.out.printf("Number: '%s'%n", s);
    }

    private static void toOtherNumericBase() {
        int x = 42;
        System.out.println(Integer.toString(x, 3));
        System.out.println(Integer.toBinaryString(x));
        System.out.println(Integer.toOctalString(x));
        System.out.println(Integer.toHexString(x));
    }
}
