package com.group34.Model.Road;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import com.group34.Model.Board.Board;
import com.group34.Model.Game.Player;

public class RoadBuilder {
    private final Board board;
    private final Player player; 
    private final List<Point2D> points = new ArrayList<>();

    public RoadBuilder(Board board, Player player) {
        this.board = board;
        this.player = player;
    }

    public RoadBuilder add(Point2D point) {
        points.add(point);
        return this;
    }

    public RoadSpawn build() throws InvalidRoadException {

        if (points.size() < 2) {
            throw new InvalidRoadException("Road must have at least 2 points");
        }

        for (Point2D point : points) {
            if (!withinDimension(point)) {
                throw new InvalidRoadException("Road point outside of board");
            }
        }

        Point2D start_point = points.get(0);
        Point2D end_point = points.get(points.size() - 1);

        if (
            start_point.getX() != 0 && 
            start_point.getY() != 0 &&
            start_point.getX() != board.getDimension().getWidth() &&
            start_point.getY() != board.getDimension().getHeight()
        ) {
            throw new InvalidRoadException("Road start point must be on border");
        }


        if (
            end_point.getX() != 0 &&
            end_point.getY() != 0 && 
            end_point.getX() != board.getDimension().getWidth() &&
            end_point.getY() != board.getDimension().getHeight()
        ) {
            throw new InvalidRoadException("Road end point must be on border");
        }


        RoadEnd end = new RoadEnd(
            points.get(points.size() - 1), 
            player);

        Road child = end;

        for (int i = points.size() - 1; i >= 0; i--) {
            child = new RoadSection(points.get(i), child);
         
        }

        RoadSpawn spawn = new RoadSpawn(points.get(0), child); 

        return spawn;

    }

    private boolean withinDimension(Point2D point) {
        double x = point.getX();
        double y = point.getY();
        boolean widthIsOk = x >= 0 && x <= board.getDimension().getWidth();
        boolean heightIsOk = y >= 0 && y <= board.getDimension().getHeight();
        return widthIsOk && heightIsOk;
    }

}


