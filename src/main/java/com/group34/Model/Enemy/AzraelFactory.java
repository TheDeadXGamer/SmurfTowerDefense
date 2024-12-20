package com.group34.Model.Enemy;

import com.group34.Model.Road.RoadToken;

public class AzraelFactory extends EnemyFactory {

    public static final int spawnValue = 10;
    
    @Override
    public Enemy createEnemy(RoadToken token) {
        Azrael azrael = new Azrael(token);
        return azrael;
    }
    @Override
    public int getSpawnValue() {
        return spawnValue;
    }
}
