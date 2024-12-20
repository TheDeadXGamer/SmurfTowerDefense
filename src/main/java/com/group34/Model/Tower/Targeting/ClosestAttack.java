package com.group34.Model.Tower.Targeting;

import java.awt.geom.Point2D;
import java.util.List;

import com.group34.Model.Enemy.Attackable;
import com.group34.Model.Positionable;
import com.group34.Model.Projectile.ProjectileFactory;
import com.group34.Model.Projectile.Projectile;
import com.group34.Model.Projectile.ProjectileManager;

public class ClosestAttack<enemies extends Positionable & Attackable> implements Targetings<enemies> {

    private ProjectileManager projectileManager = new ProjectileManager();
    private ProjectileFactory factory;
    private Point2D towerPosition;

    /**
     * Constructs a {@code ClosestAttack} instance with the specified projectile factory
     * and tower position.
     *
     * @param projectileType the factory responsible for creating projectiles for attacks.
     * @param position the position of the tower, used to determine the closest enemy.
     */
    public ClosestAttack(ProjectileFactory projectileType, Point2D position) {
        this.factory = projectileType;
        this.towerPosition = position;
    }

    /**
     * Attacks the closest enemy from the given list.
     * <p>
     * This method determines the closest enemy to the tower using the {@code closestEnemy}
     * method. If a closest enemy is found, a projectile is created via the
     * {@link ProjectileFactory} and added to the {@link ProjectileManager}.
     * </p>
     *
     * @param enemy a list of enemies to be evaluated for an attack.
     *              If the list is empty, no attack is performed.
     */
    @Override
    public void attack(List<enemies> enemy) {
        enemies closestEnemy = closestEnemy(enemy);
        if (closestEnemy == null) {
            return;
        }
        // Create a projectile for the closest enemy and add it to the manager
        Projectile projectile = factory.createProjectile(closestEnemy);
        projectileManager.getInstance().addProjectile(projectile); // TODO: Add behavior for projectile
    }

    /**
     * Finds the closest enemy to the tower from the given list.
     * <p>
     * This method iterates over all the enemies in the list, calculating the distance
     * between each enemy's position and the tower's position. The enemy with the smallest
     * distance is returned.
     * </p>
     *
     * @param enemies a list of enemies to evaluate. If the list is empty, {@code null} is returned.
     * @return the closest enemy to the tower, or {@code null} if the list is empty.
     */
    private enemies closestEnemy(List<enemies> enemies) {
        if (enemies.isEmpty()) {
            return null;
        }

        enemies closestEnemy = null;

        for (enemies enemy2 : enemies) {
            double distance = enemy2.getPosition().distance(towerPosition);
            if (closestEnemy == null ||
                    closestEnemy.getPosition().distance(towerPosition) > distance) {
                closestEnemy = enemy2;
            }
        }
        return closestEnemy;
    }
}