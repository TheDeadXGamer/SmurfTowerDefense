package com.group34.Model.Projectile;

import java.awt.*;
import java.awt.geom.Point2D;

public interface Projectile {

    int getSpeed();
    Point2D getCurrentPosition();
    int getDamage();

    Image getArt();

    public double getAngle();
    public void update();

    public Point2D getTargetPosition();
}
