package com.farenda.java.util.concurrent;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class CallableExample {

    private static class ComputingTask implements Callable<Integer> {

        private static int nth = 0;
        private final int id = ++nth;

        @Override
        public Integer call() throws Exception {
            int value = new Random().nextInt(1000);
            try {
                System.out.printf("Task %d started computing...%n", id);
                MILLISECONDS.sleep(value);
            } catch (InterruptedException e) {
                // ignore interruptions
            }
            System.out.printf("Task %d is returning value: %d%n",
                    id, value);
            return value;
        }
    }

    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();

        System.out.println("Submitting tasks for execution:");
        List<Future<Integer>> results = new LinkedList<>();
        for (int i = 0; i < 5; ++i) {
            results.add(executor.submit(new ComputingTask()));
        }

        System.out.println("Getting results from futures:");
        for (Future<Integer> result : results) {
            try {
                System.out.printf("computed result: %d%n", result.get());
            } catch (InterruptedException e) {
                System.out.println("Interrupted while waiting for result: "
                        + e.getMessage());
            } catch (ExecutionException e) {
                System.out.println("A task ended up with an exception: "
                        + e.getCause());
            }
        }

        System.out.println("Shutting down the executor.");
        executor.shutdown();
    }
}
