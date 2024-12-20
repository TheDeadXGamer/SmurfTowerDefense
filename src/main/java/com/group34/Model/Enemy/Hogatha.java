package com.group34.Model.Enemy;

import com.group34.Model.Road.RoadToken;

public class Hogatha extends BaseEnemy{
    private static int speed = 2;
    private static int health = 60;
    private static int reward = 45;

    public Hogatha( 
        RoadToken point
    ) {
        super(health, speed, reward, point);
    }

}