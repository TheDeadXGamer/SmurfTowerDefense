package com.group34.Model.Tower;

import com.group34.Model.Enemy.Attackable;
import com.group34.Model.Positionable;
import com.group34.Model.Projectile.WaterdropFactory;
import com.group34.Model.Tower.Targeting.ClosestAttack;
import com.group34.Model.Tower.Targeting.Targetings;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class TsunamiSmurf<enemies extends Positionable & Attackable> implements Attack<enemies> {
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

    public TsunamiSmurf(Point2D position) {
        this.attackSpeed = 4;
        this.damage = 8;
        this.range = 220;
        this.position = position;
        this.cost = 75;
        this.towerWidth = 50;
        this.targeting = new ClosestAttack(new WaterdropFactory(this), position);
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

    @Override
    public Tower upgrade() {
        return null;
    }



}
