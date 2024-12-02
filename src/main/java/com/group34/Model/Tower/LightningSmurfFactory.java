package com.group34.Model.Tower;


import java.awt.geom.Point2D;

public class LightningSmurfFactory extends TowerFactory {

    public LightningSmurfFactory() {
        super(new LightningSmurf(null,0,0,0));
    }
    @Override
    public Tower createTower(Point2D position) {
        return new LightningSmurf(position,1,2,200);
    }
}