package com.group34.Model.Enemy;

import java.awt.geom.Point2D;

// Concrete Factory 
public class EnemyFactory {

    private Point2D spawn;

    public EnemyFactory(Point2D spawn) {
        this.spawn = spawn;
    }

}
