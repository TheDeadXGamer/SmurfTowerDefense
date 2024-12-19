package com.group34.Model.Enemy;

import com.group34.Model.Road.RoadToken;

public class Balthazar extends BaseEnemy{
    private static int speed = 2;
    private static int health = 45;
    private static int reward = 20;

    public Balthazar( 
        RoadToken point
    ) {
        super(health, speed, reward, point);
    }

}
