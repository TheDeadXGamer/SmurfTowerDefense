package com.group34.Model.Road;

import java.awt.geom.Point2D;
import java.util.List;
import java.util.Map;

public class RoadValidator {
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

        // Check start and end points are on borders
        Point2D start = points.get(0);
        Point2D end = points.get(points.size() - 1);

        boolean startOnBorder = start.getX() == 0 || start.getY() == 0 ||
                start.getX() == width || start.getY() == height;

        boolean endOnBorder = end.getX() == 0 || end.getY() == 0 ||
                end.getX() == width || end.getY() == height;

        return startOnBorder && endOnBorder;
    }

    public static boolean isValidPercentageRoad(Map<Double, Double> points) {
        if (points.size() < 2) {
            return false;
        }

        // Check all percentages are within 0-100
        for (Map.Entry<Double, Double> point : points.entrySet()) {
            if (point.getKey() < 0 || point.getKey() > 100 ||
                    point.getValue() < 0 || point.getValue() > 100) {
                return false;
            }
        }

        // Get first and last points
        Map.Entry<Double, Double> start = points.entrySet().iterator().next();
        Double lastKey = null;
        Double lastValue = null;
        for (Map.Entry<Double, Double> entry : points.entrySet()) {
            lastKey = entry.getKey();
            lastValue = entry.getValue();
        }

        // Check start and end points are on borders (0 or 100)
        boolean startOnBorder = start.getKey() == 0 || start.getValue() == 0 ||
                start.getKey() == 100 || start.getValue() == 100;

        boolean endOnBorder = lastKey == 0 || lastValue == 0 ||
                lastKey == 100 || lastValue == 100;

        return startOnBorder && endOnBorder;
    }
}
