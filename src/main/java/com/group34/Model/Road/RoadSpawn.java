package com.group34.Model.Road;

import java.awt.geom.Point2D;

import com.group34.Model.Enemy.EnemyFactory;

public class RoadSpawn implements Road {
    private final  Point2D position;
    private final Road child;

    public RoadSpawn(Point2D position, Road child) {
        this.position = position;
        this.child = child;
    }

    public void spawn(EnemyFactory enemyFactory) {
        RoadToken token = new RoadToken(child);
        enemyFactory.createEnemy(token);
    }

    @Override
    public Point2D getPosition(int distance) {
        assert distance == 0;
        return position;
    }

    @Override
    public void advance(RoadToken token, int distance) {
        assert distance > 0;
        //System.out.println("RoadSpawn.advance");
        token.setRoadSection(child);
        token.distance = distance;
    }
}