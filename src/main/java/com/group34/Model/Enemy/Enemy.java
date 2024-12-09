package com.group34.Model.Enemy;

import com.group34.Model.Positionable;

import java.awt.geom.Point2D;

public interface Enemy extends Positionable,Attackable {

    int getHealth();

    int getSpeed();

    int getReward();    





    void move();

}
