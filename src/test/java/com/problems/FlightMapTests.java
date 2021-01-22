package com.problems;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class FlightMapTests {
    private static final FlightMap flight = new FlightMap();

    @Test
    public void test01() {
        String[] rows = new String[] {
                "00_010_00",
                "00_000_00",
                "00_010_00",
                "00_000_00"
        };

        final Spot[][] spots = toSpots(rows);
        final List<Spot> list = flight.getSeats(spots, 4);
        Assertions.assertEquals(list.size(), 4);
        Assertions.assertEquals(list.get(0).getId(), "2A");

        final List<Spot> list2 = flight.getSeats(spots, 3);
        Assertions.assertEquals(list2.size(), 3);
        Assertions.assertEquals(list2.get(0).getId(), "2C");
        Assertions.assertEquals(list2.get(1).getId(), "2D");
        Assertions.assertEquals(list2.get(2).getId(), "2E");
    }

    private Spot[][] toSpots(final String[] rows) {
        final Spot[][] spots = new Spot[rows.length][];
        int lineIndex = 0;
        for (final String row : rows) {
            final byte[] bytes = row.getBytes();
            final Spot[] line = new Spot[bytes.length];
            String id;
            int s = 0;
            int c = 0;
            for (final byte b : bytes) {
                if (b == '0') {
                    id = (1+lineIndex) + String.valueOf((char)('A' + c));
                    line[s] = Spot.builder().free(true).aisle(false).id(id).build();
                    c++;
                } else if (b == '1') {
                    id = (1+lineIndex) + String.valueOf((char)('A' + c));
                    line[s] = Spot.builder().free(false).aisle(false).id(id).build();
                    c++;
                } else if (b == '_') {
                    line[s] = Spot.builder().free(false).aisle(true).id("none").build();
                }
                s++;
            }
            spots[lineIndex] = line;
            lineIndex++;
        }
        return spots;
    }
}
