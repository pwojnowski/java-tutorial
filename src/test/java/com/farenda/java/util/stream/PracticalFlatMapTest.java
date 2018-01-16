package com.farenda.java.util.stream;

import com.farenda.java.util.stream.PracticalFlatMap.Flight;
import com.farenda.java.util.stream.PracticalFlatMap.Leg;
import org.junit.Test;

import java.util.*;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static org.junit.Assert.*;

public class PracticalFlatMapTest {

    private PracticalFlatMap practical = new PracticalFlatMap();

    @Test
    public void shouldCollectAirports() {
        //given:
        List<Flight> flights = createFlights(
                createLegs("a", "b", "c"),
                createLegs("a", "d"),
                createLegs("b", "d", "e"));

        Set<String> expectedAirports
                = new HashSet<>(asList("a", "b", "c", "d", "e"));

        //when:
        Set<String> collected = practical.collectAirports(flights);

        //then:
        assertEquals(expectedAirports, collected);
    }

    private List<Leg> createLegs(String... airports) {
        List<Leg> legs = new ArrayList<>(airports.length-1);
        for (int i = 1; i < airports.length; ++i) {
            legs.add(new Leg(airports[i-1], airports[i]));
        }
        return legs;
    }

    private List<Flight> createFlights(List<Leg>... legs) {
        return Arrays.stream(legs).map(Flight::new).collect(toList());
    }
}