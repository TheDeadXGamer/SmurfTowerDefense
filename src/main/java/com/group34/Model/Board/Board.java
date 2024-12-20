package com.group34.Model.Board;

import java.awt.Dimension;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.group34.Model.Game.TowerNotifier;
import com.group34.Model.Projectile.ProjectileManager;
import com.group34.Model.Tower.Tower;

public class Board {
    private ProjectileManager projectileManager = new ProjectileManager();
    private TowerNotifier notifier = new TowerNotifier().getInstance();
    private List<Tower> towers = new ArrayList<>();
    private Dimension dimension;

    /**
     * Constructor for the Board class
     * @param dimension The dimension of the board
     */
    public Board(Dimension dimension) {
        this.dimension = dimension;
    }

    /**
     * Get the dimension of the board
     * @return The dimension of the board
     */
    public Dimension getDimension() {
        return dimension;
    }

    /**
     * Get the projectile manager
     * @return The projectile manager
     */
    public ProjectileManager getProjectileManager() {
        return projectileManager.getInstance();
    }

    /**
     * Get the towers on the board
     * @return An iterator of the towers on the board
     */
    public Iterator<Tower> getTowers() {
        return towers.iterator();
    }

    /**
     * Remove a tower from the board
     * @param tower The tower to remove
     * @throws InvalidRemovalError If the tower is not on the board
     * @return void
     */
    public void removeTower(Tower tower) throws InvalidRemovalError {
        if (!towers.contains(tower)) {
            throw new InvalidRemovalError();
        }
        towers.remove(tower);
        notifier.getInstance().unsubscribe(tower);
    }

    /**
     * Add a tower to the board
     * @param tower The tower to add
     * @throws PlacementError If the tower is not within the board or too close to another tower
     * @return boolean
     */
    public boolean addTower(Tower tower) throws PlacementError {

   
        if (!withinDimension(tower.getPosition())) {
            throw new PlacementError("Tower not within the board");

        }

        Iterator<Tower> iterator = getTowers();

        // Check if the tower is being placed on top of another tower
        for (Tower t; iterator.hasNext();) {
            t = iterator.next();
            if (t.getPosition().distance(tower.getPosition()) < t.getTowerWidth()) {
                throw new PlacementError("Too close to another tower!");
            }
        }

        towers.add(tower);
        notifier.getInstance().subscribe(tower);
        return true;
    }

    /**
     * Update the board
     * @return void
     */
    public void update() {
        for (Tower tower : towers) {
            tower.action();
        }
        projectileManager.getInstance().updateProjectiles();
    }

    /**
     * Check if a point is within the board
     * @param point The point to check
     * @return boolean
     */
    private boolean withinDimension(Point2D point) {
        double x = point.getX();
        double y = point.getY();
        boolean widthIsOk = x >= 0 && x <= getDimension().getWidth();
        boolean heightIsOk = y >= 0 && y <= getDimension().getHeight();
        return widthIsOk && heightIsOk;
    }

    
}
