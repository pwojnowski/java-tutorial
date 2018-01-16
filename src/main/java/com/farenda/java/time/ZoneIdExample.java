package com.farenda.java.time;

import java.time.ZoneId;
import java.util.Set;
import java.util.TreeSet;

public class ZoneIdExample {

    public static void main(String[] args) {
        getAvailableZones();
    }

    private static void getAvailableZones() {
        Set<String> zoneIds = ZoneId.getAvailableZoneIds();
        System.out.printf("Available zones (%d):%n", zoneIds.size());

        // Lets use TreeSet to have them in lexicographically order:
        zoneIds = new TreeSet<>(zoneIds);

        for (String zoneId : zoneIds) {
            System.out.println("Zone id: " + zoneId);
        }
    }
}
