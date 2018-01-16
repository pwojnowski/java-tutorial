package com.farenda.java.lang;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ThreadUncaughtExceptionHandlerExample {

    public static class MyUncaughtExceptionHandler
            implements Thread.UncaughtExceptionHandler {

        private List<String> errors = new LinkedList<>();

        public List<String> getErrors() {
            return errors;
        }

        @Override
        public void uncaughtException(Thread t, Throwable e) {
            String message = String.format("Thread %s hit by exception %s.",
                    t.getName(), e.toString());
            System.out.println(message);
            errors.add(message);
        }
    }

    public static class ExceptionalThread extends Thread {
        @Override
        public void run() {
            System.out.println("Starting work in thread: " + getId());
            try {
                TimeUnit.MILLISECONDS.sleep(200);
            } catch (InterruptedException e) {
                //ignore
            }
            if ((getId() % 2) == 0) {
                throw new RuntimeException("An Exception that ends the thread: "
                        + getId());
            } else {
                System.out.printf("Thread %d finished normally.%n", getId());
            }
        }
    }

    public static void main(String[] args) {
        MyUncaughtExceptionHandler handler = new MyUncaughtExceptionHandler();

        startThreadsWithPerThreadHandler(handler);
        printResults(handler);

        System.out.println("\nClearing errors in handler.\n");
        handler.errors.clear();

        startThreadsWithDefaultHandler(handler);
        printResults(handler);
    }

    private static void startThreadsWithDefaultHandler(
            MyUncaughtExceptionHandler handler) {
        System.out.println("Setting default uncaught exception handler.");
        Thread.setDefaultUncaughtExceptionHandler(handler);
        System.out.println("Starting threads");
        for (int i = 0; i < 5; ++i) {
            new ExceptionalThread().start();
        }
    }

    private static void startThreadsWithPerThreadHandler(
            MyUncaughtExceptionHandler handler) {
        System.out.println("Starting threads with per thread uncaught exception handler");
        for (int i = 0; i < 5; ++i) {
            ExceptionalThread command = new ExceptionalThread();
            command.setUncaughtExceptionHandler(handler);
            command.start();
        }
    }

    private static void printResults(MyUncaughtExceptionHandler handler) {
        try {
            System.out.println("Waiting a bit for threads to finish...");
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            // ignore
        }

        System.out.println("\nCaught errors:");
        handler.errors.forEach(System.out::println);
    }
}
