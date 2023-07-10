package com.farenda.java.util.concurrent.locks;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockExample {

    private static class Counter {
        private final ReentrantLock lock = new ReentrantLock();
        private int number;

        public Counter(int number) {
            this.number = number;
        }

        public boolean dec() {
            lock.lock();
            try {
                if (number > 0) {
                    Thread.yield();
                    --number;
                    return true;
                }
                return false;
            } finally {
                lock.unlock();
            }
        }
    }

    private static class Worker implements Runnable {

        private static int nth = 0;

        private final int id = ++nth;
        private final Counter counter;

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
