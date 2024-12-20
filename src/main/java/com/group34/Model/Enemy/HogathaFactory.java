package com.group34.Model.Enemy;

import com.group34.Model.Road.RoadToken;

public class HogathaFactory extends EnemyFactory {
    public static final int spawnValue = 3;
    @Override
    public Enemy createEnemy(RoadToken token) {
        Hogatha hogatha = new Hogatha(token);
        return hogatha;
    }
    @Override
    public int getSpawnValue() {
        return spawnValue;
    }
}
