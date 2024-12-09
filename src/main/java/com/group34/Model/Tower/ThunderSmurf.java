package com.group34.Model.Tower;


import com.group34.Model.Enemy.Attackable;
import com.group34.Model.Enemy.Enemy;
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


    Targetings targeting = new ClosestAttack(new LightningBoltFactory(this),position); //TODO Den ska ha annan projectile men lägger såhär undertiden

    public ThunderSmurf(Point2D position) {
        this.attackSpeed = 1;
        this.damage = 5;
        this.range = 400;
        this.position = position;
        this.cost = 1000;
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
        if (System.nanoTime() - lastAttack >= (Math.pow(10,9) / attackSpeed )) {
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
        return "ThunderSmurf";
    }

    /**
     * Returns the path to the image equivalent of the tower
     * @return the path to the image equivalent of the tower
     */
    @Override
    public String getTowerImagePath() {
        return ""; // no image yet
    }

    /**
     * Returns the cost of the tower
     * @return the cost of the tower
     */
    @Override
    public int getCost() {
        return cost;
    }

    @Override
    public void notifyTower(enemies enemy) {
        if (checkIfInRange(enemy) && !targets.contains(enemy)) {
            targets.add(enemy);
        }
        else if (!checkIfInRange(enemy) && targets.contains(enemy)) {
            targets.remove(enemy);
        }
    }
    private boolean checkIfInRange(enemies enemy) {
        return position.distance(enemy.getPosition()) <= this.range;
    }
}
