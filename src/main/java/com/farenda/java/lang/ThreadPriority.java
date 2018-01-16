package com.farenda.java.lang;

public class ThreadPriority {

    private static class Counter extends Thread {

        private static int threads = 0;
        private int id = ++threads;
        private int loops = 100_000;
        private long startMillis;

        public Counter(long startMillis) {
            this.startMillis = startMillis;
        }

        @Override
        public void run() {
            if (id == 10) {
                setPriority(MAX_PRIORITY);
            } else {
                setPriority(MIN_PRIORITY);
            }

            while (loops-- >= 0) {
                process(loops);
                Thread.yield();
            }

            System.out.printf("Thread %d (priority %d) done after: %d%n",
                    id, currentThread().getPriority(),
                    System.currentTimeMillis() - startMillis);
        }

        private long process(int x) {
            int sum = 0;
            for (int i = 0; i < x; ++i) {
                sum += i * Math.PI;
            }
            return sum;
        }
    }

    public static void main(String[] args) {
        long startMillis = System.currentTimeMillis();
        Counter[] counters = {
                new Counter(startMillis),
                new Counter(startMillis),
                new Counter(startMillis),
                new Counter(startMillis),
                new Counter(startMillis),
                new Counter(startMillis),
                new Counter(startMillis),
                new Counter(startMillis),
                new Counter(startMillis),
                new Counter(startMillis)
        };

        System.out.println("Starting counters:");
        for (Counter c : counters) {
            c.start();
        }

        System.out.println("Main thread done after: " +
                (System.currentTimeMillis() - startMillis));
    }
}
