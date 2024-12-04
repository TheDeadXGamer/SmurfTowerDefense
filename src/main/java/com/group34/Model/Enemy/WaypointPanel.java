// WaypointPanel.java
package com.group34.Model.Enemy;

import javax.swing.JPanel;
import javax.imageio.ImageIO;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

public class WaypointPanel extends JPanel {
    private Image backgroundImage;
    private FileWriter fileWriter;

    public WaypointPanel() {
        try {
            InputStream imageStream = getClass().getResourceAsStream("/assets/Maps/BaseMap.png");
            if (imageStream == null) {
                throw new IOException("Background image not found.");
            }
            backgroundImage = ImageIO.read(imageStream);
        } catch (IOException e) {
            System.out.println("Error loading background image");
        }

        try {
            fileWriter = new FileWriter("waypoints.txt", true); // Append mode
        } catch (IOException e) {
            System.out.println("Error opening file for writing");
        }

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                double xPercent = (double) x / getWidth();
                double yPercent = (double) y / getHeight();
                String coordinates = String.format("x=%.2f; y=%.2f%n", xPercent, yPercent);
                System.out.print(coordinates);

                try {
                    fileWriter.write(coordinates);
                    fileWriter.flush();
                } catch (IOException ex) {
                    System.out.println("Error writing to file");
                }
            }
        });
    }

    // Paint the background image
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

    // Close the file writer when the panel is removed
    @Override
    public void removeNotify() {
        super.removeNotify();
        try {
            if (fileWriter != null) {
                fileWriter.close();
            }
        } catch (IOException e) {
            System.out.println("Error closing file writer");
        }
    }
}