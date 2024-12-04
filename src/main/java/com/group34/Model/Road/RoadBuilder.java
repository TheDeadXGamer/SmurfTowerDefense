package com.group34.Model.Road;

import java.awt.geom.Point2D;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import com.group34.Model.Game.Player;

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

    public Road build() {

        return new RoadSection(points.get(0), null);

       

    }
}


