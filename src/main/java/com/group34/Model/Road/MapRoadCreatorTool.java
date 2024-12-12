package com.group34.Model.Road;

import javax.swing.*;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;
import java.util.List;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MapRoadCreatorTool extends JFrame {
    private BufferedImage mapImage;
    private MapPanel mapPanel;
    private List<Point2D> points = new ArrayList<Point2D>();
    private Map<Double, Double> scalablePoints = new LinkedHashMap<>();

    public MapRoadCreatorTool() {
        super("Map Road Creator Tool");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        mapPanel = new MapPanel();
        add(mapPanel);

        loadImage();
        setVisible(true);
    }

    private class MapPanel extends JPanel {
        private MapPanel() {
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    mouseClicker(e);
                }
            });
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (mapImage != null) {
                g.drawImage(mapImage, 0, 0, this.getWidth(), this.getHeight(), null);
            }

            if (points.size() > 1) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setStroke(new BasicStroke(2));
                for (int i = 0; i < points.size() - 1; i++) {
                    Point2D point1 = points.get(i);
                    Point2D point2 = points.get(i + 1);
                    g.setColor(Color.BLUE);
                    g.drawLine((int) point1.getX(), (int) point1.getY(),
                            (int) point2.getX(), (int) point2.getY());
                }
            }

            for(Point2D point : points){
                g.setColor(Color.RED);
                g.fillOval((int)point.getX(), (int)point.getY(), 10, 10);
            }
        }
    }

    private void loadImage() {
        try {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileFilter(new FileNameExtensionFilter("PNG Images", "png"));
            if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                mapImage = ImageIO.read(fileChooser.getSelectedFile());
                mapPanel.repaint();
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error loading image: " + e.getMessage());
        }
    }

    void mouseClicker(MouseEvent e) {
        addPoint(e.getX(), e.getY());
        addScalablePoint(e.getX(), e.getY());

        // Check if path is valid after each point addition
        if (!RoadValidator.isValidPercentageRoad(scalablePoints)) {
            JOptionPane.showMessageDialog(this,
                    "Warning: Current path configuration is invalid. Make sure start and end points are on borders.");
        }

        mapPanel.repaint();
    }

    private void addPoint(double x, double y){
        Point2D point = new Point2D.Double(x,y);
        points.add(point);
    }

    private void addScalablePoint(double x, double y){
        double xPercentage = (x / mapPanel.getWidth()) * 100;
        double yPercentage = (y / mapPanel.getHeight()) * 100;
        scalablePoints.put(xPercentage, yPercentage);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MapRoadCreatorTool();
        });
    }
}