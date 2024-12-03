// EnemyPath.java
package com.group34.Model.Enemy;

import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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

    /**
     * Initialize waypoints for the enemy to follow.
     * Waypoints are stored as percentages of the window size.
     * @param filePath The filepath to the txt file containing the waypoints.
     */
    private void initializeWaypoints(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                double xPercent = Double.parseDouble(parts[0].trim());
                double yPercent = Double.parseDouble(parts[1].trim());
                addWaypoint(xPercent, yPercent);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Add a waypoint to the enemy's path.
     * @param xPercent The x-coordinate as a percentage of the window width.
     * @param yPercent The y-coordinate as a percentage of the window height.
     */
    private void addWaypoint(double xPercent, double yPercent) {
        double x = xPercent * DEFAULT_WIDTH;
        double y = yPercent * DEFAULT_HEIGHT;
        waypoints.offer(new Point2D.Double(x, y));
    }

    /**
     * @return The waypoints for the enemy to follow.
     */
    public Queue<Point2D> getWaypoints() {
        return waypoints;
    }
}