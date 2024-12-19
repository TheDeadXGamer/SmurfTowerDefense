package com.group34;

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

import com.group34.Model.Road.RoadValidator;
import com.group34.View.ViewConstants;

public class MapRoadCreatorTool extends JFrame {
    private BufferedImage mapImage;
    private MapPanel mapPanel;
    private List<Point2D> points = new ArrayList<Point2D>();
    private Map<Double, Double> scalablePoints = new LinkedHashMap<>();
    private JButton finishButton;

    // TODO: needs more comments

    public MapRoadCreatorTool() {
        super("Map Road Creator Tool");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(ViewConstants.GAME_WIDTH, ViewConstants.GAME_HEIGHT);

        mapPanel = new MapPanel();
        add(mapPanel);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        finishButton = new JButton("Finish Path");
        finishButton.addActionListener(e -> validateAndFinishPath());
        buttonPanel.add(finishButton);

        setLayout(new BorderLayout());
        add(mapPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        loadImage();
        setVisible(true);
    }

    private void validateAndFinishPath() {
        if (points.size() < 2) {
            JOptionPane.showMessageDialog(this,
                    "Path must have at least 2 points.");
            return;
        }

        Map.Entry<Double, Double> lastPoint = null;
        for (Map.Entry<Double, Double> entry : scalablePoints.entrySet()) {
            lastPoint = entry;
        }

        if (lastPoint != null && RoadValidator.isValidBorderPoint(lastPoint.getKey(), lastPoint.getValue())) {
            // Path is valid - TODO: add code to save/export the path
            JOptionPane.showMessageDialog(this, "Path is valid and complete!");
        } else {
            JOptionPane.showMessageDialog(this,
                    "Last point must be on a border.");
        }
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
        double xPercentage = (e.getX() / (double)mapPanel.getWidth());
        double yPercentage = (e.getY() / (double)mapPanel.getHeight());

        System.out.println("Click at: (" + e.getX() + ", " + e.getY() + ")");
        System.out.println("Percentage: (" + xPercentage + ", " + yPercentage + ")");

        if (points.isEmpty()) {
            // First point - must be on a border
            if (RoadValidator.isValidBorderPoint(xPercentage, yPercentage)) {
                addPoint(e.getX(), e.getY());
                addScalablePoint(e.getX(), e.getY());
                mapPanel.repaint();
            } else {
                JOptionPane.showMessageDialog(this,
                        "First point must be on a border.");
            }
        } else {
            // Check if this is meant to be the last point (near a border)
            boolean isLastPoint = RoadValidator.isValidBorderPoint(xPercentage, yPercentage);

            Map<Double, Double> tempPoints = new LinkedHashMap<>(scalablePoints);
            tempPoints.put(xPercentage, yPercentage);

            if (RoadValidator.isValidPercentageRoad(tempPoints, isLastPoint)) {
                addPoint(e.getX(), e.getY());
                addScalablePoint(e.getX(), e.getY());
                mapPanel.repaint();
            } else {
                if (isLastPoint) {
                    JOptionPane.showMessageDialog(this,
                            "Invalid path configuration.");
                }
            }
        }
    }

    private void addPoint(double x, double y){
        Point2D point = new Point2D.Double(x, y); // Raw coordinates
        points.add(point);
    }

    private void addScalablePoint(double x, double y){
        double xPercentage = x / mapPanel.getWidth();
        double yPercentage = y / mapPanel.getHeight();
        scalablePoints.put(xPercentage, yPercentage);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MapRoadCreatorTool();
        });
    }
}