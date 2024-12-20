package com.group34.Model.Tower;

import com.group34.Model.Positionable;

import java.awt.geom.Point2D;

/**
 * Wrapper class for the tower class.
 */
public class TowerWrapper implements Tower{
    private Tower tower;

    public TowerWrapper(Tower tower) {
        this.tower = tower;
    }

    /**
     * Returns the tower's position.
     * @return the tower's position.
     */
    @Override
    public Point2D getPosition() {
        return tower.getPosition();
    }

    /**
     * Sets the tower's position.
     * @param position the new position.
     * @return void
     */
    @Override
    public void setPosition(Point2D position) {
        tower.setPosition(position);
    }

    /**
     * Returns the tower's range.
     * @return the tower's range.
     */
    @Override
    public int getRange() {
        return tower.getRange();
    }

    /**
     * Calls the tower's action method.
     * @return void
     */
    @Override
    public void action() {
        tower.action();
    }

    /**
     * Returns the tower's type as a string.
     * @return the tower's type as a string.
     */
    @Override
    public String getTowerType() {
        return tower.getTowerType();
    }

    /**
     * Returns the tower's width.
     * @return the tower's width.
     */
    @Override
    public float getTowerWidth() {
        return tower.getTowerWidth();
    }

    /**
     * Returns the tower's cost.
     * @return the tower's cost.
     */
    @Override
    public int getCost() {
        return tower.getCost();
    }

    /**
     * Notifies the tower of an enemy.
     * @param enemy the enemy to notify the tower of.
     * @return void
     */
    @Override
    public void notifyTower(Positionable enemy) {
        tower.notifyTower(enemy);
    }

    /**
     * Notifies the tower of an enemy's death.
     * @param enemy the enemy that died.
     * @return void
     */
    @Override
    public void notifyOfDeath(Positionable enemy) {
        tower.notifyOfDeath(enemy);
    }

    /**
     * Upgrades the tower.
     * @return the upgraded tower.
     */
    @Override
    public Tower upgrade(){

        Tower upgradedTower = tower.upgrade();
        if(upgradedTower != null) {
            tower = tower.upgrade();
        }
        return upgradedTower;
    }

    /**
     * Returns the tower's upgrade cost.
     * @return the tower's upgrade cost.
     */
    @Override
    public int getUpgradeCost() {
        return tower.getUpgradeCost();
    }

}
