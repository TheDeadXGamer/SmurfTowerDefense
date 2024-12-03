// WaypointTool.java
package com.group34.Model.Pathway;

import javax.swing.JFrame;

public class WaypointTool extends JFrame {
    public WaypointTool() {
        setTitle("Waypoint Tool");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        WaypointPanel panel = new WaypointPanel();
        add(panel);

        setVisible(true);
    }

    public static void main(String[] args) {
        new WaypointTool();
    }
}