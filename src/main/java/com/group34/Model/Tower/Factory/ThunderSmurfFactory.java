package com.group34.Model.Tower.Factory;

import java.awt.geom.Point2D;

import com.group34.Model.Tower.ThunderSmurf;
import com.group34.Model.Tower.Tower;

public class ThunderSmurfFactory extends TowerFactory {

    public ThunderSmurfFactory() {
        super(new ThunderSmurf(null));
    }

    /**
     * Creates a new ThunderSmurf tower
     * @param position the position of the tower
     * @return the new ThunderSmurf tower
     */
    @Override
    public Tower createTower(Point2D position) {
        return new ThunderSmurf(position);
    }
    
}
