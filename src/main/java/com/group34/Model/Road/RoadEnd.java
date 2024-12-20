package com.group34.Model.Road;

import java.awt.geom.Point2D;

import com.group34.Model.Game.Player;

public class RoadEnd implements Road {
    Player player;
    Point2D position;

    public RoadEnd(Point2D position, Player player) {
        this.player = player;
        this.position = position;
    }

    @Override
    public void advance(RoadToken token, int distance) {
        assert distance > 0;
        token.isFinished = true;
        player.reduceHealth(1);
    }

    @Override
    public Point2D getPosition(int distance) {
        return position;
    }

}
