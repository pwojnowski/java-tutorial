package com.farenda.javax.validation;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

public class Player {

    // name have to be 3 chars:
    @Size(min = 3, max = 3)
    private final String name;

    // possible score in game:
    @Min(0) @Max(100)
    private final int score;

    public Player(String name, int score) {
        this.name = name;
        this.score = score;
    }

    // just for logs
    @Override
    public String toString() {
        return "Player{name='" + name + '\'' + ", score=" + score + '}';
    }
}
