package com.group34.Model.Tower;

import com.group34.GameSpeed;
import com.group34.Model.Enemy.Attackable;
import com.group34.Model.Positionable;
import com.group34.Model.Projectile.LightningBoltFactory;
import com.group34.Model.Tower.Targeting.ClosestAttack;
import com.group34.Model.Tower.Targeting.Targetings;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class ThunderSmurf<enemies extends Positionable & Attackable> implements Attack<enemies> {
    private int attackSpeed;
    private int damage;
    private Point2D position;
    private int range;
    private int cost;
    private List<enemies> targets = new ArrayList<>();
    private boolean canAttack = true;
    private float lastAttack = System.nanoTime();
    private float towerWidth;

    Targetings targeting;

    public ThunderSmurf(Point2D position) {
        this.attackSpeed = 2;
        this.damage = 7;
        this.range = 200;
        this.position = position;
        this.cost = 50;
        this.targeting = new ClosestAttack(new LightningBoltFactory(this),position);
    }

    /**
     * Returns the position of the tower
     * @return the position of the tower
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
     * Upgrades the tower and returns the new tower
     * @return the new tower
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
     * Returns the type of the tower as a String
     * @return the type of the tower as a String
     */
    @Override
    public String getTowerType() {
        return this.getClass().getSimpleName();
    }

    @Override
    public float getTowerWidth() {
        return towerWidth;
    }

    @Override
    public int getCost() {
        return cost;
    }

    @Override
    public void notifyTower(enemies enemy) {
        if (checkIfInRange(enemy) && !targets.contains(enemy)) {
            targets.add(enemy);
        } else if (!checkIfInRange(enemy) && targets.contains(enemy)) {
            targets.remove(enemy);
        }
    }
    @Override
    public void notifyOfDeath(enemies enemy) {
        if (targets.contains(enemy)) {
            targets.remove(enemy);
        }
    }

    /**
     * Determines if the specified enemy is within the range of the tower.
     * <p>
     * This method calculates the distance between the tower's position
     * and the enemy's position and checks if the distance is less than
     * or equal to the tower's range. If the enemy is within the range,
     * it returns {@code true}; otherwise, it returns {@code false}.
     * </p>
     *
     * @param enemy the enemy whose position is checked to determine if it is within the tower's range.
     *              The enemy object must provide its position through the {@code getPosition()} method.
     * @return {@code true} if the enemy is within the range of the tower;
     *         {@code false} otherwise.
     */
    private boolean checkIfInRange(enemies enemy) {
        return position.distance(enemy.getPosition()) <= this.range;
    }

    @Override
    public Tower upgrade() {
        return null;
    }

    @Override
    public int getUpgradeCost() {
        return 0;
    }
}
