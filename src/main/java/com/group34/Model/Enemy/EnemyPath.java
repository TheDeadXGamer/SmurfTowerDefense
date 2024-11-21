// EnemyPath.java
package com.group34.Model.Enemy;

import java.awt.geom.Point2D;
import java.util.LinkedList;
import java.util.Queue;

public class EnemyPath {
    private Queue<Point2D> waypoints;
    private static final int DEFAULT_WIDTH = 800;   // Default window size
    private static final int DEFAULT_HEIGHT = 600;  // Used for initial calculations

    public EnemyPath() {
        waypoints = new LinkedList<>();
        initializeWaypoints();
    }

    private void initializeWaypoints() {
        // Store waypoints using percentages but convert to actual coordinates
        addWaypoint(0.0, 0.4);    // Start at left, 40% down
        addWaypoint(0.2, 0.4);    // 20% from left, 40% down
        addWaypoint(0.3, 0.6);
        addWaypoint(0.5, 0.3);
        addWaypoint(0.7, 0.5);
        addWaypoint(1.0, 0.4);    // End at right edge
    }

    private void addWaypoint(double xPercent, double yPercent) {
        double x = xPercent * DEFAULT_WIDTH;
        double y = yPercent * DEFAULT_HEIGHT;
        waypoints.offer(new Point2D.Double(x, y));
    }

    // Returns a new copy of waypoints for each enemy
    public Queue<Point2D> getWaypoints() {
        return waypoints;
    }
}