package com.farenda.java.lang;

public class RunnableExample {

    private static class Counter implements Runnable {

        private final int number;

        public Counter(int number) {
            this.number = number;
        }

        @Override
        public void run() {
            String threadName = Thread.currentThread().getName();
            System.out.println("Worker's thread: " + threadName);
            for (int i = 0; i < number; ++i) {
                System.out.println("counter's value: " + i);
            }
        }
    }

    public static void main(String[] args) {
        final String mainThreadName = Thread.currentThread().getName();
        System.out.println("Main thread: " + mainThreadName);

        Runnable counter = new Counter(2);

        System.out.println("\nRunning Runnable.run() without Thread:");
        counter.run();

        Thread counterThread = new Thread(counter, "counter-thread");
        System.out.println("\nRunning Thread.run() without starting the Thread:");
        counterThread.run();

        // Thread.start() will call Runnable.run()
        System.out.println("\nRunning using Thread.start():");
        counterThread.start();

        System.out.println("\nEnd of main()");
    }
}
