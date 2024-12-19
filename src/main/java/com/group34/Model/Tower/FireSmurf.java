package com.group34.Model.Tower;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import com.group34.Model.Enemy.Attackable;
import com.group34.Model.Positionable;
import com.group34.Model.Projectile.FireballFactory;
import com.group34.Model.Tower.Targeting.ClosestAttack;
import com.group34.Model.Tower.Targeting.Targetings;

public class FireSmurf<enemies extends Positionable & Attackable> implements Attack<enemies> {
    protected int attackSpeed;
    protected int damage;
    private boolean canAttack = true;
    private float lastAttack = System.nanoTime();
    protected Point2D position;
    protected int range;
    List<enemies> targets = new ArrayList<>();
    Targetings targeting;
    private int cost;
    private float towerWidth;

    public FireSmurf(Point2D position, int attackSpeed, int damage, int range, float towerWidth, int cost) {
        this.attackSpeed = attackSpeed;
        this.damage = damage;
        this.range = range;
        this.position = position;
        this.targeting = new ClosestAttack(new FireballFactory(this), position);
        this.towerWidth = towerWidth;
        this.cost = cost;
    }

    @Override
    public Attack upgrade() {
        return new BlazeSmurf(this.position);
    }

    @Override
    public Point2D getPosition() {
        return (Point2D) position.clone();
    }

    @Override
    public void setPosition(Point2D position) {
        this.position = position;
    }

    @Override
    public int getRange() {
        return range;
    }

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
    public int getAttackSpeed() {
        return attackSpeed;
    }

    @Override
    public int getDamage() {
        return damage;
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
        targets.remove(enemy);
    }

    private boolean checkIfInRange(enemies enemy) {
        return position.distance(enemy.getPosition()) <= this.range;
    }

    public void changeTargeting(Targetings targetingtype) {
        targeting = targetingtype;
    }
}
