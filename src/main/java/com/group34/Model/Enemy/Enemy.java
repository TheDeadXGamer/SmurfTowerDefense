package com.group34.Model.Enemy;

import java.awt.geom.Point2D;

public interface Enemy extends Positionable {

    int getHealth();

    int getSpeed();

    int getReward();    


    void damage(int damage);

    void move();

}
