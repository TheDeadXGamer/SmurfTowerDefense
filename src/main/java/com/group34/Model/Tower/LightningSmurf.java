package com.group34.Model.Tower;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import com.group34.GameSpeed;
import com.group34.Model.Enemy.Attackable;
import com.group34.Model.Positionable;
import com.group34.Model.Projectile.LightningBoltFactory;
import com.group34.Model.Tower.Targeting.ClosestAttack;
import com.group34.Model.Tower.Targeting.Targetings;

/**
 * A class representing the LightningSmurf tower
 */
public class LightningSmurf<enemies extends Positionable & Attackable> implements  Attack<enemies> {
    private int attackSpeed;
    private int damage;
    private boolean canAttack = true;
    private float lastAttack = System.nanoTime();
    private Point2D position;
    private int range;
    private List<enemies> targets = new ArrayList<>();
    private Targetings targeting;
    private int cost;
    private float towerWidth;
    
    public LightningSmurf(Point2D position, int attackSpeed, int damage, int range,float towerWidth,int cost) {
        this.attackSpeed = attackSpeed;
        this.damage = damage;
        this.range = range;
        this.position = position;
        this.targeting = new ClosestAttack(new LightningBoltFactory(this), position);
        this.towerWidth = towerWidth;
        this.cost = cost;
    }

    /**
     * Upgrades the tower and returns the new tower
     * @return the new tower
     */
    @Override
    public Attack upgrade() {
        return new ThunderSmurf(this.position);
    }

    /**
     * Returns the cost of the upgrade
     * @return the cost of the upgrade
     */
    @Override
    public int getUpgradeCost() {
        return 50;
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
     * Attacks the enemies in range
     * @param enemies the enemies in range
     * @return void
     */
    @Override
    public void action() {
        if (System.nanoTime() - lastAttack >= (Math.pow(10,9) / ((double) (attackSpeed * GameSpeed.getCurrentSpeed()) /60 ) )) {
            canAttack = true;
        }

        if (canAttack) {
            targeting.attack(targets);
            canAttack = false;
            lastAttack = System.nanoTime();
        }

    }

    /**
     * Returns the type of the tower
     * @return the type of the tower
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
     * Notifies the tower of the enemies in range
     * @param enemy the enemy in range
     * @return void
     */
    @Override
    public void notifyTower(enemies enemy) {
        if (checkIfInRange(enemy) && !targets.contains(enemy)) {
            targets.add(enemy);
        }
        else if (!checkIfInRange(enemy) && targets.contains(enemy)) {
            targets.remove(enemy);
        }
    }

    /**
     * Notifies the tower of the death of the enemies
     * @param enemy the enemy that died
     * @return void
     */
    @Override
    public void notifyOfDeath(enemies enemy) {
        if (targets.contains(enemy)) {
            targets.remove(enemy);
        }
    }

    /**
     * Checks if the enemy is in range
     * @param enemy the enemy to check
     * @return true if the enemy is in range, false otherwise
     */
    private boolean checkIfInRange(enemies enemy) {
        return position.distance(enemy.getPosition()) <= this.range;
    }

    /**
     * Changes the targeting type of the tower
     * @param targetingtype the new targeting type
     * @return void
     */
    public void changeTargeting(Targetings targetingtype) {
        targeting = targetingtype;
    }


}
