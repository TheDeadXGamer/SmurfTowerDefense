package com.group34.Model.Enemy;

import com.group34.Model.Road.RoadToken;

public class GargamelFactory extends EnemyFactory {

    public static final int spawnValue = 1;

    /**
     * Create a new Gargamel
     * @param token the token to spawn the enemy on
     * @return the new Gargamel
     */
    @Override
    public Enemy createEnemy(RoadToken token) {
        Gargamel gargamel = new Gargamel(token);
        return gargamel;
    }

    /**
     * Get the spawn value of the Gargamel
     * @return the spawn value of the Gargamel
     */
    @Override
    public int getSpawnValue() {
        return spawnValue;
    }
}
