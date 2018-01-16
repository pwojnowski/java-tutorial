package com.farenda.java.util.concurrent;

import java.util.concurrent.ThreadFactory;

import static java.lang.Thread.MAX_PRIORITY;
import static java.lang.Thread.MIN_PRIORITY;

public class ThreadFactoryExample {

    private static class EchoJob implements Runnable {
        private static int njobs = 0;
        private final int id = ++njobs;

        @Override
        public void run() {
            System.out.println("Running job: " + id);
        }
    }

    private static class PrioritizedDaemonThreadFactory implements ThreadFactory {
        private static final String INVALID_PRIORITY_MESSAGE
                = "Priority must be between " + MIN_PRIORITY
                + " and " + MAX_PRIORITY + ". Was: ";
        private final int priority;

        public PrioritizedDaemonThreadFactory(int priority) {
            validatePriority(priority);
            this.priority = priority;
        }

        private void validatePriority(int priority) {
            if (priority < MIN_PRIORITY || MAX_PRIORITY < priority) {
                throw new IllegalArgumentException(
                        INVALID_PRIORITY_MESSAGE + priority);
            }
        }

        @Override
        public Thread newThread(Runnable job) {
            System.out.println("Creating a new thread");
            Thread thread = new Thread(job);
            thread.setDaemon(true);
            thread.setPriority(priority);
            return thread;
        }
    }

    public static void main(String[] args) {
        System.out.println("Creating ThreadFactory with invalid priority:");
        try {
            new PrioritizedDaemonThreadFactory(0);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage() + "\n");
        }

        System.out.println("Creating ThreadFactory producing slow daemons");
        ThreadFactory slowDaemons = new PrioritizedDaemonThreadFactory(MIN_PRIORITY);

        runJobs(slowDaemons);

        System.out.println("End of program.");
    }

    private static void runJobs(ThreadFactory threadFactory) {
        System.out.println("Starting jobs:");
        for (int i = 0; i < 5; ++i) {
            threadFactory.newThread(new EchoJob()).start();
        }
    }
}
