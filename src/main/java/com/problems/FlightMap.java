package com.problems;

import java.util.LinkedList;
import java.util.List;

public class FlightMap {
    public List<Spot> getSeats(final Spot[][] spots, final int num) {
        // next to each other, no aisle separation
        final List<Spot> contiguousSeats = new LinkedList<>();

        // only separation can be aisles
        final List<Spot> aisleSeparatedSeats = new LinkedList<>();
        // we need the one closest to front of the plane
        final List<List<Spot>> aisleSeparatedCandidates = new LinkedList<>();

        for (final Spot[] row : spots) {
            contiguousSeats.clear();
            aisleSeparatedSeats.clear();
            int visited = 0;
            for (final Spot spot : row) {
                if (spot.isAisle()) {
                    // -----
                    // aisle
                    // -----
                    contiguousSeats.clear();
                } else if (spot.isFree()) {
                    // ---------
                    // free seat
                    // ---------
                    contiguousSeats.add(spot);
                    if (contiguousSeats.size() == num) {
                        return contiguousSeats;
                    }
                    if (aisleSeparatedSeats.size() < num) {
                        aisleSeparatedSeats.add(spot);
                    }
                } else {
                    // -------------
                    // occupied seat
                    // -------------
                    contiguousSeats.clear();
                    aisleSeparatedSeats.clear();
                }
                visited++;
                // if needed count is strictly higher than rest of row,
                // no need to continue examining the row
                if (num - aisleSeparatedSeats.size() > row.length - visited) {
                    break;
                }
            }
            if (aisleSeparatedSeats.size() == num) {
                aisleSeparatedCandidates.add(new LinkedList<>(aisleSeparatedSeats));
            }
        }

        if (aisleSeparatedCandidates.size() == 0) {
            return new LinkedList<>();
        } else {
            return aisleSeparatedCandidates.get(0);
        }
    }
}
