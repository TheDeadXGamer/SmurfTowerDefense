package com.group34.Model.Tower;


import java.awt.geom.Point2D;

public class LightningSmurfFactory extends TowerFactory {
    @Override
    public Tower createTower(Point2D position) {
       return new TowerWrapper(new LightningSmurf(position,2,5,200,50,50));
    }

}