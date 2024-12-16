package com.group34.Model.Board;

import java.awt.Dimension;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.group34.Model.Game.TowerNotifier;
import com.group34.Model.Projectile.Projectile;
import com.group34.Model.Projectile.ProjectileManager;
import com.group34.Model.Tower.Tower;

public class Board {
    private ProjectileManager projectileManager = new ProjectileManager();

    private TowerNotifier notifier = new TowerNotifier();

    private List<Tower> towers = new ArrayList<>();

    private Dimension dimension;

    public Board(Dimension dimension) {
        this.dimension = dimension;
    }

    public Dimension getDimension( ) {
        return dimension;
    }



    public ProjectileManager getProjectileManager() {
        return projectileManager.getInstance();
    }

    public Iterator<Tower> getTowers() {
        return towers.iterator();

    }

    public boolean addTower(Tower tower) throws PlacementError {

   
        if (!withinDimension(tower.getPosition())) {
            return false;

        }

        Iterator<Tower> iterator = getTowers();
        for (Tower t; iterator.hasNext();) {
            t = iterator.next();
            if (t.getPosition().distance(tower.getPosition()) < t.getTowerWidth()) {
                return false;
            }
        }

        towers.add(tower);
        notifier.getInstance().subscribe(tower);
        return true;
    }

    public void update() {
        for (Tower tower : towers) {
            tower.action();
        }
        projectileManager.getInstance().updateProjectiles();
    }

    private boolean withinDimension(Point2D point) {
        double x = point.getX();
        double y = point.getY();
        boolean widthIsOk = x >= 0 && x <= getDimension().getWidth();
        boolean heightIsOk = y >= 0 && y <= getDimension().getHeight();
        return widthIsOk && heightIsOk;
    }

    
}
