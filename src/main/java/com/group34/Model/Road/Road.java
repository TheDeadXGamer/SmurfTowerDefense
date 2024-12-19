package com.group34.Model.Road;

import java.awt.geom.Point2D;

interface Road {
    void advance(RoadToken token, int distance);

    Point2D getPosition(int distance);
}
