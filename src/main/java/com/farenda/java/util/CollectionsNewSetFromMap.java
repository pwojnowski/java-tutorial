package com.farenda.java.util;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class CollectionsNewSetFromMap {

    public static void main(String[] args) {
        Set<String> users = Collections.newSetFromMap(
                new ConcurrentHashMap<String, Boolean>());

        System.out.println("Users: " + users);

        users.add("Jon");
        users.add("Tyron");

        System.out.println("Users: " + users);
    }
}
