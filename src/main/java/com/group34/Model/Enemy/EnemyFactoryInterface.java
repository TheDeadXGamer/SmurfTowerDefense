package com.group34.Model.Enemy;

import com.group34.Model.Enemy.BaseEnemy;

import java.awt.geom.Point2D;

interface EnemyFactoryInterface {
    BaseEnemy createGargamel(Point2D position);
}
