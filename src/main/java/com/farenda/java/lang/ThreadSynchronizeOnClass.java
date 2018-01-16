package com.farenda.java.lang;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadSynchronizeOnClass {

    private static int number;

    public static synchronized boolean dec() {
        if (number > 0) {
            Thread.yield();
            --number;
            return true;
        }
        return false;
    }

    private static class Worker implements Runnable {

        private static int nth = 0;

        private final int id = ++nth;

        @Override
        public void run() {
            System.out.println("Starting worker: " + id);
            while (dec()) {
                System.out.printf("Decreased to %2d by worker: %2d%n",
                        ThreadSynchronizeOnClass.number, id);
            }
        }
    }

    public static void main(String[] args) {
        ThreadSynchronizeOnClass.number = 10;
        ExecutorService executor = Executors.newCachedThreadPool();
        for (int i = 1; i <= 10; ++i) {
            executor.execute(new Worker());
        }
        executor.shutdown();

        try {
            executor.awaitTermination(2, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            //ignore
        }

        System.out.println("Number actually is: " + ThreadSynchronizeOnClass.number);
    }
}
