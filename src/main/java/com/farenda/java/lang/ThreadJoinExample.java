package com.farenda.java.lang;

import java.util.concurrent.TimeUnit;

public class ThreadJoinExample {

    private static class BusyJob extends Thread {
        @Override
        public void run() {
            System.out.println("Started BusyJob...");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                // ignore
            }
            System.out.println("Busy job done.");
        }
    }

    private static class JobCleaner extends Thread {

        private Thread job;

        public JobCleaner(Thread job) {
            this.job = job;
        }

        @Override
        public void run() {
            job.start();
            System.out.println("Cleaner is waiting on a busy job...");
            try {
                job.join();
                System.out.println("Cleaning up after job: " + job.getClass().getSimpleName());
            } catch (InterruptedException e) {
                System.out.println("Some other thread has interrupted me!");
            }
            System.out.println("Cleaning done.");
        }
    }

    public static void main(String[] args) {
        Thread busyJob = new BusyJob();
        Thread cleaner = new JobCleaner(busyJob);

        System.out.println("Starting the cleaner.");
        cleaner.start();

        System.out.println(Thread.currentThread().getName()
                + " is waiting for the cleaner to finish");
        try {
            cleaner.join();
        } catch (InterruptedException e) {
            System.out.println("main thread interrupted!");
        }

        System.out.println("End of main thread!");
    }
}
