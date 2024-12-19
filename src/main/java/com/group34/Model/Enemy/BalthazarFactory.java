package com.group34.Model.Enemy;

import com.group34.Model.Road.RoadToken;

public class BalthazarFactory extends EnemyFactory{
    
    @Override
    public Enemy createEnemy(RoadToken token) {
        Balthazar balthazar = new Balthazar(token);
        return balthazar;
    }
}
