package com.farenda.java.util.stream;

import java.util.HashMap;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

public class FrequencyOfChars {

    public static void main(String[] args) {
        String s = "abcaba";
        forEachWithMapMerge(s);
        toMapCollector(s);
    }

    private static void forEachWithMapMerge(String s) {
        Map<Character, Integer> freqs = new HashMap<>();
        for (char c : s.toCharArray()) {
            freqs.merge(c, 1, Integer::sum);
        }
        System.out.println("Frequencies:\n" + freqs);
    }

    private static void toMapCollector(String s) {
        Map<Character, Integer> frequencies = s.chars().boxed()
                .collect(toMap(
                        k -> Character.valueOf((char) k.intValue()),
                        v -> 1,
                        Integer::sum));
        System.out.println("Frequencies:\n" + frequencies);
    }
}