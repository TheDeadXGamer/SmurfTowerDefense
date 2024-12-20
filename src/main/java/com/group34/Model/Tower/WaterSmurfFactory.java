package com.group34.Model.Tower;

import java.awt.geom.Point2D;

/**
 * Factory class for creating WaterSmurf towers.
 */
public class WaterSmurfFactory extends TowerFactory {

    /**
     * Creates a WaterSmurf tower.
     * @param position the position of the tower.
     * @return the created tower.
     */
    @Override
    public Tower createTower(Point2D position) {
        return new TowerWrapper(new WaterSmurf(position, 4, 6, 150, 50, 55));
    }
}
