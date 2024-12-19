package com.group34.Model.Projectile;

import java.awt.geom.Point2D;

public interface Projectile {
    double getSpeed();

    Point2D getCurrentPosition();

    int getDamage();

    String getProjectileType();

    void damage();

    double getAngle();

    void updatePosition();

    Point2D getTargetPosition();

    boolean IfTargetDead();
}
