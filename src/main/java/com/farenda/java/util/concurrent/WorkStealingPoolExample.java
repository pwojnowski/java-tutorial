package com.farenda.java.util.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class WorkStealingPoolExample {

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
        System.out.println("Available processors: "
                + Runtime.getRuntime().availableProcessors());
        System.out.println("Starting Work Stealing Pool");
        ExecutorService executor = Executors.newWorkStealingPool();
        for (int i = 1; i <= 4; ++i) {
            executor.execute(new Counter(3));
        }
        executor.shutdown();
    }
}
