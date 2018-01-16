package com.farenda.javax.validation;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

public class Player {

    // name have to be 3 chars:
    @Size(min = 3, max = 3)
    private String name;

    // possible score in game:
    @Min(0) @Max(100)
    private int score;

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
