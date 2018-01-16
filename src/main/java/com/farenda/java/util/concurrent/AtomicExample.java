package com.farenda.java.util.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicExample {

    private static class Counter {

        private final AtomicInteger number;

        public Counter(int number) {
            this.number = new AtomicInteger(number);
        }

        /**
         * @return true if still can decrease
         */
        public boolean dec() {
            // updateAndGet(fn) executed atomically:
            return number.updateAndGet(n -> (n > 0) ? n - 1 : n) > 0;
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
                System.out.printf("Worker %2d saw value: %2d%n",
                        id, counter.number.intValue());
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
