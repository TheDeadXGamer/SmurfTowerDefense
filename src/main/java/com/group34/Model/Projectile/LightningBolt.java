package com.group34.Model.Projectile;

import java.awt.*;
import java.awt.geom.Point2D;

public class LightningBolt implements ProjectileInterface{

    int Speed;
    Point2D position;
    int Damage;
    Image art;


    public  LightningBolt(int speed, Point2D position, int damage, Image art) {
        this.Speed = speed;
        this.position = position;
        this.Damage = damage;
        this.art = art;

    }
    @Override
    public int getSpeed() {
        return this.Speed;
    }

    @Override
    public Point2D getPosition() {
        return this.position;
    }

    @Override
    public int getDamage() {
        return this.Damage;
    }

    @Override
    public Image getArt() {
        return this.art;
    }
}
