package com.group34.Model.Enemy;

import java.awt.geom.Point2D;

// Factory class that creates enemies based on class type 
public class EnemyFactory {
    @Override
    public Enemy createGargamel(Point2D position) {
        return new Gargamel(position);
    }
}
