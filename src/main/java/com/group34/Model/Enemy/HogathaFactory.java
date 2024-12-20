package com.group34.Model.Enemy;

import com.group34.Model.Road.RoadToken;

public class HogathaFactory extends EnemyFactory {
    public static final int spawnValue = 3;

    /**
     * Creates a new Hogatha enemy
     * @param token RoadToken to spawn the enemy on
     * @return Hogatha enemy
     */
    @Override
    public Enemy createEnemy(RoadToken token) {
        Hogatha hogatha = new Hogatha(token);
        return hogatha;
    }

    /**
     * Returns the spawn value of the enemy
     * @return spawn value
     */
    @Override
    public int getSpawnValue() {
        return spawnValue;
    }
}
