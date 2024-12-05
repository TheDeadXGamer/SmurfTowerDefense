package com.group34.Model.Road;

import java.awt.geom.Point2D;

public class RoadToken {
    Road section;
    int distance;

    public RoadToken(Road section) {
        this.section = section;
        this.distance = 0;
    }

    public RoadToken(Road section, int distance) {
        this.section = section;
        this.distance = distance;
    }

    void setRoadSection(Road section) {
        this.section = section;
    }

    public void advance(int amount) {
        section.advance(this, amount);
    }

    public Point2D getPosition() {
        return section.getPosition(distance);
    }

}
