package com.group34.Model.Road;

import java.awt.geom.Point2D;

/**
 * RoadSection class is a road that is a section of a road.
 * It implements the Road interface.
 * @see Road
 */
public class RoadSection implements Road {
    private final Point2D start;
    private final  Road child;
    private final float angle;
    private final int length;

    public RoadSection(Point2D point, Road child) {
        this.start = point;
        
        this.child = child;
        this.angle = (float) Math.atan2(
            child.getPosition(0).getY() - point.getY(),
            child.getPosition(0).getX() - point.getX()
        );
        
        this.length = (int) Math.
            abs(child.getPosition(0).distance(point));
    }

    /**
     * Returns the start position of the road.
     * @return The start position of the road.
     */
    public Point2D getStart() {
        return new Point2D.Double(this.start.getX(), this.start.getY());
    }

    /**
     * Advances the road token by a certain distance.
     * @param token The road token to advance.
     * @param distance The distance to advance the road token by.
     * @return void
     */
    @Override
    public void advance(RoadToken token, int amount) {
        if (token.distance + amount >= this.length) {
            token.setRoadSection(child);
            token.distance = token.distance + amount - this.length;
        } else {
            token.distance += amount;
        }
    }

    /**
     * Returns the position of the road at a certain distance.
     * @param distance The distance to get the position of the road at.
     * @return The position of the road at the distance.
     */
    @Override
    public Point2D getPosition(int distance) {
        Point2D point = new Point2D.Double(distance, 0);
        Point2D rotatedPoint = rotate(point, angle);
        return new Point2D.Double(
            rotatedPoint.getX() + start.getX(),
            rotatedPoint.getY() + start.getY()
        );

    }

    /**
     * Rotates a point by a certain angle.
     * @param point The point to rotate.
     * @param angle The angle to rotate the point by.
     * @return The rotated point.
     */
    private static Point2D rotate(Point2D point, double angle) {
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
