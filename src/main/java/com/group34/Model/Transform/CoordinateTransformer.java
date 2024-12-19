package com.group34.Model.Transform;

import java.awt.Dimension;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class CoordinateTransformer {
    private double scaleX;
    private double scaleY;
    private final Dimension baseResolution;
    private Dimension currentResolution;
    private final List<ViewportChangeListener> listeners = new ArrayList<>();

    public CoordinateTransformer(Dimension baseResolution) {
        this.baseResolution = baseResolution;
        this.currentResolution = baseResolution;
        updateScaleFactors();
    }

    public void updateViewport(Dimension newResolution) {
        this.currentResolution = newResolution;
        updateScaleFactors();
        notifyViewportChanged();
    }

    private void updateScaleFactors() {
        // Calculate scale factors while maintaining aspect ratio
        double scaleWidth = currentResolution.getWidth() / baseResolution.getWidth();
        double scaleHeight = currentResolution.getHeight() / baseResolution.getHeight();

        // Use the smaller scale to maintain aspect ratio
        double scale = Math.min(scaleWidth, scaleHeight);

        this.scaleX = scale;
        this.scaleY = scale;
    }

    public Point2D toScreen(Point2D gamePosition) {
        return new Point2D.Double(
                gamePosition.getX() * scaleX,
                gamePosition.getY() * scaleY
        );
    }

    public Point2D toGame(Point2D screenPosition) {
        return new Point2D.Double(
                screenPosition.getX() / scaleX,
                screenPosition.getY() / scaleY
        );
    }

    public double scaleValue(double value) {
        return value * Math.min(scaleX, scaleY);
    }

    public void addViewportChangeListener(ViewportChangeListener listener) {
        listeners.add(listener);
    }

    private void notifyViewportChanged() {
        for (ViewportChangeListener listener : listeners) {
            listener.onViewportChanged(scaleX, scaleY);
        }
    }

    public Dimension getCurrentResolution() {
        return new Dimension(currentResolution);
    }

    public double getScale() {
        return Math.min(scaleX, scaleY);
    }
}