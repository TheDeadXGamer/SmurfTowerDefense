package com.group34.Model.Board;

import java.awt.Dimension;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.group34.Model.Enemy.Enemy;
import com.group34.Model.Tower.Tower;

public class Board {

    private List<Tower> towers = new ArrayList<>();
    private float towerWidth = (float) 1.0;
    private Dimension dimension;

    public Board(Dimension dimension) {
        this.dimension = dimension;
    }

    public Dimension getDimension( ) {
        return dimension;
    }

    public int getTowerWidth() {
        return (int) towerWidth;
    }

    public Iterator<Tower> getTowers() {
        return towers.iterator();
    }

    public void addTower(Tower tower) throws PlacementError { 
   
        if (!withinDimension(tower.getPosition())) {
            throw new PlacementError("Tower placed outside of board");
        }

        Iterator<Tower> iterator = getTowers();
        for (Tower t; iterator.hasNext();) {
            t = iterator.next();
            if (t.getPosition().distance(tower.getPosition()) < getTowerWidth()) {
                throw new PlacementError("Tower placed on top of another tower");
            }
        }

        towers.add(tower);
    }

    public void update(Iterator<Enemy> enemies) {
        for (Tower tower : towers) {
            //tower.attack(enemies);
        }
    }

    private boolean withinDimension(Point2D point) {
        double x = point.getX();
        double y = point.getY();
        boolean widthIsOk = x >= 0 && x <= getDimension().getWidth();
        boolean heightIsOk = y >= 0 && y <= getDimension().getHeight();
        return widthIsOk && heightIsOk;
    }

    
}
