package com.group34.Model.Tower;


import com.group34.Model.Enemy.Enemy;
import com.group34.Model.Projectile.LightningBoltFactory;
import com.group34.Model.Tower.Targeting.ClosestAttack;
import com.group34.Model.Tower.Targeting.Targetings;

import javax.swing.*;
import java.awt.geom.Point2D;
import java.util.List;

public class ThunderSmurf implements Attack {
    protected int attackSpeed;
    protected int damage;
    protected Point2D position;
    protected int range;
    protected int cost;
    List<Enemy> targets;


    Targetings targeting = new ClosestAttack(new LightningBoltFactory(this),position); //TODO Den ska ha annan projectile men lägger såhär undertiden

    public ThunderSmurf(Point2D position, int attackSpeed, int damage, int range) {

        this.attackSpeed = attackSpeed;
        this.damage = damage;
        this.range = range;
        this.position = position;
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
        targeting.attack(targets);
    }

    @Override
    public String getTowerType() {
        return "ThunderSmurf";
    }

    @Override
    public String getTowerImagePath() {
        return ""; // no image yet
    }

    @Override
    public int getCost() {
        return 1000;
    }
}
