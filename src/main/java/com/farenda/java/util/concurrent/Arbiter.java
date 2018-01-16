package com.farenda.java.util.concurrent;

import java.util.EnumMap;
import java.util.concurrent.ExecutorService;

import static com.farenda.java.util.concurrent.Weapons.Fire;
import static com.farenda.java.util.concurrent.Weapons.Ice;
import static com.farenda.java.util.concurrent.Weapons.Water;

class Arbiter implements Runnable {

    // Table that decides which weapon wins/draws/loses
    // to other kinds of weapon:
    private static EnumMap<Weapons,EnumMap<Weapons,Integer>>
            SCORING_TABLE = new EnumMap<>(Weapons.class);

    static {
        EnumMap<Weapons, Integer> scores
                = new EnumMap<>(Weapons.class);
        scores.put(Ice, -1);
        scores.put(Fire, 0);
        scores.put(Water, 1);
        SCORING_TABLE.put(Fire, scores);

        scores = new EnumMap<>(Weapons.class);
        scores.put(Water, -1);
        scores.put(Ice, 0);
        scores.put(Fire, 1);
        SCORING_TABLE.put(Ice, scores);

        scores = new EnumMap<>(Weapons.class);
        scores.put(Fire, -1);
        scores.put(Water, 0);
        scores.put(Ice, 1);
        SCORING_TABLE.put(Water, scores);
    }

    private final ExecutorService executor;
    private final int maxGames;
    private Player player1;
    private Player player2;
    private int player1Wins;
    private int player2Wins;
    private int draws;

    public Arbiter(ExecutorService executor, int maxGames) {
        this.executor = executor;
        this.maxGames = maxGames;
    }

    public void setPlayers(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    @Override
    public void run() {
        System.out.printf("%s vs %s%n",
                player1.weapon, player2.weapon);

        switch (fight()) {
            case -1: ++player1Wins; break;
            case  0: ++draws;       break;
            case  1: ++player2Wins; break;
        }

        if (playedGames() == maxGames) {
            player1.gameOver = true;
            player2.gameOver = true;
            System.out.printf("%s %d : %d %s, %d draws%n",
                    player1.name, player1Wins,
                    player2Wins, player2.name,
                    draws);
            executor.shutdownNow();
        }
    }

    private int fight() {
        return SCORING_TABLE
                .get(player1.weapon)
                .get(player2.weapon);
    }

    private int playedGames() {
        return player1Wins + player2Wins + draws;
    }
}
