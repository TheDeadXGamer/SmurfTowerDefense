package com.group34.Model.Road;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class RoadBuilder {
    private int hight;
    private int width;
    List<Point2D> points = new ArrayList<>();
    List<Integer> distances = new ArrayList<>();

    public RoadBuilder(int height, int width) {

    }

    public void add(Point2D point, int distance) {
        points.add(point);
        distances.add(distance);
    }

    public void build() {

  

    }
}


