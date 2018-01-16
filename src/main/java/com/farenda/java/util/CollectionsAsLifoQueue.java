package com.farenda.java.util;

import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class CollectionsAsLifoQueue {

    public static void main(String[] args) {
        // Deque is LILO (Last In Last Out)
        Deque<String> items = new LinkedList<>();

        // In this Deque items are added at the end:
        items.offer("a");
        items.offer("b");
        System.out.println("Added to Deque: " + items);

        // In this Deque items are removed from the front:
        items.poll();
        System.out.println("Removed from Deque: " + items);

        // View this Deque as a LIFO Queue, which is a Stack
        Queue<String> stack = Collections.asLifoQueue(items);

        // In Stack items are added at the front:
        stack.offer("c");
        System.out.println("Added to Stack: " + items);

        // And removed from the front:
        stack.poll();
        System.out.println("Removed from Stack: " + items);
    }
}
