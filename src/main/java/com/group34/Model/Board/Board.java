package com.group34.Model.Board;

import java.awt.geom.Point2D;
import java.util.List;

import com.group34.Model.Tower.Tower;

public class Board {
    private int width;
    private int height;
    private List<Tower> towers;
    private float towerWidth = (float) 1.0;

    public Board(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void addTower(Tower tower) throws PlacementError { 
   
        Point2D position = tower.getPosition();
        if (
            position.getX() < 0 || 
            position.getX() > width ||
            position.getY() < 0 ||
            position.getY() > height
        ) {
            throw new PlacementError("Tower placed outside of board");
        }

        for (Tower t : towers) {
            if (t.getPosition().distance(tower.getPosition()) < towerWidth) {
                throw new PlacementError("Tower placed on top of another tower");
            }
        }

        towers.add(tower);
    }

    public void update() {
        for (Tower tower : towers) {
            tower.action();
        }
    }

    
}
