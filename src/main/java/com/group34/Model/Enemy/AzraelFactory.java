package com.group34.Model.Enemy;

import com.group34.Model.Road.RoadToken;

public class AzraelFactory extends EnemyFactory {
    
    @Override
    public Enemy createEnemy(RoadToken token) {
        Azrael azrael = new Azrael(token);
        return azrael;
    }
}
