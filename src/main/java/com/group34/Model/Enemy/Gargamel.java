package com.group34.Model.Enemy;

import com.group34.Model.Road.RoadToken;

public class Gargamel extends BaseEnemy {
    private static int speed = 2;
    private static int health = 30;
    private static int reward = 10;

    public Gargamel( 
        RoadToken point
    ) {
        super(health, speed, reward, point);
    }
}
