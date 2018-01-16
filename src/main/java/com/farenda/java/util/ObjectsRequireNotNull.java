package com.farenda.java.util;

import java.util.Objects;

public class ObjectsRequireNotNull {

    private static void theOldWay(String data) {
        System.out.println("\nOld, verbose way of checking arguments:");
        if (data == null) {
            throw new NullPointerException("'data' must not be null");
        }

        System.out.println("Processing data: " + data);
    }

    private static void theNewWay(String data) {
        System.out.println("\nThe new, Java 7 way of checking arguments:");
        Objects.requireNonNull(data, "'data' must not be null");

        System.out.println("Processing data: " + data);
    }

    public static void main(String[] args) {
        try {
            theOldWay("Hello world!");
            theOldWay(null);
        } catch (NullPointerException e) {
            System.out.println("Caught: " + e.getMessage());
        }

        try {
            theNewWay("Hello world!");
            theNewWay(null);
        } catch (NullPointerException e) {
            System.out.println("Caught: " + e.getMessage());
        }
    }
}
