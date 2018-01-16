package com.farenda.java.util.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class FixedThreadPoolExecutorExample {

    private static class Counter implements Runnable {

        private static int nth = 0;

        private final int id = ++nth;
        private final int number;

        public Counter(int number) {
            this.number = number;
        }

        @Override
        public void run() {
            System.out.println("Starting counter: " + id);
            for (int i = 0; i < number; ++i) {
                try {
                    MILLISECONDS.sleep(300);
                } catch (InterruptedException e) {
                    // ignore
                }
                System.out.printf("counter %d, value: %d%n", id, i);
            }
            System.out.println("Finishing counter: " + id);
        }
    }

    public static void main(String[] args) {
        int nThreads = 2;
        System.out.printf("Starting Fixed Thread Pool of %d threads%n", nThreads);
        ExecutorService executor = Executors.newFixedThreadPool(nThreads);
        for (int i = 1; i <= 4; ++i) {
            executor.execute(new Counter(3));
        }
        executor.shutdown();
    }
}
