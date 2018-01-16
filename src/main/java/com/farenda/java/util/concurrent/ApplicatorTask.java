package com.farenda.java.util.concurrent;

import java.util.List;
import java.util.concurrent.RecursiveAction;
import java.util.function.UnaryOperator;

class ApplicatorTask<T> extends RecursiveAction {

    private final UnaryOperator<T> function;
    private final int threshold;
    private final List<T> items;
    private final int from;
    private final int to;

    public ApplicatorTask(UnaryOperator<T> function,
                          int threshold,
                          List<T> items, int from, int to) {
        this.function = function;
        this.threshold = threshold;
        this.items = items;
        this.from = from;
        this.to = to;
    }

    @Override
    protected void compute() {
        if (to-from < threshold) {
            System.out.printf("Computing [%d,%d] in thread %s%n",
                    from, to, Thread.currentThread().getName());
            for (int i = from; i < to; ++i) {
                items.set(i, function.apply(items.get(i)));
            }
        } else {
            int mid = Math.floorDiv(from+to, 2);
            invokeAll(newTask(from, mid), newTask(mid+1, to));
        }
    }

    private ApplicatorTask<T> newTask(int start, int end) {
        return new ApplicatorTask<>(
                function, threshold, items, start, end);
    }
}
