package com.group34.Model.Tower;

import java.awt.geom.Point2D;

/**
 * Provide Client API for Tower Collections.
 **/
public interface Tower extends TowerListener {
    /**
     * @return the position is needed to calculate in range targets. Also required for UI placement.
     **/
    Point2D getPosition();

    /**
     * @return Sets the position of the tower.
     **/
    void setPosition(Point2D position);

    /**
     * @return Determines in range targets.
     **/
    int getRange();

    /**
     * @return Generalized action of Tower which is called by downstream client user. The towers themselves handle the required input in their implementation.
     **/
    void action();

    /**
     * @return The type of the tower as a String.
     **/
    String getTowerType();

    /**
     * @return The path to the image equivalent of the tower.
     **/
    String getTowerImagePath();

    /**
     * @return The cost of the tower.
     **/
    int getCost();
}
