package com.group34.Model.Projectile;

import com.group34.Model.Enemy.Attackable;
import com.group34.Model.Enemy.Killable;
import com.group34.Model.Positionable;

import java.awt.geom.Point2D;

public class Fireball<Target extends Positionable & Attackable & Killable> implements Projectile {
    private double speed;
    private Target enemy;
    private Point2D currentPosition;
    private int damage;
    private String projectileType;

    public Fireball(double speed, Point2D startPosition, int damage, String projectileType, Target enemy) {
        this.speed = speed;
        this.currentPosition = startPosition;
        this.damage = damage;
        this.projectileType = projectileType;
        this.enemy = enemy;
    }

    @Override
    public double getSpeed() {
        return this.speed;
    }

    @Override
    public Point2D getCurrentPosition() {
        return this.currentPosition;
    }

    @Override
    public int getDamage() {
        return this.damage;
    }

    @Override
    public String getProjectileType() {
        return this.projectileType;
    }

    @Override
    public void damage() {
        enemy.damage(damage);
        System.out.println("Fireball did " + damage + " damage!");
    }

    @Override
    public double getAngle() {
        double deltaX = getTargetPosition().getX() - getCurrentPosition().getX();
        double deltaY = getTargetPosition().getY() - getCurrentPosition().getY();
        return Math.atan2(deltaY, deltaX);
    }

    @Override
    public void updatePosition() {
        double deltaX = Math.cos(getAngle()) * speed;
        double deltaY = Math.sin(getAngle()) * speed;
        currentPosition = new Point2D.Double(currentPosition.getX() + deltaX, currentPosition.getY() + deltaY);
    }

    @Override
    public Point2D getTargetPosition() {
        return enemy.getPosition();
    }

    @Override
    public boolean IfTargetDead() {
        return !enemy.isAlive();
    }
}
