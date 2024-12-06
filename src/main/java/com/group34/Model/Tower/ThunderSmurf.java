package com.group34.Model.Tower;


import com.group34.Model.Enemy.Enemy;
import com.group34.Model.Projectile.LightningBoltFactory;
import com.group34.Model.Tower.Targeting.ClosestAttack;
import com.group34.Model.Tower.Targeting.Targetings;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class ThunderSmurf implements Attack {
    protected int attackSpeed;
    protected int damage;

    private Boolean canAttack = true;

    private float lastAttackTime = 0;
    protected Point2D position;
    protected int range;
    List<Enemy> targets = new ArrayList<>();
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
    public int getRange() {
        return range;
    }

    @Override
    public void action() {
        if (System.nanoTime() - lastAttackTime >=  (Math.pow(10,9)) / attackSpeed) {
            canAttack = true;
        }
        if (canAttack) {

            targeting.attack(targets);
            canAttack = false;
            lastAttackTime = System.nanoTime();

        }
    }

    @Override
    public void checkInRange(Enemy enemy) {
        if (position.distance(enemy.getPosition()) <= range && !targets.contains(enemy)) {
            targets.add(enemy);

        }
        else if (position.distance(enemy.getPosition()) > range && targets.contains(enemy)) {
            targets.remove(enemy);
        }
    }

    @Override
    public void notifyAllTowers(Enemy enemy) {
        checkInRange(enemy);
    }
}
