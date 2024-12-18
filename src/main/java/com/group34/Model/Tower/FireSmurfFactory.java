package com.group34.Model.Tower;

import java.awt.geom.Point2D;

public class FireSmurfFactory extends TowerFactory {
    @Override
    public Tower createTower(Point2D position) {
        return new TowerWrapper(new FireSmurf(position, 7, 1, 180, 50, 60));
    }
}
