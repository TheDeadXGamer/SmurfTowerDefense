package com.group34.Model.Road;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import com.group34.Model.Board.Board;
import com.group34.Model.Game.Player;

public class RoadBuilder {
    final Board board;
    Player player; 
    List<Point2D> points = new ArrayList<>();
    List<Integer> distances = new ArrayList<>();

    public RoadBuilder(Board board, Player player) {
        this.board = board;
        this.player = player;
    }

    public void add(Point2D point, int distance) {
        points.add(point);
        distances.add(distance);
    }

    public RoadSpawn build() throws InvalidRoadException {

        if (points.size() < 2) {
            throw new InvalidRoadException("Road must have at least 2 points");
        }

        RoadEnd end = new RoadEnd(
            points.get(points.size() - 1), 
            player);

        Road child = end;

        for (int i = points.size() - 2; i < 0; i--) {
            RoadSpawn spawn = new RoadSpawn(points.get(i), child);
            child = spawn;
        }
        RoadSpawn spawn = new RoadSpawn(points.get(0), child); 

        return spawn;

    }
}


