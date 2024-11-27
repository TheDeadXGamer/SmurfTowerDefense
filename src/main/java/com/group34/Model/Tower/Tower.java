package com.group34.Model.Tower;
import java.awt.geom.Point2D;

public interface Tower {
    public Point2D getPosition();
    public int getRange();

    public void action();
}
