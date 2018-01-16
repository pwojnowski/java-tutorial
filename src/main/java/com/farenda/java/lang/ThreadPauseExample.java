package com.farenda.java.lang;

import java.util.concurrent.TimeUnit;

public class ThreadPauseExample {

    public static void main(String[] args) {
        pauseUsingThreadSleep();

        pauseUsingThreadSleepWithNanos();

        pauseUsingTimeUnit();
    }

    private static void pauseUsingThreadSleep() {
        System.out.print("Pausing Thread.sleep(100)... ");
        long start = System.currentTimeMillis();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            // someone interrupted me!
        }
        long end = System.currentTimeMillis();
        System.out.printf("done after: %d ms%n", end - start);
    }

    private static void pauseUsingThreadSleepWithNanos() {
        System.out.print("Pausing Thread.sleep(0, 500000)... ");
        long start = System.currentTimeMillis();
        try {
            Thread.sleep(0, 500000);
        } catch (InterruptedException e) {
            // someone interrupted me!
        }
        long end = System.currentTimeMillis();
        System.out.printf("done after: %d ms%n", end - start);
    }

    private static void pauseUsingTimeUnit() {
        System.out.print("Pausing TimeUnit.MILLISECONDS.sleep(100)... ");
        long start = System.currentTimeMillis();
        try {
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException e) {
            // someone interrupted me!
        }
        long end = System.currentTimeMillis();
        System.out.printf("done after: %d ms%n", end - start);
    }
}
