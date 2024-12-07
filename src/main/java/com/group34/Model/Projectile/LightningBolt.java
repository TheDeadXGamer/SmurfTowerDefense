package com.group34.Model.Projectile;

import com.group34.Model.Enemy.Enemy;
import com.group34.Model.Enemy.Positionable;

import java.awt.*;
import java.awt.geom.Point2D;

public class LightningBolt implements Projectile {

    private double speed;
    private Positionable enemy;
    private Point2D currentPosition;
    private int damage;
    private String projectileType;
    private double lastAngle = 0;



    public LightningBolt(double speed, Point2D startPosition, int damage, String projectileType, Positionable enemy) {
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
    public double getAngle() {
        // Calculate the differences in x and y
        double deltaX = getTargetPosition().getX() - getCurrentPosition().getX();
        double deltaY = getTargetPosition().getY() - getCurrentPosition().getY();

        // Get the angle using atan2, which returns a value from -π to π
        double angle = Math.atan2(deltaY, deltaX);



        return angle;
    }

    @Override
    public void update() {

        lastAngle += 0.000000000000000000000000000001*(lastAngle - getAngle());

        //Calculate how far to move projectile in each axis
        double deltaX = Math.cos(getAngle()) * speed;
        double deltaY = Math.sin(getAngle()) * speed;

        currentPosition = new Point2D.Double(currentPosition.getX() + deltaX,currentPosition.getY() + deltaY);
    }

    @Override
    public Point2D getTargetPosition() {
        return enemy.getPosition();
    }
}
