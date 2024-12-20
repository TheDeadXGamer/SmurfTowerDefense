package com.group34.Model.Projectile;

import com.group34.Model.Enemy.Attackable;
import com.group34.Model.Enemy.Killable;
import com.group34.Model.Positionable;

import java.awt.geom.Point2D;


/**
 * Waterdrop class is a projectile that is created by the WaterTower.
 * It is used to attack the enemies.
 * It implements the Projectile interface.
 * @param <Target> The target that the waterdrop is attacking.
 * @see Projectile
 */
public class Waterdrop<Target extends Positionable & Attackable & Killable> implements Projectile {
    private double speed;
    private Target enemy;
    private Point2D currentPosition;
    private int damage;
    private String projectileType;

    public Waterdrop(double speed, Point2D startPosition, int damage, String projectileType, Target enemy) {
        this.speed = speed;
        this.currentPosition = startPosition;
        this.damage = damage;
        this.projectileType = projectileType;
        this.enemy = enemy;
    }

    /**
     * Returns the speed of the waterdrop.
     * @return The speed of the waterdrop.
     */
    @Override
    public double getSpeed() {
        return this.speed;
    }

    /**
     * Returns the current position of the waterdrop.
     * @return The current position of the waterdrop.
     */
    @Override
    public Point2D getCurrentPosition() {
        return this.currentPosition;
    }

    /**
     * Returns the damage of the waterdrop.
     * @return The damage of the waterdrop.
     */
    @Override
    public int getDamage() {
        return this.damage;
    }

    /**
     * Returns the type of the projectile.
     * @return The type of the projectile.
     */
    @Override
    public String getProjectileType() {
        return this.projectileType;
    }

    /**
     * Damages the enemy by the damage of the waterdrop.
     * @return void
     */
    @Override
    public void damage() {
        enemy.damage(damage);
        System.out.println("Waterdrop did " + damage + " damage!");
    }

    /**
     * Returns the angle between the waterdrop and the enemy.
     * @return The angle between the waterdrop and the enemy.
     */
    @Override
    public double getAngle() {
        double deltaX = getTargetPosition().getX() - getCurrentPosition().getX();
        double deltaY = getTargetPosition().getY() - getCurrentPosition().getY();
        return Math.atan2(deltaY, deltaX);
    }

    /**
     * Updates the position of the waterdrop.
     * @return void
     */
    @Override
    public void updatePosition() {
        double deltaX = Math.cos(getAngle()) * speed;
        double deltaY = Math.sin(getAngle()) * speed;
        currentPosition = new Point2D.Double(currentPosition.getX() + deltaX, currentPosition.getY() + deltaY);
    }

    /**
     * Returns the target position of the waterdrop.
     * @return The target position of the waterdrop.
     */
    @Override
    public Point2D getTargetPosition() {
        return enemy.getPosition();
    }

    /**
     * Returns if the target is dead.
     * @return If the target is dead.
     */
    @Override
    public boolean IfTargetDead() {
        return !enemy.isAlive();
    }
}
