package com.group34.Model.Tower;


import java.awt.geom.Point2D;
public class ThunderSmurf implements ClosestAttack {
    protected int attackSpeed;
    protected int damage;

    protected Point2D position;
    protected int range;


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
    public int getRange() {
        return range;
    }
}
