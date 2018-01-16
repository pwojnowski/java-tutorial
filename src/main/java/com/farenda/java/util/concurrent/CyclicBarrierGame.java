package com.farenda.java.util.concurrent;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrierGame {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();

        // Action to run when all Threads will hit the barrier:
        Arbiter arbiter = new Arbiter(executor, 10);

        // Number of threads to wait for:
        int numberOfPlayers = 2;

        CyclicBarrier barrier = new CyclicBarrier(numberOfPlayers, arbiter);

        Player player1 = new Player(barrier, "Daenerys");
        Player player2 = new Player(barrier, "Night's King");

        arbiter.setPlayers(player1, player2);

        System.out.printf("Starting the game: %s vs %s%n",
                player1.name, player2.name);
        executor.execute(player1);
        executor.execute(player2);
    }
}
