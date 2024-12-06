package com.group34.Model.Projectile;

import java.awt.*;
import java.awt.geom.Point2D;

public class LightningBolt implements ProjectileInterface{

    private int speed;
    private Point2D position;
    private int damage;
    private Image art;


    public LightningBolt(int speed, Point2D position, int damage, Image art) {
        this.speed = speed;
        this.position = position;
        this.damage = damage;
        this.art = art;

    }

    /**
     * Returns the speed of the projectile
     * @return the speed of the projectile
     */
    @Override
    public int getSpeed() {
        return this.speed;
    }

    /**
     * Returns the position of the projectile
     * @return the position of the projectile
     */
    @Override
    public Point2D getPosition() {
        return this.position;
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
}
