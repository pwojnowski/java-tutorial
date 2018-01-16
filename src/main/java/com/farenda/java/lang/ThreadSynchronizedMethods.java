package com.farenda.java.lang;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadSynchronizedMethods {

    private static class Counter {
        private int number;

        public Counter(int number) {
            this.number = number;
        }

        public boolean dec() {
            synchronized(this) {
                if (number > 0) {
                    Thread.yield();
                    --number;
                    return true;
                }
                return false;
            }
        }
    }

    private static class Worker implements Runnable {

        private static int nth = 0;

        private final int id = ++nth;
        private Counter counter;

        public Worker(Counter counter) {
            this.counter = counter;
        }

        @Override
        public void run() {
            System.out.println("Starting worker: " + id);
            while (counter.dec()) {
                System.out.printf("Decreased to %2d by worker: %2d%n",
                        counter.number, id);
            }
        }
    }

    public static void main(String[] args) {
        Counter counter = new Counter(10);
        ExecutorService executor = Executors.newCachedThreadPool();
        for (int i = 1; i <= 10; ++i) {
            executor.execute(new Worker(counter));
        }
        executor.shutdown();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            //ignore
        }

        System.out.println("Number actually is: " + counter.number);
    }
}
