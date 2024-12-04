package com.group34.Model.Tower.Factory;

import java.awt.geom.Point2D;

import com.group34.Model.Tower.Tower;

public abstract class TowerFactory {
    private Tower towerReference;
    
    protected TowerFactory(Tower towerReference) {
        this.towerReference = towerReference;
    }

    abstract public Tower createTower(Point2D position);
    
    public Tower getTowerReference() {
        return towerReference;
    }
}
