package com.group34.Model.Enemy;

import com.group34.Model.Road.RoadToken;

public class Azrael extends BaseEnemy {

    private static int speed = 4;
    private static int health = 15;
    private static int reward = 5;

    public Azrael( 
        RoadToken point
    ) {
        super(health, speed, reward, point);
    }

}
