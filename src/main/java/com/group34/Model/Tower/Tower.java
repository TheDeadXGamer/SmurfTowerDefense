package com.group34.Model.Tower;
import com.group34.Model.Enemy.Enemy;

import java.awt.geom.Point2D;

/**
 * Provide Client API for Tower Collections.
 **/
public interface Tower extends TowerListener{
    /**
     * @return the position is needed to calculate in range targets. Also required for UI placement.
     **/
    public Point2D getPosition();

    /**
     * @return Determines in range targets.
     **/
    public int getRange();

    /**
     * @return Generalized action of Tower which is called by downstream client user. The towers themselves handle the required input in their implementation.
     **/
    public void action();
    public void checkInRange(Enemy enemy);
}
