package com.farenda.java.time;

import java.time.Clock;
import java.time.Duration;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static java.time.Instant.ofEpochMilli;

public class ClockExample {

    public static void main(String[] args) throws InterruptedException {
        fixedClockExample();
        offsetClock();
        systemClockExample();
        systemDefaultZone();
        systemUTC();
        tickClockWithDuration();
        tickSeconds();
    }

    private static void tickSeconds() {
        Clock sysClock = Clock.systemDefaultZone();
        Clock laClock = Clock.tickSeconds(
                ZoneId.of("America/Los_Angeles"));
        Clock kaClock = Clock.tickSeconds(
                ZoneId.of("Europe/Kaliningrad"));

        System.out.println("Sys clock millis: "
                + sysClock.millis());
        System.out.println("LA clock millis : "
                + laClock.millis());
        System.out.println("KA clock millis : "
                + kaClock.millis());

        System.out.println("System clock time  : "
                + ZonedDateTime.now(sysClock));
        System.out.println("LA time tickSeconds: "
                + ZonedDateTime.now(laClock));
        System.out.println("KA time tickSeconds: "
                + ZonedDateTime.now(kaClock));
    }

    private static void tickClockWithDuration() throws InterruptedException {
        Clock fixed = Clock.fixed(
                ofEpochMilli(12345678),
                ZoneId.systemDefault());
        Clock tickClock = Clock.tick(fixed, Duration.ofSeconds(1));
        System.out.println("Tick of fixed: " + tickClock.millis());

        Clock sysClock = Clock.systemDefaultZone();
        System.out.println("Sys clock millis: " + sysClock.millis());

        tickClock = Clock.tick(sysClock, Duration.ofSeconds(1));
        System.out.println("Tick of sys clock: " + tickClock.millis());

        TimeUnit.SECONDS.sleep(2);
        System.out.println("Tick of sys clock: " + tickClock.millis());
    }

    private static void systemUTC() {
        Clock utcClock = Clock.systemUTC();
        System.out.println("Clock's zone: " + utcClock.getZone());
        System.out.println("UTC Clock millis:         "
                + utcClock.millis());
        System.out.println("System.currentTimeMillis: "
                + System.currentTimeMillis());

        // It's the same as:
        Clock sysClock = Clock.system(ZoneId.of("UTC"));
        System.out.println("System clock's zone: "
                + sysClock.getZone());
        System.out.println("System Clock millis:      "
                + sysClock.millis());

        sysClock = Clock.system(ZoneId.of("Europe/Warsaw"));
        System.out.println("System clock's zone: "
                + sysClock.getZone());
        System.out.println("System Clock millis:      "
                + sysClock.millis());
    }

    private static void systemDefaultZone() {
        Clock clock = Clock.systemDefaultZone();
        System.out.println("Clock's zone: " + clock.getZone());
        System.out.println("System Default Clock millis: "
                + clock.millis());
        System.out.println("System.currentTimeMillis:    "
                + System.currentTimeMillis());

        // It's the same as:
        Clock sysClock = Clock.system(ZoneId.systemDefault());
        System.out.println("System clock's zone: "
                + sysClock.getZone());
    }

    private static void offsetClock() {
        Clock constClock = Clock.fixed(
                ofEpochMilli(0), ZoneId.systemDefault());
        System.out.println("Current millis: " + constClock.millis());

        Clock clock = Clock.offset(constClock, Duration.ofSeconds(10));
        System.out.println("Current millis: " + clock.millis());

        clock = Clock.offset(clock, Duration.ofSeconds(-5));
        System.out.println("Current millis: " + clock.millis());

        // The duration 0 returns the same clock:
        clock = Clock.offset(constClock, Duration.ZERO);
        System.out.println("Current millis: " + clock.millis());
        System.out.println("Identical? " + (constClock == clock));
    }

    private static void fixedClockExample() {
        Clock clock = Clock.fixed(
                ofEpochMilli(0),
                ZoneId.systemDefault());
        System.out.println("Current millis: " + clock.millis());
        try {
            // wait for a while:
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Current millis: " + clock.millis());
    }

    private static void systemClockExample() {
        Date now = new Date();
        System.out.println("Current date: " + now);

        ZoneId centralEurope = ZoneId.of("Europe/Warsaw");
        Clock clock = Clock.system(centralEurope);
        System.out.println("From clock: "
                + new Date(clock.millis()));

        try {
            // wait for a while:
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Second later: "
                + new Date(clock.millis()));
    }
}
