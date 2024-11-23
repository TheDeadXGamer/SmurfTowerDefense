package com.group34.Model.Tower;

import com.group34.Model.Enemy.BaseEnemy;
import com.group34.Model.Projectile.LightningBolt;
import com.group34.Model.Projectile.ProjectileInterface;

import java.awt.geom.Point2D;
import java.util.stream.Stream;

public class LightningSmurf implements Upgrade, Attack {

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
        return new Point2D.Double(position.getX(),position.getY());
    }


    @Override
    public int getRange() {
        int copy = range;
        return copy;
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


}
