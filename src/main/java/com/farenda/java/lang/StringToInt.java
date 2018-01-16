package com.farenda.java.lang;

public class StringToInt {

    public static void main(String[] args) {
        stringToInt();
        numberFormatException();
        valueOf();

        decodeString();
    }

    private static void decodeString() {
        System.out.println("From dec: " + Integer.decode("42"));
        System.out.println("From oct: " + Integer.decode("052"));
        System.out.println("From hex: " + Integer.decode("0x2a"));
    }

    private static void stringToInt() {
        int x = Integer.parseInt("42");
        System.out.println("int is: " + x);
    }

    private static void numberFormatException() {
        try {
            int x = Integer.parseInt("aoeu");
            System.out.println("Number is: " + x);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    private static void valueOf() {
        Integer x = Integer.valueOf("42");
        System.out.println("Integer is: " + x);

        try {
            Integer.valueOf("xyz");
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }
}
