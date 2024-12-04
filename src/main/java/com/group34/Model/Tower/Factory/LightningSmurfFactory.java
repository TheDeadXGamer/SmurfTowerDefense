package com.group34.Model.Tower.Factory;

import java.awt.geom.Point2D;

import com.group34.Model.Tower.LightningSmurf;
import com.group34.Model.Tower.Tower;

public class LightningSmurfFactory extends TowerFactory {

    public LightningSmurfFactory() {
        super(new LightningSmurf(null));
    }

    /**
     * Creates a new LightningSmurf tower
     * @param position the position of the tower
     * @return the new LightningSmurf tower
     */
    @Override
    public Tower createTower(Point2D position) {
        return new LightningSmurf(position);
    }
}