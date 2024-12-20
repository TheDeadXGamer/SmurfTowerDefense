package com.group34.Model.Tower;

import com.group34.Model.Enemy.Attackable;
import com.group34.Model.Positionable;

import java.awt.geom.Point2D;

/**
 * Provide Client API for Tower Collections.
 **/
public interface Tower<enemies extends Positionable & Attackable> extends TowerListener<enemies>, Positionable,Upgrade {
    /**
     * @return the position is needed to calculate in range targets. Also required for UI placement.
     **/

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
     *
     * @return the width of the tower.
     */
    float getTowerWidth();

    /**
     * @return the cost of the tower.
     */
    int getCost();


}
