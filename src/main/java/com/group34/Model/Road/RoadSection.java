package com.group34.Model.Road;


import java.awt.geom.Point2D;

public class RoadSection implements Road {
    private Point2D start;
    private RoadSection child;
    // Angles and cruve could be delagated to views. 
    private float angle;
    private int distance;


    public RoadSection(Point2D point, RoadSection child) {
        this.start = point;
        this.child = child;
        this.distance = (int) Math.abs(child.getStart().distance(point));
    }

    public Point2D getStart() {
        return new Point2D.Double(this.start.getX(), this.start.getY());
    }

    public RoadToken advance(RoadToken token, int distance) {
        if (distance > this.distance) {
            return new RoadToken(child,
                distance + token.distance - this.distance
            );
        } else {
            token.distance += distance;
            return token;
        }
    }

    public Point2D getPosition(int distance) {
        Point2D point = new Point2D.Double(distance, 0);
        Point2D rotatedPoint = rotate(point, angle);
        return new Point2D.Double(
            rotatedPoint.getX() + start.getX(),
            rotatedPoint.getY() + start.getY()
        );

    }

    public static Point2D rotate(Point2D point, double angle) {
        double x = point.getX();
        double y = point.getY();

        // Compute cos and sin of the angle
        double cos = Math.cos(angle);
        double sin = Math.sin(angle);

        // Rotate the point using complex multiplication
        double newX = x * cos - y * sin;
        double newY = x * sin + y * cos;

        return new Point2D.Double(newX, newY);
    }


}
