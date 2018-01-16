package com.farenda.java.util.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NamedGroups {

    public static void main(String[] args) {
        beforeJava8WithIndices();
        java8WithNamedGroups();
    }

    private static void beforeJava8WithIndices() {
        String data = "Flight AA JFK.101.KRK[2016-12-06]";
        Pattern flightPattern = Pattern.compile("\\w+" + " "
                + "(..) "
                + "(...)\\." + "(\\d+)\\." + "(...)"
                + "\\[(\\d+-\\d+-\\d+)\\]");
        Matcher flight = flightPattern.matcher(data);
        flight.find();
        System.out.println("Airline: " + flight.group(1));
        System.out.println("Origin: " + flight.group(2));
        System.out.println("Number: " + flight.group(3));
        System.out.println("Destination: " + flight.group(4));
        System.out.println("Departure date: " + flight.group(5));
    }

    private static void java8WithNamedGroups() {
        String data = "Flight AA JFK.101.KRK[2016-12-06]";
        Pattern flightPattern = Pattern.compile("\\w+" + " "
                + "(?<airline>..) "
                + "(?<origin>...)\\." + "(?<number>\\d+)\\." + "(?<destination>...)"
                + "\\[(?<deptDate>\\d+-\\d+-\\d+)\\]");
        Matcher flight = flightPattern.matcher(data);
        flight.find();
        System.out.println("Airline: " + flight.group("airline"));
        System.out.println("Origin: " + flight.group("origin"));
        System.out.println("Number: " + flight.group("number"));
        System.out.println("Destination: " + flight.group("destination"));
        System.out.println("Departure date: " + flight.group("deptDate"));
    }
}
