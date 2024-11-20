package com.group34.Model.Projectile;

import java.awt.*;
import java.awt.geom.Point2D;

public interface ProjectileInterface {

    int getSpeed();
    Point2D getPosition();
    int getDamage();

    Image getArt();
}
