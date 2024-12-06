package com.group34.Model.Tower;


import java.awt.geom.Point2D;

public class LightningSmurfFactory extends TowerFactory{
    Point2D position;
    public int cost = 500;
    public LightningSmurfFactory(Point2D position) {
        this.position = position;
    }
    @Override
    public Tower createTower() {

        return new LightningSmurf(position,5,2,200);
    }
}