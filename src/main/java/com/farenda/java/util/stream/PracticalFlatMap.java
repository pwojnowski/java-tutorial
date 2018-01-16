package com.farenda.java.util.stream;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toSet;

public class PracticalFlatMap {

    static class Flight {
        private final List<Leg> legs;

        public Flight(List<Leg> legs) {
            this.legs = legs;
            System.out.println("Flight with legs: " + legs);
        }

        public List<Leg> getLegs() {
            return legs;
        }
    }

    static class Leg {
        String origin, destination;

        public Leg(String origin, String destination) {
            this.origin = origin;
            this.destination = destination;
        }

        @Override
        public String toString() {
            return "Leg(" + origin + "->" + destination + ')';
        }
    }

    public Set<String> collectAirports(Collection<Flight> flights) {
        return flights.stream()
                .map(Flight::getLegs)
                .flatMap(Collection::stream)
                .flatMap(this::getLegAirports)
                .collect(toSet());
    }

    private Stream<String> getLegAirports(Leg leg) {
        return Stream.of(leg.origin, leg.destination);
    }
}