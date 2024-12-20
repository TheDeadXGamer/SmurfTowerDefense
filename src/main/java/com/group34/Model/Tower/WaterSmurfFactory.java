package com.group34.Model.Tower;

import java.awt.geom.Point2D;

public class WaterSmurfFactory extends TowerFactory {
    @Override
    public Tower createTower(Point2D position) {
        return new TowerWrapper(new WaterSmurf(position, 4, 6, 150, 50, 55));
    }
}
