package com.farenda.java.util.concurrent;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorServiceExample {

    private static class Counter implements Runnable {

        private static int nth = 0;

        private final int id = ++nth;
        private final int number;

        public Counter(int number) {
            this.number = number;
        }

        @Override
        public void run() {
            for (int i = 0; i < number; ++i) {
                try {
                    TimeUnit.MILLISECONDS.sleep(300);
                } catch (InterruptedException e) {
                    // ignore
                }
                System.out.printf("counter %d, value: %d%n", id, i);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Starting ExecutorService");
        ExecutorService executor = Executors.newSingleThreadExecutor();
        for (int i = 1; i <= 5; ++i) {
            executor.execute(new Counter(i));
        }
        System.out.println("Shutting down the executor");
        // Comment out to hang the app:
        //executor.shutdown();
        List<Runnable> notStarted = executor.shutdownNow();
        System.out.println("Number of not started: " + notStarted.size());
        System.out.println("The main thread ends now.");
    }
}
