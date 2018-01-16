package com.farenda.java.lang;

import java.util.concurrent.TimeUnit;

public class ThreadDaemon {

    private static class SampleThread extends Thread {
        private final int id;

        public SampleThread(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            System.out.println("Running thread " + id);
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                System.out.println("Interrupted!");
            } finally {
                System.out.println("Finally in thread " + id);
            }
            System.out.println("Stopping thread " + id);
        }
    }

    public static void main(String[] args) {
        System.out.println("Starting threads:");
        for (int i = 1; i <= 5; ++i) {
            Thread t = new SampleThread(i);
            t.setDaemon(true);
            t.start();
        }
        System.out.println("Stopping program");
    }
}
