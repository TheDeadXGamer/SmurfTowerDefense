package com.group34.Model.Road;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import com.group34.Model.Board.Board;
import com.group34.Model.Game.Player;

/**
 * This class is used to build a road by adding points to the road.
 */
public class RoadBuilder {
    private final Board board;
    private final Player player;
    private final List<Point2D> points = new ArrayList<>();

    public RoadBuilder(Board board, Player player) {
        this.board = board;
        this.player = player;
    }

    /**
     * Adds a point to the road.
     * @param point The point to add.
     * @return The road builder.
     */
    public RoadBuilder add(Point2D point) {
        points.add(point);
        return this;
    }

    /**
     * Builds the road.
     * @return The road.
     * @throws InvalidRoadException If the road is invalid.
     */
    public RoadSpawn build() throws InvalidRoadException {
        if (!RoadValidator.isValidRoad(points, board.getDimension().getWidth(),
                board.getDimension().getHeight())) {
            throw new InvalidRoadException("Invalid road configuration");
        }

        RoadEnd end = new RoadEnd(points.get(points.size() - 1), player);
        Road child = end;

        for (int i = points.size() - 1; i >= 0; i--) {
            child = new RoadSection(points.get(i), child);
        }

        return new RoadSpawn(points.get(0), child);
    }
}