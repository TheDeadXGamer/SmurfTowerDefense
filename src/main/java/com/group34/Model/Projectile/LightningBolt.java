package com.group34.Model.Projectile;

import com.group34.Model.Enemy.Attackable;
import com.group34.Model.Enemy.Killable;
import com.group34.Model.Positionable;

import java.awt.geom.Point2D;

public class LightningBolt<Target extends Positionable & Attackable & Killable> implements Projectile {
    private double speed;
    private Target enemy;
    private Point2D currentPosition;
    private int damage;
    private String projectileType;

    public LightningBolt(double speed, Point2D startPosition, int damage, String projectileType, Target enemy) {
        this.speed = speed;

        this.currentPosition = startPosition;
        this.damage = damage;
        this.projectileType = projectileType;
        this.enemy = enemy;

    }

    /**
     * Returns the speed of the projectile
     * @return the speed of the projectile
     */
    @Override
    public double getSpeed() {
        return this.speed;
    }

    /**
     * Returns the position of the projectile
     * @return the position of the projectile
     */
    @Override
    public Point2D getCurrentPosition() {
        return this.currentPosition;
    }

    /**
     * Returns the damage of the projectile
     * @return the damage of the projectile
     */
    @Override
    public int getDamage() {
        return this.damage;
    }

    /**
     * Returns the art of the projectile
     * @return the art of the projectile
     */
    @Override
    public String getProjectileType() {
        return this.projectileType;
    }

    @Override
    public void damage() {
        enemy.damage(damage);
        System.out.println("did " + damage + " damage!");
    }

    @Override
    public double getAngle() {
        // Calculate the differences in x and y
        double deltaX = getTargetPosition().getX() - getCurrentPosition().getX();
        double deltaY = getTargetPosition().getY() - getCurrentPosition().getY();

        // Get the angle using atan2, which returns a value from -π to π
        double angle = Math.atan2(deltaY, deltaX);



        return angle;
    }

    @Override
    public void updatePosition() {
        // Calculate how far to move projectile in each axis
        double deltaX = Math.cos(getAngle()) * speed;
        double deltaY = Math.sin(getAngle()) * speed;

        currentPosition = new Point2D.Double(currentPosition.getX() + deltaX,currentPosition.getY() + deltaY);
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
