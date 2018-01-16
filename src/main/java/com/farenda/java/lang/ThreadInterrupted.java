package com.farenda.java.lang;

import java.util.concurrent.TimeUnit;

public class ThreadInterrupted {

    static class Waiter extends Thread {
        private Object lock = new Object();
        @Override
        public void run() {
            try {
                System.out.println("Waiter running...");
                synchronized (lock) {
                    lock.wait();
                }
            } catch (InterruptedException e) {
                System.out.println("Waiter thread interrupted!");
                e.printStackTrace();
            }
        }
    }

    static class Cleaner extends Thread {
        private final Thread other;

        public Cleaner(Thread other) {
            this.other = other;
        }

        @Override
        public void run() {
            try {
                System.out.println("Cleaner running...");
                other.join();
                System.out.println("Not waiting...");
            } catch (InterruptedException e) {
                System.out.println("Cleaner thread interrupted!");
                e.printStackTrace();
            }
        }
    }

    static class Sleeper extends Thread {
        @Override
        public void run() {
            try {
                System.out.println("Sleeper running...");
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                System.out.println("Sleeper thread interrupted!");
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        interruptWait();
        interruptJoin();
        interruptSleep();
    }

    private static void interruptWait() throws InterruptedException {
        Thread waiter = new Waiter();
        waiter.start();

        // give the waiter some time to lock itself
        TimeUnit.SECONDS.sleep(1);

        waiter.interrupt();
    }

    private static void interruptJoin() throws InterruptedException {
        Thread cleaner = new Cleaner(Thread.currentThread());
        cleaner.start();

        // give some time to call join()
        TimeUnit.MILLISECONDS.sleep(100);

        cleaner.interrupt();

        TimeUnit.MILLISECONDS.sleep(50);
    }

    private static void interruptSleep() throws InterruptedException {
        Thread sleeper = new Sleeper();
        sleeper.start();

        // give some time to call sleep()
        TimeUnit.MILLISECONDS.sleep(100);

        sleeper.interrupt();
    }
}
