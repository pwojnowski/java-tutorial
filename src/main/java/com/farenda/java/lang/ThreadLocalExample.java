package com.farenda.java.lang;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadLocalExample {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();
        executor.execute(new Worker("AAA"));
        executor.execute(new Worker("BBB"));
        executor.execute(new Worker("CCC"));
        executor.shutdown();
    }

    static class Worker implements Runnable {

        private final String customer;

        public Worker(String customer) {
            this.customer = customer;
        }

        @Override
        public void run() {
            CurrentCustomer.set(customer);
            System.out.printf("Thread %s, customer: %s%n",
                    Thread.currentThread().getName(),
                    CurrentCustomer.get());
            sleep();
            System.out.printf("%s processed customer: %s%n",
                    Thread.currentThread().getName(),
                    CurrentCustomer.get());
        }

        private void sleep() {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                // ignore
            }
        }
    }
}