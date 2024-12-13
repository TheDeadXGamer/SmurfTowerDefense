package com.group34.Model.Tower;

import com.group34.Model.Enemy.Attackable;
import com.group34.Model.Enemy.Enemy;
import com.group34.Model.Positionable;

public interface TowerListener<enemies extends Positionable & Attackable> {
    public void notifyTower(enemies enemy);
}
