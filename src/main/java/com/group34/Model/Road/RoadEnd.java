package com.group34.Model.Road;

import java.awt.geom.Point2D;

import com.group34.Model.Game.Player;

/**
 * RoadEnd class is a road that is the end of the road.
 * It implements the Road interface.
 * @see Road
 */
public class RoadEnd implements Road {
    Player player;
    Point2D position;


    public RoadEnd(Point2D position, Player player) {
        this.player = player;
        this.position = position;
    }

    /**
     * Advances the road token by a certain distance.
     * @param token The road token to advance.
     * @param distance The distance to advance the road token by.
     * @return void
     */
    @Override
    public void advance(RoadToken token, int distance) {
        assert distance > 0;
        token.isFinished = true;
        player.reduceHealth(1);
    }

    /**
     * Returns the position of the road at a certain distance.
     * @param distance The distance to get the position of the road at.
     * @return The position of the road at the distance.
     */
    @Override
    public Point2D getPosition(int distance) {
        return position;
    }

}
