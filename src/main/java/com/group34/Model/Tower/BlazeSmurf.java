package com.group34.Model.Tower;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import com.group34.Model.Enemy.Attackable;
import com.group34.Model.Positionable;
import com.group34.Model.Projectile.FireballFactory;
import com.group34.Model.Tower.Targeting.AreaAttack;
import com.group34.Model.Tower.Targeting.Targetings;

/**
 * A class representing the BlazeSmurf tower
 */
public class BlazeSmurf<enemies extends Positionable & Attackable> implements Attack<enemies> {
    private int attackSpeed;
    private int damage;
    private Point2D position;
    private int range;
    private int cost;
    private List<enemies> targets = new ArrayList<>();
    private boolean canAttack = true;
    private float lastAttack = System.nanoTime();
    private float towerWidth;
    private Targetings targeting;

    public BlazeSmurf(Point2D position) {
        this.attackSpeed = 3;
        this.damage = 10;
        this.range = 180;
        this.position = position;
        this.cost = 70;
        this.towerWidth = 50;
        this.targeting = new AreaAttack(new FireballFactory(this), position, range);
    }

    /**
     * Returns the attack speed of the tower
     * @return the attack speed of the tower
     */
    @Override
    public int getAttackSpeed() {
        return attackSpeed;
    }

    /**
     * Returns the damage of the tower
     * @return the damage of the tower
     */
    @Override
    public int getDamage() {
        return damage;
    }

    /**
     * Returns the position of the tower
     * @return the position of the tower
     */
    @Override
    public Point2D getPosition() {
        return (Point2D) position.clone();
    }

    /**
     * Sets the position of the tower
     * @param position the new position of the tower
     * @return void
     */
    @Override
    public void setPosition(Point2D position) {
        this.position = position;
    }

    /**
     * Returns the range of the tower
     * @return the range of the tower
     */
    @Override
    public int getRange() {
        return range;
    }

    /**
     * Attacks the enemies in the list of targets
     * @return void
     */
    @Override
    public void action() {
        if (System.nanoTime() - lastAttack >= (Math.pow(10, 9) / attackSpeed)) {
            canAttack = true;
        }
        if (canAttack) {
            targeting.attack(targets);
            canAttack = false;
            lastAttack = System.nanoTime();
        }
    }

    /**
     * Returns the type of the tower as a string
     * @return the type of the tower as a string
     */
    @Override
    public String getTowerType() {
        return this.getClass().getSimpleName();
    }

    /**
     * Returns the width of the tower
     * @return the width of the tower
     */
    @Override
    public float getTowerWidth() {
        return towerWidth;
    }

    /**
     * Returns the cost of the tower
     * @return the cost of the tower
     */
    @Override
    public int getCost() {
        return cost;
    }

    /**
     * Notifies the tower of an enemy in range
     * @param enemy the enemy in range
     * @return void
     */
    @Override
    public void notifyTower(enemies enemy) {
        if (checkIfInRange(enemy) && !targets.contains(enemy)) {
            targets.add(enemy);
        } else if (!checkIfInRange(enemy) && targets.contains(enemy)) {
            targets.remove(enemy);
        }
    }

    /**
     * Notifies the tower of an enemy death
     * @param enemy the enemy that died
     * @return void
     */
    @Override
    public void notifyOfDeath(enemies enemy) {
        targets.remove(enemy);
    }

    /**
     * Checks if the enemy is in range of the tower
     * @param enemy the enemy to check
     * @return true if the enemy is in range, false otherwise
     */
    private boolean checkIfInRange(enemies enemy) {
        return position.distance(enemy.getPosition()) <= this.range;
    }

    /**
     * Upgrades the tower
     * @return the upgraded tower
     */
    @Override
    public Tower upgrade() {
        return null;
    }

    /**
     * Returns the cost of the upgrade
     * @return the cost of the upgrade
     */
    @Override
    public int getUpgradeCost() {
        return 0;
    }

}