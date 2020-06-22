package com.problems;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.problems.SeatMap.AVAIL;
import static com.problems.SeatMap.TAKEN;
import static com.problems.SeatMap.AISLE;
import static com.problems.SeatMap.RowSeating;
import static com.problems.SeatMap.MapSeating;

public class SeatMapTests {

    private final SeatMap seatMap = new SeatMap();

    private final static int[] row_3TA2T = new int[] {
            TAKEN, TAKEN, TAKEN,
            AISLE,
            TAKEN, TAKEN, AVAIL, AVAIL,
            AISLE,
            AVAIL, AVAIL, AVAIL};

    private final static int[] row_3A4A3 = new int[] {
            AVAIL, AVAIL, AVAIL,
            AISLE,
            AVAIL, AVAIL, AVAIL, AVAIL,
            AISLE,
            AVAIL, AVAIL, AVAIL};

    private final static int[] row_3T4T3 = new int[] {
            AVAIL, AVAIL, AVAIL,
            TAKEN,
            AVAIL, AVAIL, AVAIL, AVAIL,
            TAKEN,
            AVAIL, AVAIL, AVAIL};

    private final static int[] row_2TA = new int[] {
            TAKEN, TAKEN, AVAIL,
            AISLE,
            AVAIL, AVAIL, AVAIL, AVAIL,
            AISLE,
            AVAIL, AVAIL, AVAIL};

    @Test
    public void testRow3A4A3() throws Exception {
        final RowSeating result = seatMap.getRowSeating(row_3A4A3, 4);
        Assertions.assertEquals(4, result.getStart());
        Assertions.assertEquals(7, result.getEnd());
        Assertions.assertEquals(0, result.getAisles());
    }

    @Test
    public void testRow3T4T3() throws Exception {
        final RowSeating result = seatMap.getRowSeating(row_3T4T3, 4);
        Assertions.assertEquals(4, result.getStart());
        Assertions.assertEquals(7, result.getEnd());
        Assertions.assertEquals(0, result.getAisles());
    }

    @Test
    public void testRowOneAisle() throws Exception {
        final RowSeating result = seatMap.getRowSeating(row_3TA2T, 4);
        Assertions.assertEquals(6, result.getStart());
        Assertions.assertEquals(10, result.getEnd());
        Assertions.assertEquals(1, result.getAisles());
    }

    @Test
    public void testRowOneAisleAllSeatsAvailable() throws Exception {
        final RowSeating result = seatMap.getRowSeating(row_3A4A3, 6);
        Assertions.assertEquals(0, result.getStart());
        Assertions.assertEquals(6, result.getEnd());
        Assertions.assertEquals(1, result.getAisles());
    }

    @Test
    public void testRowOneAisleWithTakenSeats() throws Exception {
        final RowSeating result = seatMap.getRowSeating(row_3TA2T, 5);
        Assertions.assertEquals(6, result.getStart());
        Assertions.assertEquals(11, result.getEnd());
        Assertions.assertEquals(1, result.getAisles());
    }

    @Test
    public void testRowPartyAcrossTwoAisles() throws Exception {
        final RowSeating result = seatMap.getRowSeating(row_2TA, 6);
        Assertions.assertEquals(2, result.getStart());
        Assertions.assertEquals(9, result.getEnd());
        Assertions.assertEquals(2, result.getAisles());
    }

    @Test
    public void testMap() {
        final MapSeating mapSeating = seatMap.getSeating(
                new int[][] {row_2TA, row_3A4A3, row_3T4T3, row_3TA2T}, 5);
        Assertions.assertEquals(0, mapSeating.getRow());
        Assertions.assertEquals(2, mapSeating.getRowSeating().getStart());
        Assertions.assertEquals(7, mapSeating.getRowSeating().getEnd());
        Assertions.assertEquals(1, mapSeating.getRowSeating().getAisles());
    }

    @Test
    public void testMap2() {
        final MapSeating mapSeating = seatMap.getSeating(
                new int[][] {row_3A4A3, row_2TA, row_3T4T3, row_3TA2T}, 7);
        Assertions.assertEquals(0, mapSeating.getRow());
        Assertions.assertEquals(0, mapSeating.getRowSeating().getStart());
        Assertions.assertEquals(7, mapSeating.getRowSeating().getEnd());
        Assertions.assertEquals(1, mapSeating.getRowSeating().getAisles());
    }

    @Test
    public void testMap3() {
        final MapSeating mapSeating = seatMap.getSeating(
                new int[][] {row_3A4A3, row_2TA, row_3T4T3, row_3TA2T}, 10);
        Assertions.assertEquals(0, mapSeating.getRow());
        Assertions.assertEquals(0, mapSeating.getRowSeating().getStart());
        Assertions.assertEquals(11, mapSeating.getRowSeating().getEnd());
        Assertions.assertEquals(2, mapSeating.getRowSeating().getAisles());
    }

    @Test
    public void testMap4() {
        final MapSeating mapSeating = seatMap.getSeating(
                new int[][] {row_2TA, row_3T4T3, row_3TA2T, row_3A4A3}, 10);
        Assertions.assertEquals(3, mapSeating.getRow());
        Assertions.assertEquals(0, mapSeating.getRowSeating().getStart());
        Assertions.assertEquals(11, mapSeating.getRowSeating().getEnd());
        Assertions.assertEquals(2, mapSeating.getRowSeating().getAisles());
    }

    @Test
    public void testMapCannotSeatSuperLargeParty() {
        try {
            seatMap.getSeating(new int[][]{row_2TA, row_3T4T3, row_3TA2T, row_3A4A3}, 20);
            Assertions.assertEquals(1, 2);
        } catch (RuntimeException rte) {
            Assertions.assertEquals(1, 1);
        }
    }
}
