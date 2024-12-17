package com.group34.Model.Enemy;


import com.group34.Model.Road.RoadToken;

public class GargamelFactory extends EnemyFactory {
    
    @Override
    public Enemy createEnemy(RoadToken token) {
        Gargamel gargamel = new Gargamel(token);
        return gargamel;
    }
    
}
