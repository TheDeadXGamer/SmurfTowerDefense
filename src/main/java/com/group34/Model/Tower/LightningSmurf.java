package com.group34.Model.Tower;

import java.awt.geom.Point2D;

public class LightningSmurf implements Upgrade, ClosestAttack {

    protected int attackSpeed;
    protected int damage;

    protected Point2D position;
    protected int range;


    public LightningSmurf(Point2D position, int attackSpeed, int damage, int range) {
        this.attackSpeed = attackSpeed;
        this.damage = damage;
        this.range = range;
        this.position = position;
    }

    @Override
    public Attack upgrade() {
        return new ThunderSmurf(this.position,1,5,400);
    }

    @Override
    public Point2D getPosition() {
        return (Point2D) position.clone();
    }


    @Override
    public int getRange() {
        return range;
    }

    @Override
    public int getAttackSpeed() {
        return attackSpeed;
    }

    @Override
    public int getDamage() {
        return damage;
    }


}
