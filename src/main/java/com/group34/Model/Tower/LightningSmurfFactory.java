package com.group34.Model.Tower;


import java.awt.geom.Point2D;

public class LightningSmurfFactory extends TowerFactory{
    public LightningSmurfFactory() {

    }
    @Override
    public Tower createTower(Point2D position) {

        return new LightningSmurf(position,2,5,200,50);
    }
}