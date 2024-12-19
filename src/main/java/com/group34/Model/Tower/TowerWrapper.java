package com.group34.Model.Tower;

import com.group34.Model.Positionable;

import java.awt.geom.Point2D;

public class TowerWrapper implements Tower{
    Tower tower;

    public TowerWrapper(Tower tower) {
        this.tower = tower;
    }

    @Override
    public Point2D getPosition() {
        return tower.getPosition();
    }

    @Override
    public void setPosition(Point2D position) {
        tower.setPosition(position);
    }

    @Override
    public int getRange() {
        return tower.getRange();
    }

    @Override
    public void action() {
        tower.action();
    }

    @Override
    public String getTowerType() {
        return tower.getTowerType();
    }

    @Override
    public float getTowerWidth() {
        return tower.getTowerWidth();
    }

    @Override
    public int getCost() {
        return tower.getCost();
    }

    @Override
    public void notifyTower(Positionable enemy) {
        tower.notifyTower(enemy);
    }

    @Override
    public void notifyOfDeath(Positionable enemy) {
        tower.notifyOfDeath(enemy);
    }

    @Override
    public Tower upgrade(){

        Tower upgradedTower = tower.upgrade();
        if(upgradedTower != null) {
            tower = tower.upgrade();
        }
        return upgradedTower;
    }
}
