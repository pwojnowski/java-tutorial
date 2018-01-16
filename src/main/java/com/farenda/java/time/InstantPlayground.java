package com.farenda.java.time;

import java.time.Clock;
import java.time.Instant;

public class InstantPlayground {

    public static void main(String[] args) {
        System.out.println(Instant.MIN);
        System.out.println(Instant.EPOCH);
        System.out.println(Instant.ofEpochMilli(1));
        System.out.println(Instant.now());
        System.out.println(Instant.now(Clock.systemUTC()));
        System.out.println(Instant.parse("2017-07-01T14:40:08.000Z"));
        System.out.println(Instant.MAX);
    }
}
