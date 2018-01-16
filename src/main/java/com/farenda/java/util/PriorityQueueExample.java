package com.farenda.java.util;

import java.util.PriorityQueue;
import java.util.Random;

public class PriorityQueueExample {

    public static void main(String[] args) {
        PriorityQueueExample example = new PriorityQueueExample();
        example.sortNumbers();
        example.processUsers();
    }

    private void sortNumbers() {
        PriorityQueue<Integer> queue = new PriorityQueue<>();

        Random rand = new Random();
        for (int i = 0; i < 10; ++i) {
            queue.add(rand.nextInt(100));
        }

        while (!queue.isEmpty()) {
            System.out.printf("%d,", queue.remove());
        }
        System.out.println();
    }

    private void processUsers() {
        PriorityQueue<User> queue = new PriorityQueue<>(new NameComparator());

        queue.add(new User("Jon Snow"));
        queue.add(new User("Cersei"));
        queue.add(new User("Littlefinger"));
        queue.add(new User("Daenerys"));

        while (!queue.isEmpty()) {
            System.out.printf("Handling: %s%n", queue.remove());
        }
    }
}
