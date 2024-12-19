package com.group34.Model.Tower;

import java.awt.geom.Point2D;

public abstract class TowerFactory {
    /**
     * Creates a tower at the given position.
     * @param position the position of the created tower.
     * @return the created tower.
     */
    abstract public Tower createTower(Point2D position);
}
