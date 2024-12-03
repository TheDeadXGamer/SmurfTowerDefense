package com.group34.Model.Enemy;

import java.awt.geom.Point2D;
import java.util.LinkedList;
import java.util.Queue;

public class PositionManager {
    private Point2D currentPosition;
    private Queue<Point2D> waypoints;
    private Point2D currentTarget;
    private double speed;

    public PositionManager(Point2D startPosition, Queue<Point2D> waypoints, double speed) {
        this.currentPosition = startPosition;
        this.waypoints = new LinkedList<>(waypoints);
        this.speed = speed;
        this.currentTarget = this.waypoints.poll();
    }


    /**
     * @param deltaTime the time passed since the last update
     */
    public void update(double deltaTime) {
        // Running a while loop since the enemy can reach multiple waypoints in a single frame
        while (currentTarget != null) {
            // Calculate direction to target
            double dx = currentTarget.getX() - currentPosition.getX();
            double dy = currentTarget.getY() - currentPosition.getY();
            double distance = Math.sqrt(dx * dx + dy * dy);

            if (distance < 1) { // Reached waypoint
                currentPosition = currentTarget;
                currentTarget = waypoints.poll();
                continue;
            }

            // Normalize direction and apply speed and delta time
            double moveX = (dx / distance) * speed * deltaTime;
            double moveY = (dy / distance) * speed * deltaTime;

            // Check if the next position would overshoot the target
            if (moveX * moveX + moveY * moveY > distance * distance) {
                currentPosition = currentTarget;
                currentTarget = waypoints.poll();
            } else {
                currentPosition = new Point2D.Double(
                        currentPosition.getX() + moveX,
                        currentPosition.getY() + moveY
                );
                break;
            }
        }
    }
}