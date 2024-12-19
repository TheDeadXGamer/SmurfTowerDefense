package com.group34.Model.Transform;

import java.awt.*;

public class ViewportManager {
    private final CoordinateTransformer transformer;

    public ViewportManager(Dimension baseResolution) {
        this.transformer = new CoordinateTransformer(baseResolution);
    }

    /**
     * Handle a resize event by adjusting the viewport to maintain the aspect ratio
     * @param newSize The new size of the viewport
     */
    public void handleResize(Dimension newSize) {
        // Calculate the new size while maintaining aspect ratio
        double targetAspectRatio = transformer.getCurrentResolution().getWidth() /
                transformer.getCurrentResolution().getHeight();

        double currentAspectRatio = newSize.getWidth() / newSize.getHeight();
        Dimension adjustedSize;

        if (currentAspectRatio > targetAspectRatio) {
            // Width is too large, scale based on height
            int newWidth = (int)(newSize.getHeight() * targetAspectRatio);
            adjustedSize = new Dimension(newWidth, newSize.height);
        } else {
            // Height is too large, scale based on width
            int newHeight = (int)(newSize.getWidth() / targetAspectRatio);
            adjustedSize = new Dimension(newSize.width, newHeight);
        }

        transformer.updateViewport(adjustedSize);
    }

    public CoordinateTransformer getTransformer() {
        return transformer;
    }
}
