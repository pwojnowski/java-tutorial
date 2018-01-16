package com.farenda.java.lang;

public class ThreadExample {

    private static class Counter extends Thread {

        private static int threads = 0;

        private int id = ++threads;
        private final int number;

        public Counter(int number) {
            this.number = number;
        }

        @Override
        public void run() {
            for (int i = 0; i < number; ++i) {
                System.out.printf("counter %d, value: %d%n", id, i);
            }
        }
    }

    public static void main(String[] args) {
        Counter counter1 = new Counter(5);
        Counter counter2 = new Counter(5);

        System.out.println("Starting counters:");
        counter1.start();
        counter2.start();
        System.out.println("main thread ends here");
    }
}
