package com.farenda.java.lang;

import com.farenda.java.lang.ThreadUncaughtExceptionHandlerExample.MyUncaughtExceptionHandler;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

public class ThreadUncaughtExceptionHandlerExecutorService {

    public static class ExceptionalTask implements Runnable {
        private static int ntasks = 0;
        private int taskId = ++ntasks;

        @Override
        public void run() {
            System.out.println("Starting work in thread: " + taskId);
            try {
                TimeUnit.MILLISECONDS.sleep(200);
            } catch (InterruptedException e) {
                //ignore
            }
            if ((taskId % 2) == 0) {
                throw new RuntimeException("An Exception that ends task: " + taskId);
            } else {
                System.out.printf("Task %d finished normally.%n", taskId);
            }
        }
    }

    private static class CaughtExceptionsThreadFactory implements ThreadFactory {
        private MyUncaughtExceptionHandler handler = new MyUncaughtExceptionHandler();

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r);
            t.setUncaughtExceptionHandler(handler);
            return t;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        CaughtExceptionsThreadFactory threadFactory = new CaughtExceptionsThreadFactory();
        ExecutorService executor = Executors.newCachedThreadPool(threadFactory);

        System.out.println("Executing tasks:");
        for (int i = 0; i < 5; ++i) {
            executor.execute(new ExceptionalTask());
        }

        System.out.println("Shutting down the executor.");
        executor.awaitTermination(2, TimeUnit.SECONDS);

        printResults(threadFactory.handler);
    }

    private static void printResults(MyUncaughtExceptionHandler handler) {
        System.out.println("\nCaught errors:");
        handler.getErrors().forEach(System.out::println);
    }
}
