package com.group34.Model.Tower.Factory;

import com.group34.Model.Tower.Attack;
import com.group34.Model.Tower.Tower;

import java.awt.geom.Point2D;

public abstract class TowerFactory {
    protected Attack towerReference;
    protected TowerFactory(Attack towerReference) {
        this.towerReference = towerReference;
    }

    abstract public Tower createTower(Point2D position);
    
    public Attack getTowerReference() {
        return towerReference;
    }
}
