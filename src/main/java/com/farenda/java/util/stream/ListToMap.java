package com.farenda.java.util.stream;

import com.farenda.java.util.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toMap;

public class ListToMap {

    public static void main(String[] args) {
        preJava8Mapping();

        java8Mapping();
    }

    private static void preJava8Mapping() {
        List<User> users = asList(new User("Foo"), new User("Bar"));
        Map<String, User> usersByName = new HashMap<>();
        for (User user : users) {
            usersByName.put(user.getName(), user);
        }
        System.out.println("Mapping: " + usersByName);
    }

    private static void java8Mapping() {
        List<User> users = asList(new User("Foo"), new User("Bar"));
        Map<String, User> usersByName = users.stream()
                .collect(toMap(User::getName, Function.identity()));
        System.out.println("Mapping: " + usersByName);
    }
}
