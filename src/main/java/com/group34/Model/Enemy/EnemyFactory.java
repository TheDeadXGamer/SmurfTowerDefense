package com.group34.Model.Enemy;

import com.group34.Model.Road.RoadToken;

public abstract class EnemyFactory {

    public abstract Enemy createEnemy(RoadToken token);

}
