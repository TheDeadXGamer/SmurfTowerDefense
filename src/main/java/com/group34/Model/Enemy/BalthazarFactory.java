package com.group34.Model.Enemy;

import com.group34.Model.Road.RoadToken;

public class BalthazarFactory extends EnemyFactory{
    public static final int spawnValue = 5;

    /**
     * Create a new Balthazar enemy
     * @param token the token that the enemy will spawn on
     * @return Enemy
     */
    @Override
    public Enemy createEnemy(RoadToken token) {
        Balthazar balthazar = new Balthazar(token);
        return balthazar;
    }

    /**
     * Get the spawn value of the enemy
     * @return int
     */
    @Override
    public int getSpawnValue() {
        return spawnValue;
    }
}
