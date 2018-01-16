package com.farenda.java.util.concurrent.locks;

public class ConditionExample {

    private final Object lock = new Object();

    public void m() {
        synchronized (lock) {
            System.out.println("Applied to instance field!");
        }
    }

    public static void main(String[] args) {

    }
}
