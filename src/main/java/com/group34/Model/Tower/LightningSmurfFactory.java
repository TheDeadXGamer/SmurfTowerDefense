package com.group34.Model.Tower;

import java.awt.geom.Point2D;

/**
 * This class is a factory for creating LightningSmurf tower.
 */
public class LightningSmurfFactory extends TowerFactory {

    /**
     * This method creates a LightningSmurf tower.
     * @param position The position of the tower.
     * @return The LightningSmurf tower.
     */
    @Override
    public Tower createTower(Point2D position) {
       return new TowerWrapper(new LightningSmurf(position,2,5,200,50,50));
    }

}