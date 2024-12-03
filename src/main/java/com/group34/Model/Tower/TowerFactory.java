package com.group34.Model.Tower;

import java.awt.geom.Point2D;

public abstract class TowerFactory {
    protected Tower towerReference;
    protected TowerFactory(Tower towerReference) {
        this.towerReference = towerReference;
    }

    abstract public Tower createTower(String type, Point2D position);
    
    public Tower getTowerReference() {
        return towerReference;
    }
}
