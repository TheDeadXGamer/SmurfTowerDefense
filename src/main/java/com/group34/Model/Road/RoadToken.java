package com.group34.Model.Road;

import java.awt.geom.Point2D;

public class RoadToken {
    RoadSection section;
    int distance;

    public RoadToken(RoadSection section) {
        this.section = section;
        this.distance = 0;
    }

    public RoadToken(RoadSection section, int distance) {
        this.section = section;
        this.distance = distance;
    }

    void setRoadSection(RoadSection section) {
        this.section = section;
    }

    public void advance(int amount) {
        section.advance(this, amount);
    }

    public Point2D getPosition() {
        return section.getPosition(distance);
    }

}
