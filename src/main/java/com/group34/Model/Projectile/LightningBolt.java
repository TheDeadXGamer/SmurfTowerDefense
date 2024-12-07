package com.group34.Model.Projectile;

import java.awt.*;
import java.awt.geom.Point2D;

public class LightningBolt implements Projectile {

    private double speed;
    private Point2D startPosition;
    private Point2D targetDestination;
    private Point2D currentPosition;
    private int damage;
    private Image art;


    public LightningBolt(double speed, Point2D startPosition, int damage, Image art, Point2D targetDestination) {
        this.speed = speed;
        this.startPosition = startPosition;
        this.currentPosition = startPosition;
        this.damage = damage;
        this.art = art;
        this.targetDestination = targetDestination;

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
    public Image getArt() {
        return this.art;
    }

    @Override
    public double getAngle() {
        // Calculate the differences in x and y
        double deltaX = targetDestination.getX() - startPosition.getX();
        double deltaY = targetDestination.getY() - startPosition.getY();

        // Get the angle using atan2, which returns a value from -π to π
        double angle = Math.atan2(deltaY, deltaX);



        return angle;
    }

    @Override
    public void update() {

        //Calculate how far to move projectile in each axis
        double deltaX = Math.cos(getAngle()) * speed;
        double deltaY = Math.sin(getAngle()) * speed;

        currentPosition = new Point2D.Double(currentPosition.getX() + deltaX,currentPosition.getY() + deltaY);
    }

    @Override
    public Point2D getTargetPosition() {
        return targetDestination;
    }
}
