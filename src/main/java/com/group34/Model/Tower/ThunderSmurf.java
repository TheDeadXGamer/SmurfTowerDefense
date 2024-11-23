package com.group34.Model.Tower;

import com.group34.Model.Enemy.BaseEnemy;

import java.awt.geom.Point2D;
import java.util.stream.Stream;

public class ThunderSmurf implements Attack{
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
    public void attack(Stream<BaseEnemy> enemy) {

    }

    @Override
    public Point2D getPosition() {
        return new Point2D.Double(position.getX(),position.getY());
    }

    @Override
    public int getRange() {
        return range;
    }
}
