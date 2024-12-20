package com.group34.Model.Tower;

import java.awt.geom.Point2D;

/**
 * Factory class for creating FireSmurf tower
 */
public class FireSmurfFactory extends TowerFactory {

    /**
     * Create a FireSmurf tower
     * @param position position of the tower
     * @return FireSmurf tower
     */
    @Override
    public Tower createTower(Point2D position) {
        return new TowerWrapper(new FireSmurf(position, 7, 1, 180, 50, 60));
    }
}
