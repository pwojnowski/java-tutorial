package com.farenda.java.util.concurrent;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.*;

public class AutoCloseableExecutorService implements AutoCloseable, ExecutorService {

    private ExecutorService delegate;

    public AutoCloseableExecutorService(ExecutorService delegate) {
        Objects.requireNonNull(delegate, "ExecutorService must not be null!");
        this.delegate = delegate;
    }

    @Override
    public void shutdown() {
        delegate.shutdown();
    }

    @Override
    public List<Runnable> shutdownNow() {
        return delegate.shutdownNow();
    }

    @Override
    public boolean isShutdown() {
        return delegate.isShutdown();
    }

    @Override
    public boolean isTerminated() {
        return delegate.isTerminated();
    }

    @Override
    public boolean awaitTermination(long timeout, TimeUnit unit)
            throws InterruptedException {
        return delegate.awaitTermination(timeout, unit);
    }

    @Override
    public <T> Future<T> submit(Callable<T> task) {
        return delegate.submit(task);
    }

    @Override
    public <T> Future<T> submit(Runnable task, T result) {
        return delegate.submit(task, result);
    }

    @Override
    public Future<?> submit(Runnable task) {
        return delegate.submit(task);
    }

    @Override
    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks)
            throws InterruptedException {
        return delegate.invokeAll(tasks);
    }

    @Override
    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks,
                                         long timeout, TimeUnit unit)
            throws InterruptedException {
        return delegate.invokeAll(tasks, timeout, unit);
    }

    @Override
    public <T> T invokeAny(Collection<? extends Callable<T>> tasks)
            throws InterruptedException, ExecutionException {
        return delegate.invokeAny(tasks);
    }

    @Override
    public <T> T invokeAny(Collection<? extends Callable<T>> tasks,
                           long timeout, TimeUnit unit)
            throws InterruptedException, ExecutionException, TimeoutException {
        return delegate.invokeAny(tasks, timeout, unit);
    }

    @Override
    public void execute(Runnable command) {
        delegate.execute(command);
    }

    @Override
    public void close() {
        System.out.println("Shutting down executor service.");
        delegate.shutdown();
    }

    // helper method to ease construction
    private static AutoCloseableExecutorService newCached() {
        System.out.println("Creating a new CachedThreadPool executor.");
        return new AutoCloseableExecutorService(Executors.newCachedThreadPool());
    }

    public static void main(String[] args) {
        System.out.println("Start of program.");
        try (AutoCloseableExecutorService executor = newCached()) {
            executor.execute(() -> System.out.println("Running a task!"));
        }
    }
}
