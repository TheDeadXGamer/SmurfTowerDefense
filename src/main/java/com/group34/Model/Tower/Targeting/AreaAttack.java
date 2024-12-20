package com.group34.Model.Tower.Targeting;

import java.awt.geom.Point2D;
import java.util.List;

import com.group34.Model.Enemy.Attackable;
import com.group34.Model.Positionable;
import com.group34.Model.Projectile.ProjectileFactory;
import com.group34.Model.Projectile.ProjectileManager;

/**
 * AreaAttack class is a class that implements the Targetings interface.
 * It is used to attack all enemies in a certain range.
 * It has a ProjectileManager object to manage the projectiles.
 * @param enemies is a generic type that extends Positionable and Attackable.
 * @see Targetings
 */
public class AreaAttack<enemies extends Positionable & Attackable> implements Targetings<enemies> {

    private ProjectileManager projectileManager = new ProjectileManager();
    private ProjectileFactory factory;
    private Point2D towerPosition;
    private int range;

    public AreaAttack(ProjectileFactory projectileType, Point2D position, int range) {
        this.factory = projectileType;
        this.towerPosition = position;
        this.range = range;
    }

    /**
     * This method is used to attack all enemies in a certain range.
     * @param enemies is a list of generic type that extends Positionable and Attackable.
     * @return void
     */
    @Override
    public void attack(List<enemies> enemies) {
        for (enemies enemy : enemies) {
            if (checkIfInRange(enemy)) {
                projectileManager.getInstance().addProjectile(factory.createProjectile(enemy));
            }
        }
    }

    /**
     * This method is used to check if the enemy is in the range of the tower.
     * @param enemy is a generic type that extends Positionable and Attackable.
     * @return boolean
     */
    private boolean checkIfInRange(enemies enemy) {
        return towerPosition.distance(enemy.getPosition()) <= this.range;
    }
}