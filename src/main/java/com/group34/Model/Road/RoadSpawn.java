package com.group34.Model.Road;

import java.awt.geom.Point2D;

public class RoadSpawn implements Road {
    private final Point2D position;
    private final Road child;

    public RoadSpawn(Point2D position, Road child) {
        this.position = position;
        this.child = child;
    }

    @Override
    public Point2D getPosition(int distance) {
        assert distance == 0;
        return position;
    }

    @Override
    public void advance(RoadToken token, int distance) {
        assert distance > 0;
        token.setRoadSection(child);
        token.distance = distance;
    }
}