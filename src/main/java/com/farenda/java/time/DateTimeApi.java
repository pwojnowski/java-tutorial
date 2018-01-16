package com.farenda.java.time;

import java.time.*;
import java.util.Date;

public class DateTimeApi {

    void pointInTime() {
        Instant now = Instant.now();
        System.out.println("Point in time: " + now);
        Date nowDate = Date.from(now);
        System.out.println("Current date: " + nowDate);
    }

    void localDateTime() {
        LocalDateTime departureDate = LocalDateTime
                .of(2017, Month.OCTOBER, 2, 15, 50);
        System.out.println("Departure: " + departureDate);
        System.out.println(Year.of(1980).isLeap());
    }

    void duration() {
        Instant beginning = Instant.EPOCH;
        Instant end = Instant.now();
        Duration length = Duration.between(beginning, end);
        System.out.println("Millis since the Epoch: " + length.toMillis());
    }

    void daysAgo() {
        LocalDate pastDate = LocalDate.of(2015, Month.MAY, 19);
        Period period = Period.between(pastDate, LocalDate.now());

        System.out.printf("It was %s years ago.%n", period.getYears());
    }

    public static final void main(final String[] args) {
        DateTimeApi api = new DateTimeApi();
        api.pointInTime();
        api.localDateTime();
        api.duration();
        api.daysAgo();
    }

}
