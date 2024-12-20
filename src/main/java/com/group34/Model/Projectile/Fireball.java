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

    /**
     * Get the speed of the projectile
     * @return speed of the projectile
     */
    @Override
    public double getSpeed() {
        return this.speed;
    }

    /**
     * Get the current position of the projectile
     * @return current position of the projectile
     */
    @Override
    public Point2D getCurrentPosition() {
        return this.currentPosition;
    }

    /**
     * Get the damage of the projectile
     * @return damage of the projectile
     */
    @Override
    public int getDamage() {
        return this.damage;
    }

    /**
     * Get the type of the projectile
     * @return type of the projectile
     */
    @Override
    public String getProjectileType() {
        return this.projectileType;
    }

    /**
     * Damage the enemy
     * @return void
     */
    @Override
    public void damage() {
        enemy.damage(damage);
        System.out.println("Fireball did " + damage + " damage!");
    }

    /**
     * Get the angle of the projectile
     * @return angle of the projectile
     */
    @Override
    public double getAngle() {
        double deltaX = getTargetPosition().getX() - getCurrentPosition().getX();
        double deltaY = getTargetPosition().getY() - getCurrentPosition().getY();
        return Math.atan2(deltaY, deltaX);
    }

    /**
     * Update the position of the projectile
     * @return void
     */
    @Override
    public void updatePosition() {
        double deltaX = Math.cos(getAngle()) * speed;
        double deltaY = Math.sin(getAngle()) * speed;
        currentPosition = new Point2D.Double(currentPosition.getX() + deltaX, currentPosition.getY() + deltaY);
    }

    /**
     * Get the target position of the projectile
     * @return target position of the projectile
     */
    @Override
    public Point2D getTargetPosition() {
        return enemy.getPosition();
    }

    /**
     * Check if the target is dead
     * @return boolean
     */
    @Override
    public boolean IfTargetDead() {
        return !enemy.isAlive();
    }
}
