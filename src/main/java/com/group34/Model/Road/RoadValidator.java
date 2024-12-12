package com.group34.Model.Road;

import java.awt.geom.Point2D;
import java.util.List;
import java.util.Map;

public class RoadValidator {
    public static final double BORDER_TOLERANCE = 0.10; // 10% tolerance for percentages
    public static final double COORDINATE_TOLERANCE = 50.0; // pixel tolerance for actual coordinates

    public static boolean isValidRoad(List<Point2D> points, double width, double height) {
        if (points.size() < 2) {
            return false;
        }

        // Check all points are within bounds
        for (Point2D point : points) {
            if (point.getX() < 0 || point.getX() > width ||
                    point.getY() < 0 || point.getY() > height) {
                return false;
            }
        }

        Point2D start = points.getFirst();
        Point2D end = points.getLast();

        boolean startOnBorder = isOnBorderX(start.getX(), width) || isOnBorderY(start.getY(), height);
        boolean endOnBorder = isOnBorderX(end.getX(), width) || isOnBorderY(end.getY(), height);

        return startOnBorder && endOnBorder;
    }

    public static boolean isValidPercentageRoad(Map<Double, Double> points, boolean isLastPoint) {
        if (points.size() < 2) {
            return false;
        }

        // Check all percentages are within 0-1
        for (Map.Entry<Double, Double> point : points.entrySet()) {
            if (point.getKey() < 0 || point.getKey() > 1 ||
                    point.getValue() < 0 || point.getValue() > 1) {
                return false;
            }
        }

        // Get first point
        Map.Entry<Double, Double> start = points.entrySet().iterator().next();
        boolean startOnBorder = isValidBorderPoint(start.getKey(), start.getValue());

        // If this is intended to be the last point, check if it's on a border
        if (isLastPoint) {
            Double lastKey = null;
            Double lastValue = null;
            for (Map.Entry<Double, Double> entry : points.entrySet()) {
                lastKey = entry.getKey();
                lastValue = entry.getValue();
            }
            boolean endOnBorder = isValidBorderPoint(lastKey, lastValue);
            return startOnBorder && endOnBorder;
        }

        // For intermediate points, just make sure start is valid and point is in bounds
        return startOnBorder;
    }

    // For percentage-based validation (used by MapRoadCreatorTool)
    public static boolean isValidBorderPoint(double x, double y) {
        boolean xNearZero = x <= BORDER_TOLERANCE;
        boolean xNearMax = x >= (1 - BORDER_TOLERANCE);
        boolean yNearZero = y <= BORDER_TOLERANCE;
        boolean yNearMax = y >= (1 - BORDER_TOLERANCE);

        return xNearZero || xNearMax || yNearZero || yNearMax;
    }

    // For coordinate-based validation (used by RoadBuilder)
    private static boolean isOnBorderX(double coordinate, double maxValue) {
        return coordinate <= COORDINATE_TOLERANCE ||
                coordinate >= (maxValue - COORDINATE_TOLERANCE);
    }

    private static boolean isOnBorderY(double coordinate, double maxValue) {
        return coordinate <= COORDINATE_TOLERANCE ||
                coordinate >= (maxValue - COORDINATE_TOLERANCE);
    }
}