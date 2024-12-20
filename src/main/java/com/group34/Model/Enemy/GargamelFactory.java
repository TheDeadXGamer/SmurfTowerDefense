package com.group34.Model.Enemy;

import com.group34.Model.Road.RoadToken;

public class GargamelFactory extends EnemyFactory {

    public static final int spawnValue = 1;
    @Override
    public Enemy createEnemy(RoadToken token) {
        Gargamel gargamel = new Gargamel(token);
        return gargamel;
    }
    @Override
    public int getSpawnValue() {
        return spawnValue;
    }
}
