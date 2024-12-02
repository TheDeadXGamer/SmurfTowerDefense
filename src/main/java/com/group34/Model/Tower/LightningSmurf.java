package com.group34.Model.Tower;

import com.group34.Model.Enemy.Enemy;
import com.group34.Model.Projectile.LightningBoltFactory;
import com.group34.Model.Tower.Targeting.ClosestAttack;
import com.group34.Model.Tower.Targeting.Targetings;

import java.awt.geom.Point2D;
import java.util.List;

public class LightningSmurf implements Upgrade,Attack {

    protected int attackSpeed;
    protected int damage;

    protected Point2D position;
    protected int range;
    List<Enemy> targets;
    Targetings targeting = new ClosestAttack(new LightningBoltFactory(this), position);
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
        return "LightningSmurf";
    }

    @Override
    public String getTowerImagePath() {
        return "/assets/Towers/LightningSmurf.png";
    }

    @Override
    public int getCost() {
        return 500;
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
