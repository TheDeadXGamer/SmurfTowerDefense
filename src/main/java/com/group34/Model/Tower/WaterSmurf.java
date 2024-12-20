package com.group34.Model.Tower;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import com.group34.Model.Enemy.Attackable;
import com.group34.Model.Positionable;
import com.group34.Model.Projectile.WaterdropFactory;
import com.group34.Model.Tower.Targeting.ClosestAttack;
import com.group34.Model.Tower.Targeting.Targetings;

/**
 * WaterSmurf class that implements the Attack interface.
 * @param <enemies> the type of enemies that the tower can attack.
 */
public class WaterSmurf<enemies extends Positionable & Attackable> implements Attack<enemies> {
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

    public WaterSmurf(Point2D position, int attackSpeed, int damage, int range, float towerWidth, int cost) {
        this.attackSpeed = attackSpeed;
        this.damage = damage;
        this.range = range;
        this.position = position;
        this.targeting = new ClosestAttack(new WaterdropFactory(this), position);
        this.towerWidth = towerWidth;
        this.cost = cost;
    }

    /**
     * Upgrades the tower to a TsunamiSmurf.
     * @return the upgraded tower.
     */
    @Override
    public Attack upgrade() {
        return new TsunamiSmurf(this.position);
    }

    /**
     * Returns the cost of upgrading the tower.
     * @return the cost of upgrading the tower.
     */
    @Override
    public int getUpgradeCost() {
        return 75;
    }

    /**
     * Returns the tower's position.
     * @return the tower's position.
     */
    @Override
    public Point2D getPosition() {
        return (Point2D) position.clone();
    }

    /**
     * Sets the tower's position.
     * @param position the new position.
     * @return void
     */
    @Override
    public void setPosition(Point2D position) {
        this.position = position;
    }

    /**
     * Returns the tower's range.
     * @return the tower's range.
     */
    @Override
    public int getRange() {
        return range;
    }

    /**
     * Attacks the enemies.
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
     * Returns the tower's type as a string.
     * @return the tower's type as a string.
     */
    @Override
    public String getTowerType() {
        return this.getClass().getSimpleName();
    }

    /**
     * Returns the tower's width.
     * @return the tower's width.
     */
    @Override
    public float getTowerWidth() {
        return towerWidth;
    }

    /**
     * Returns the tower's cost.
     * @return the tower's cost.
     */
    @Override
    public int getCost() {
        return cost;
    }

    /**
     * Returns the tower's attack speed.
     * @return the tower's attack speed.
     */
    @Override
    public int getAttackSpeed() {
        return attackSpeed;
    }

    /**
     * Returns the tower's damage.
     * @return the tower's damage.
     */
    @Override
    public int getDamage() {
        return damage;
    }

    /**
     * Notifies the tower of an enemy's presence.
     * @param enemy the enemy.
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
     * Notifies the tower of an enemy's death.
     * @param enemy the enemy.
     * @return void
     */
    @Override
    public void notifyOfDeath(enemies enemy) {
        targets.remove(enemy);
    }

    /**
     * Checks if an enemy is in range.
     * @param enemy the enemy.
     * @return true if the enemy is in range, false otherwise.
     */
    private boolean checkIfInRange(enemies enemy) {
        return position.distance(enemy.getPosition()) <= this.range;
    }

    /**
     * Changes the tower's targeting type.
     * @param targetingtype the new targeting type.
     * @return void
     */
    public void changeTargeting(Targetings targetingtype) {
        targeting = targetingtype;
    }
}

