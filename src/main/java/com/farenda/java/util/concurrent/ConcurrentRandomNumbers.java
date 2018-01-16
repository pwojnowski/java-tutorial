package com.farenda.java.util.concurrent;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadLocalRandom;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

public class ConcurrentRandomNumbers {

    private static class ConcurrentRange implements Callable<List<Integer>> {

        private ConcurrentRandomNumbers generator;
        private final int from;
        private final int howMany;

        public ConcurrentRange(ConcurrentRandomNumbers generator, int from, int howMany) {
            this.generator = generator;
            this.from = from;
            this.howMany = howMany;
        }

        @Override
        public List<Integer> call() throws Exception {
            return generator.randomRange(from, howMany);
        }
    }

    private List<Integer> randomRange(int from, int howMany) {
        return ThreadLocalRandom.current()
                .ints(howMany, from, from+howMany)
                .boxed()
                .collect(toList());
    }

    public static void main(String[] args) throws Exception {
        ConcurrentRandomNumbers generator = new ConcurrentRandomNumbers();

        ExecutorService es = Executors.newCachedThreadPool();

        List<ConcurrentRange> randomRanges = asList(
                new ConcurrentRange(generator, 10, 10),
                new ConcurrentRange(generator, 20, 10),
                new ConcurrentRange(generator, 30, 10));
        List<Future<List<Integer>>> results = es.invokeAll(randomRanges);

        for (Future<List<Integer>> future : results) {
            System.out.println("Range: " + future.get());
        }

        es.shutdown();
    }
}
