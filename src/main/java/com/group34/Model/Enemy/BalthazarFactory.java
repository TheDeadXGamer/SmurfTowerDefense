package com.group34.Model.Enemy;

import com.group34.Model.Road.RoadToken;

public class BalthazarFactory extends EnemyFactory{
    public static final int spawnValue = 5;
    @Override
    public Enemy createEnemy(RoadToken token) {
        Balthazar balthazar = new Balthazar(token);
        return balthazar;
    }
    @Override
    public int getSpawnValue() {
        return spawnValue;
    }
}
