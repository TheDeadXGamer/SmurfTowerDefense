package com.group34.Model.Road;

import java.awt.geom.Point2D;

/**
 * RoadSpawn is a road section that represents the start of a road.
 */
public class RoadSpawn implements Road {
    private final Point2D position;
    private final Road child;

    public RoadSpawn(Point2D position, Road child) {
        this.position = position;
        this.child = child;
    }

    /**
     * Returns the position of the road spawn.
     * @param distance the distance along the road
     * @throws AssertionError if the distance is not 0
     * @return the position of the road spawn
     */
    @Override
    public Point2D getPosition(int distance) {
        assert distance == 0;
        return position;
    }

    /**
     * Advances the token along the road spawn.
     * @param token the token to advance
     * @param distance the distance to advance the token
     * @throws AssertionError if the distance is not positive
     * @return void
     */
    @Override
    public void advance(RoadToken token, int distance) {
        assert distance > 0;
        token.setRoadSection(child);
        token.distance = distance;
    }
}