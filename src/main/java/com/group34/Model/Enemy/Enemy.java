package com.group34.Model.Enemy;

import java.awt.geom.Point2D;

public interface Enemy {

    int getHealth();

    int getSpeed();

    int getReward();    
    
    default String getName() {
        return this.getClass().getName();
    };

    Point2D getPosition();

    void damage(int damage);

    // How does movement relate to speed? Advance speed units per iteration? It will be coupled to frame rate? Add timeObject instead perhaps?
    //void move();

}
