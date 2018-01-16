package com.farenda.java.util;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

class Player {
    private final String name;
    private final int score;

    public Player(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }
}

public class Java8Comparators {

    public static void main(String[] args) {
        Random rand = new Random();

        // create sample players:
        List<Player> players = new ArrayList<>();
        for (int i = 1; i <= 10; ++i) {
            int score = rand.nextInt(10);
            String name = "Player " + i;
            players.add(new Player(name, score));
        }

        // Sort them by high scores, then alphabetically by names:
        players.sort(
                Comparator.comparingInt(Player::getScore).reversed()
                        .thenComparing(Player::getName));

        System.out.println("Score |   Name");
        for (Player player : players) {
            System.out.printf("%5d | %8s%n",
                    player.getScore(), player.getName());
        }
    }
}
