package com.farenda.java.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class MapExamples {

    public static void main(String[] args) {
        //compute();
        //computeIfAbsent();
        //computeIfPresent();
        //forEach();
        //getOrDefault();
        //merge();
        //putIfAbsent();
        //remove();
        //replaceKeyValue();
        //replaceOldNew();
        //replaceAll();
    }

    private static void getOrDefault() {
        Map<Integer,String> vilians = new HashMap<>();
        vilians.put(1, "Kajko");
        System.out.println("Original map: " + vilians);

        // pre Java 8 version:
        String name;
        if (vilians.containsKey(2)) {
            name = vilians.get(2);
        } else {
            name = "Kokosz";
        }
        System.out.println("Name: " + name);

        // Java 8 version:
        name = vilians.getOrDefault(2, "Kokosz");
        System.out.println("Name: " + name);
    }

    private static void compute() {
        Map<Integer,String> nameForId = new HashMap<>();
        nameForId.put(1, "Java");
        nameForId.put(2, "Clojure");
        System.out.println("Original map: " + nameForId);

        // recompute the values:
        nameForId.compute(1, (key, oldVal) -> oldVal.concat("Script"));
        nameForId.compute(2, (key, oldVal) -> oldVal.concat("Script"));
        System.out.println("Recomputed map: " + nameForId);

        // return "null" to remove value:
        nameForId.compute(1, (key, oldVal) -> null);
        // null for nothing does nothing:
        nameForId.compute(3, (key, oldVal) -> null);
        System.out.println("After null: " + nameForId);

        // unchecked exceptions are rethrown:
        try {
            nameForId.compute(1, (key, oldVal) -> {
                throw new RuntimeException("Drakaris!");
            });
        } catch (RuntimeException e) {
            System.out.println("Rethrown: " + e.getMessage());
        }
        System.out.println("Map after exception: " + nameForId);
    }

    private static void computeIfAbsent() {
        Map<Integer,String> nameForId = new HashMap<>();
        nameForId.put(1, "Java");
        nameForId.put(2, null);
        System.out.println("Original map: " + nameForId);

        nameForId.computeIfAbsent(1, key -> "Java at " + key);
        System.out.println("No changes: " + nameForId);

        nameForId.computeIfAbsent(2, key -> "Esperanto at " + key);
        System.out.println("Null updated: " + nameForId);

        nameForId.computeIfAbsent(3, key -> "Clojure at "+ key);
        System.out.println("New key: " + nameForId);

        // Multimap example:
        Map<String,Collection<String>> multimap = new HashMap<>();
        multimap.computeIfAbsent("names", key -> new ArrayList<>())
                .add("Kajko");
        System.out.println("multimap: " + multimap);

        multimap.computeIfAbsent("names", key -> new ArrayList<>())
                .add("Kokosz");
        System.out.println("multimap: " + multimap);
    }

    private static void computeIfPresent() {
        Map<Integer,String> nameForId = new HashMap<>();

        // Adding new value:
        nameForId.computeIfPresent(1, (key, oldVal) -> {
            System.out.printf("BiFunction(%d,%s) for new%n",
                    key, oldVal);
            return "Java";
        });
        System.out.println("After add new: " + nameForId);

        // Updating:
        nameForId.put(1, "Java");
        nameForId.computeIfPresent(1, (key, oldVal) -> {
            System.out.printf("BiFunction(%d,%s) update%n",
                    key, oldVal);
            return oldVal.concat("Script");
        });
        System.out.println("After update: " + nameForId);

        // null removes:
        nameForId.computeIfPresent(1, (key, oldVal) -> {
            System.out.printf("BiFunction(%d,%s) -> null%n",
                    key, oldVal);
            return null;
        });
        System.out.println("After null: " + nameForId);
    }

    private static void forEach() {
        Map<Integer,String> nameForId = new HashMap<>();
        nameForId.put(1, "Kajko");
        nameForId.put(2, "Kokosz");
        System.out.println("Original map: " + nameForId);

        System.out.println("Pre Java 8 for-each:");
        for (Entry<Integer,String> entry : nameForId.entrySet()) {
            System.out.printf("key: %d, value: %s%n",
                    entry.getKey(), entry.getValue());
        }

        System.out.println("Java 8 forEach:");
        nameForId.forEach((key,value) ->
                System.out.printf("key: %d, value: %s%n",
                        key, value));
    }

    private static void merge() {
        Map<String, Integer> prices = new HashMap<>();
        System.out.println("Prices map: " + prices);

        // Integer::sum(a,b) is perfect two-argument
        // function (BiFunction)
        prices.merge("fruits", 3, Integer::sum);
        prices.merge("fruits", 5, Integer::sum);
        System.out.println("Prices map: " + prices);

        // null removes mapping for the key:
        prices.merge("fruits", 7, (oldVal, newVal) -> {
            System.out.printf("Old val: %d, new val: %d%n",
                    oldVal, newVal);
            return null;
        });
        System.out.println("Prices map: " + prices);

        prices.put("veggies", null);
        System.out.println("Prices map: " + prices);
        // No need to handle initial null value:
        prices.merge("veggies", 42, Integer::sum);
        System.out.println("Prices map: " + prices);
    }

    private static void putIfAbsent() {
        Map<Integer,String> nameForId = new HashMap<>();
        nameForId.put(1, "Kajko");
        nameForId.put(2, null);
        System.out.println("Original map: " + nameForId);

        String previous = nameForId.putIfAbsent(1, "abc");
        System.out.println("Previous value: " + previous);
        System.out.println("No changes: " + nameForId);

        nameForId.putIfAbsent(2, "Kokosz");
        System.out.println("Null updated: " + nameForId);

        nameForId.putIfAbsent(3, "Jagna");
        System.out.println("New key: " + nameForId);
    }

    private static void remove() {
        Map<Integer,String> nameForId = new HashMap<>();
        nameForId.put(1, "Java");
        System.out.println("Original map: " + nameForId);

        nameForId.remove(1, "Clojure");
        System.out.println("Not removed: " + nameForId);

        nameForId.remove(1, "Java");
        System.out.println("Removed: " + nameForId);
    }

    private static void replaceKeyValue() {
        Map<Integer,String> languages = new HashMap<>();
        languages.put(1, "Java");
        System.out.println("Original map: " + languages);

        String oldval = languages.replace(2, "Clojure");
        System.out.println("Old value: " + oldval);
        System.out.println("No changes: " + languages);

        oldval = languages.replace(1, "Clojure");
        System.out.println("Old value: " + oldval);
        System.out.println("Current map: " + languages);
    }

    private static void replaceOldNew() {
        Map<Integer,String> languages = new HashMap<>();
        languages.put(1, "Java");
        System.out.println("Original map: " + languages);

        boolean replaced = languages.replace(1, "Clojure", "Python");
        System.out.printf("Replaced: %b, the map: %s%n",
                replaced, languages);

        replaced = languages.replace(1, "Java", "Python");
        System.out.printf("Replaced: %b, the map: %s%n",
                replaced, languages);
    }

    private static void replaceAll() {
        Map<Integer,Integer> numbers = new HashMap<>();
        for (int i = 1; i <= 10; ++i) {
            numbers.put(i, i);
        }
        System.out.println("Original: " + numbers);

        numbers.replaceAll((key, oldValue) -> oldValue * oldValue);
        System.out.println("Squared: " + numbers);
    }
}
