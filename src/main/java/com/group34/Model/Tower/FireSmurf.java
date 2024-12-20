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
 * FireSmurf is a tower that attacks enemies in range with fireballs.
 */
public class FireSmurf<enemies extends Positionable & Attackable> implements Attack<enemies> {
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

    public FireSmurf(Point2D position, int attackSpeed, int damage, int range, float towerWidth, int cost) {
        this.attackSpeed = attackSpeed;
        this.damage = damage;
        this.range = range;
        this.position = position;
        this.targeting = new AreaAttack(new FireballFactory(this), position, range);
        this.towerWidth = towerWidth;
        this.cost = cost;
    }

    /**
     * Upgrade the tower to a BlazeSmurf.
     * @return BlazeSmurf
     */
    @Override
    public Attack upgrade() {
        return new BlazeSmurf(this.position);
    }

    /**
     * Get the cost of upgrading the tower.
     * @return int
     */
    @Override
    public int getUpgradeCost() {
        return 70;
    }

    /**
     * Get the position of the tower.
     * @return Point2D
     */
    @Override
    public Point2D getPosition() {
        return (Point2D) position.clone();
    }

    /**
     * Set the position of the tower.
     * @param position the new position of the tower.
     * @return void
     */
    @Override
    public void setPosition(Point2D position) {
        this.position = position;
    }

    /**
     * Get the range of the tower.
     * @return int
     */
    @Override
    public int getRange() {
        return range;
    }

    /**
     * Attacks the enemies in range.
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
     * Get the type of the tower.
     * @return String
     */
    @Override
    public String getTowerType() {
        return this.getClass().getSimpleName();
    }

    /**
     * Get the width of the tower.
     * @return float
     */
    @Override
    public float getTowerWidth() {
        return towerWidth;
    }

    /**
     * Get the cost of the tower.
     * @return int
     */
    @Override
    public int getCost() {
        return cost;
    }

    /**
     * Get the attack speed of the tower.
     * @return int
     */
    @Override
    public int getAttackSpeed() {
        return attackSpeed;
    }

    /**
     * Get the damage of the tower.
     * @return int
     */
    @Override
    public int getDamage() {
        return damage;
    }

    /**
     * Notify the tower of an enemy in range.
     * @param enemy the enemy in range.
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
     * Notify the tower of an enemy death.
     * @param enemy the enemy that died.
     * @return void
     */
    @Override
    public void notifyOfDeath(enemies enemy) {
        targets.remove(enemy);
    }

    /**
     * Check if the enemy is in range.
     * @param enemy the enemy to check.
     * @return boolean
     */
    private boolean checkIfInRange(enemies enemy) {
        return position.distance(enemy.getPosition()) <= this.range;
    }

    /**
     * Change the targeting type of the tower.
     * @param targetingtype the new targeting type.
     * @return void
     */
    public void changeTargeting(Targetings targetingtype) {
        targeting = targetingtype;
    }


    }

