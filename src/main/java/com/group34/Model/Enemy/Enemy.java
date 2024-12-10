package com.group34.Model.Enemy;

import com.group34.Model.Positionable;

public interface Enemy extends Positionable,Attackable {

    int getHealth();

    int getSpeed();

    int getReward();    

    void move();

    boolean isAlive();

}
