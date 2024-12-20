package com.group34.Model.Road;

import java.awt.geom.Point2D;
import java.util.List;
import java.util.Map;

/**
 * RoadValidator is a utility class that provides methods to validate road points.
 */
public class RoadValidator {
    public static final double BORDER_TOLERANCE = 0.10; // 10% tolerance for percentages
    public static final double COORDINATE_TOLERANCE = 50.0; // pixel tolerance for actual coordinates

    /**
     * Checks if the road is valid. A road is valid if it has at least 2 points and both the start and end points are on the border.
     * @param points List of points that make up the road
     * @param width Width of the map
     * @param height Height of the map
     * @return True if the road is valid, false otherwise
     */
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


    /**
     * Checks if the road percentage points are valid.
     * @param points Map of points that make up the road
     * @param width Width of the map
     * @param height Height of the map
     * @param isLastPoint True if this is the last point, false otherwise
     * @return True if the road is valid, false otherwise
     */
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

    /**
     * Checks if a point is on the border of the map.
     * @param x X coordinate
     * @param y Y coordinate
     * @return True if the point is on the border, false otherwise
     */
    public static boolean isValidBorderPoint(double x, double y) {
        boolean xNearZero = x <= BORDER_TOLERANCE;
        boolean xNearMax = x >= (1 - BORDER_TOLERANCE);
        boolean yNearZero = y <= BORDER_TOLERANCE;
        boolean yNearMax = y >= (1 - BORDER_TOLERANCE);

        return xNearZero || xNearMax || yNearZero || yNearMax;
    }

    /**
     * Checks if a point is on the border of the map.
     * @param coordinate X coordinate
     * @param maxValue Maximum value for the coordinate
     * @return True if the point is on the border, false otherwise
     */
    private static boolean isOnBorderX(double coordinate, double maxValue) {
        return coordinate <= COORDINATE_TOLERANCE ||
                coordinate >= (maxValue - COORDINATE_TOLERANCE);
    }

    /**
     * Checks if a point is on the border of the map.
     * @param coordinate Y coordinate
     * @param maxValue Maximum value for the coordinate
     * @return True if the point is on the border, false otherwise
     */
    private static boolean isOnBorderY(double coordinate, double maxValue) {
        return coordinate <= COORDINATE_TOLERANCE ||
                coordinate >= (maxValue - COORDINATE_TOLERANCE);
    }
}