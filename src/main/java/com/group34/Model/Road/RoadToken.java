package com.group34.Model.Road;

import java.awt.geom.Point2D;

public class RoadToken {
    public RoadSection section;
    public int distance;

    public RoadToken(RoadSection section) {
        this.section = section;
        this.distance = 0;
    }

    public RoadToken(RoadSection section, int distance) {
        this.section = section;
        this.distance = distance;
    }

    public RoadToken advance(int distance) {
        return section.advance(this, distance);
    }

    public Point2D getPosition() {
        return section.getPosition(distance);
    }

}
