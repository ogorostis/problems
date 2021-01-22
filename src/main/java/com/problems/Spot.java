package com.problems;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Spot {
    private String id;
    private boolean free;
    private boolean aisle;
}
