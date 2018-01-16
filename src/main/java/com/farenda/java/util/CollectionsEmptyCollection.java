package com.farenda.java.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class SampleService {

    public List<Integer> retrieveData(String someParam) {
        // do processing only when have valid parameter:
        if (validate(someParam)) {
            // return default value with generic type inferred:
            return Collections.emptyList();
        }
        return Arrays.asList(1, 1, 2, 3, 5, 8);
    }

    private boolean validate(String someParam) {
        return someParam == null;
    }
}

public class CollectionsEmptyCollection {

    public static void main(String[] args) {
        SampleService service = new SampleService();

        List<Integer> data = service.retrieveData("42");
        display(data);

        data = service.retrieveData(null);
        display(data);

        System.out.println("Adding to unmodifiable:");
        data.add(1);
    }

    private static void display(List<Integer> data) {
        System.out.println("Loaded data: " + data);
        // Clean code without null checks!
        for (Integer i : data) {
            System.out.println(i);
        }
    }
}
