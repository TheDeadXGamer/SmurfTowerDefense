package com.group34.Model.Enemy;

import java.awt.geom.Point2D;

public interface Enemy {

    int getHealth();

    int getSpeed();

    int getReward();    

    Point2D getPosition();

    void damage(int damage);

    void move();

}
