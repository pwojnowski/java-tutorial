package com.farenda.java.util.concurrent;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

class Player implements Runnable {

    private static final Random RAND = new Random();
    private final CyclicBarrier roundBarrier;

    public final String name;
    public volatile Weapons weapon;
    public volatile boolean gameOver;

    public Player(CyclicBarrier roundBarrier, String name) {
        this.roundBarrier = roundBarrier;
        this.name = name;
    }

    @Override
    public void run() {
        try {
            while (!gameOver) {
                weapon = pickRandomly();
                roundBarrier.await();
            }
        } catch (InterruptedException e) {
            // this one is ok
        } catch (BrokenBarrierException e) {
            // Something funny has happened!
            throw new RuntimeException(e);
        }
    }

    private Weapons pickRandomly() {
        return Weapons.values()
                [RAND.nextInt(Weapons.values().length)];
    }
}
