package com.group34.Model.Road;

import java.awt.geom.Point2D;

/**
 * A token that is used to represent an enemy's position on a road.
 */
public class RoadToken {
    Road section;
    int distance;
    boolean isFinished = false;

    /**
     * Creates a new RoadToken that is at the start of the given road section.
     * @param section The road section that the token is on.
     */
    public RoadToken(Road section) {
        this.section = section;
        this.distance = 0;
    }

    /**
     * Creates a new RoadToken that is at the given distance along the given road section.
     * @param section The road section that the token is on.
     * @param distance The distance along the road section that the token is at.
     */
    public RoadToken(Road section, int distance) {
        this.section = section;
        this.distance = distance;
    }

    /**
     * Moves the token along the road by the given amount.
     * @param amount The amount to move the token by.
     * @return void
     */
    void setRoadSection(Road section) {
        this.section = section;
    }

    /**
     * Checks if the token has reached the end of the road.
     * @return boolean
     */
    public boolean isFinished() {
        return isFinished;
    }

    /**
     * Moves the token along the road by the given amount.
     * @param amount The amount to move the token by.
     * @return void
     */
    public void advance(int amount) {
        section.advance(this, amount);
    }

    /**
     * Gets the position of the token on the road.
     * @return Point2D
     */
    public Point2D getPosition() {
        return section.getPosition(distance);
    }

}
