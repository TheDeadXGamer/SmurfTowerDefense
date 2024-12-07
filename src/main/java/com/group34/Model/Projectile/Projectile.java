package com.group34.Model.Projectile;

import java.awt.*;
import java.awt.geom.Point2D;

public interface Projectile {

    double getSpeed();
    Point2D getCurrentPosition();
    int getDamage();

    public String getProjectileType();

    public double getAngle();
    public void update();

    public Point2D getTargetPosition();
}
