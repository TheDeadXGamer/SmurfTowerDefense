package com.group34.Model.Enemy;

import com.group34.Model.Road.RoadToken;

public class AzraelFactory extends EnemyFactory {

    public static final int spawnValue = 10;
    
    /**
     * Creates an Azrael enemy
     * @param token RoadToken to spawn the enemy on
     * @return Enemy
     */
    @Override
    public Enemy createEnemy(RoadToken token) {
        Azrael azrael = new Azrael(token);
        return azrael;
    }

    /**
     * Returns the spawn value of the enemy
     * @return int
     */
    @Override
    public int getSpawnValue() {
        return spawnValue;
    }
}
