package com.group34.Model.Enemy;

import java.awt.geom.Point2D;

// Concrete Factory 
public class EnemyFactory implements EnemyFactoryInterface {
    @Override
    public BaseEnemy createGargamel(Point2D position) {
        return new EnemyGargamel();
    }
}
