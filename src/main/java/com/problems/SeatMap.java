package com.problems;

import lombok.Data;

import java.util.LinkedList;
import java.util.List;

public class SeatMap {
    public static final int AVAIL = 0;
    public static final int TAKEN = 1;
    public static final int AISLE = 2;

    @Data
    public static class RowSeating {
        private final int start;
        private final int end;
        private final int aisles;
    }

    @Data
    public static class MapSeating {
        private final int row;
        private final RowSeating rowSeating;
    }

    /**
     * Get seating for party of a given size.
     * Find contiguous (no aisles in between) seats or
     * seats separated by aisles as a 2nd choice
     * @param seats row seats
     * @return row result
     */
    RowSeating getRowSeating(final int[] seats, final int partySize) throws Exception {
        int position = 0;
        int partyCount = 0;
        int aisles = 0;
        int lastAisle = -1;
        final List<RowSeating> candidates = new LinkedList<>();

        while (position < seats.length) {
            while (position < seats.length && partyCount < partySize) {
                if (seats[position] == AVAIL) {
                    partyCount++;
                } else if (seats[position] == AISLE) {
                    aisles++;
                    lastAisle = position;
                } else if (seats[position] == TAKEN) {
                    partyCount = 0;
                    lastAisle = -1;
                    aisles = 0;
                }
                position++;
            }

            if (partyCount == partySize) {
                final RowSeating result = new RowSeating(position - partyCount - aisles, position - 1, aisles);
                if (aisles == 0) {
                    // Found contiguous seats with no aisle separation
                    return result;
                } else {
                    candidates.add(result);
                    // Found seats with aisle separation but
                    // there may be better (no aisle) further down the row
                    // so we continue (after backtracking to right after last aisle)
                    position = 1 + lastAisle;
                    partyCount = 0;
                    aisles = 0;
                    lastAisle = -1;
                }
            }
        }

        if (candidates.size() == 0) {
            throw new Exception("Nothing in row");
        } else {
            // There might be multiple seating configs with aisle separation
            // but return the first one that was encountered (from left to right)
            return candidates.get(0);
        }
    }

    public MapSeating getSeating(final int[][] rows, final int partySize) {
        final List<MapSeating> candidates = new LinkedList<>();
        for (int r = 0; r< rows.length; r++) {
            final int[] row = rows[r];
            try {
                final MapSeating mapSeating = new MapSeating(r, getRowSeating(row, partySize));
                if (mapSeating.getRowSeating().getAisles() == 0) {
                    // As soon as we have seats not
                    // separated by aisles, return them
                    return mapSeating;
                } else {
                    candidates.add(mapSeating);
                }
            } catch (Exception e) {
                // ignore : move on to next row
            }
        }

        if (candidates.size() == 0) {
            throw new RuntimeException("Cannot fit party in any row");
        } else {
            // return the first one encountered
            // from top of the map
            return candidates.get(0);
        }
    }
}
