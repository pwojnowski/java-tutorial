package com.farenda.java.lang;

public class IllegalMonitorStateExceptionExample {

    public static void main(final String[] args) {
        brokenWait();
        brokenNotify();

        fixedWait();
        fixedNotify();
    }

    private static void brokenNotify() {
        System.out.println("In broken notify...");
        Object lock = new Object();
        try {
            lock.notify();
        } catch (IllegalMonitorStateException e) {
            e.printStackTrace();
        }
    }

    private static void fixedNotify() {
        Object lock = new Object();
        synchronized (lock) {
            lock.notify();
        }
        System.out.println("Synchronized notify works!");
    }

    private static void brokenWait() {
        System.out.println("In broken wait...");
        Object lock = new Object();
        try {
            lock.wait();
        } catch (InterruptedException | IllegalMonitorStateException e) {
            e.printStackTrace();
        }
    }

    private static void fixedWait() {
        Object lock = new Object();
        try {
            synchronized (lock) {
                lock.wait(500);
            }
            System.out.println("Synchronized wait works!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
