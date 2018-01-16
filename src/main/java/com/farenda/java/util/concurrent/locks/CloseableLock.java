package com.farenda.java.util.concurrent.locks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CloseableLock implements AutoCloseable {

    private final Lock lock;

    CloseableLock(Lock lock) { this.lock = lock; }

    public static CloseableLock of(Lock lock) {
        CloseableLock closeable = new CloseableLock(lock);
        System.out.println("Acquiring lock.");
        lock.lock();
        return closeable;
    }

    @Override
    public void close() {
        System.out.println("Releasing lock.");
        lock.unlock(); }

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();

        try (CloseableLock lockable = CloseableLock.of(lock)) {
            System.out.println("Access protected resource.");
        }
    }
}
