package com.farenda.java.lang;

import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

public class SplitStringExample {

    public static void main(String[] args) {
        String s = "Stefan Banach";

        splitPositiveLimit(s);
        splitNegativeLimit(s);
        splitZeroLimit(s);
        splitNoLimit(s);

        splitUsingPattern(s);
        splitStringTokenizer(s);
    }

    private static void splitPositiveLimit(String s) {
        // apply n-1 times = 3-1 = 2
        System.out.print("s.split(\"a\", 3): ");
        // array will be not greater than 3:
        System.out.println(Arrays.toString(s.split("a", 3)));
    }

    private static void splitNegativeLimit(String s) {
        System.out.print("s.split(\"a\", -1): ");
        System.out.println(Arrays.toString(s.split("a", -1)));

        // non-positive = apply as many times as possible:
        System.out.print("s.split(\"h\", -1): ");
        // Notice empty trailing string:
        System.out.println(Arrays.toString(s.split("h", -1)));
    }

    private static void splitZeroLimit(String s) {
        // 0 = apply as many times as possible and
        // remove trailing empty strings:
        System.out.print("s.split(\"h\", 0): ");
        System.out.println(Arrays.toString(s.split("h", 0)));
    }

    private static void splitNoLimit(String s) {
        // the same as "limit" = 0:
        System.out.print("s.split(\"h\"): ");
        System.out.println(Arrays.toString(s.split("h")));
    }

    private static void splitUsingPattern(String s) {
        Pattern pattern = Pattern.compile("h");
        System.out.print("Pattern.compile(\"h\").split(s): ");
        System.out.println(Arrays.toString(pattern.split(s)));
    }

    private static void splitStringTokenizer(String s) {
        // the same as s.split("h")
        StringTokenizer tokenizer = new StringTokenizer(s, "h");
        System.out.print("StringTokenizer(s,\"h\"): ");
        System.out.println(Collections.list(tokenizer));
        // or using Enumeration interface:
        // while (tokenizer.hasMoreTokens()) {
        //     System.out.println(tokenizer.nextToken());
        // }
    }
}
