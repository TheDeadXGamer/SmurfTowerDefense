package com.group34.Model.Tower;

import com.group34.Model.Projectile.LightningBoltFactory;

import java.awt.geom.Point2D;

public class LightningSmurfFactory extends TowerFactory{
    Point2D position;
    public LightningSmurfFactory(Point2D position) {
        this.position = position;
    }
    @Override
    public Tower createTower() {
        return new LightningSmurf(position);
    }
}
