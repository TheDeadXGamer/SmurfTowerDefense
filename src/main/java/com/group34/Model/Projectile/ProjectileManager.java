package com.group34.Model.Projectile;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages all active projectiles in the game.
 * <p>
 * This class implements the Singleton pattern to ensure that only one instance
 * of {@code ProjectileManager} exists. It keeps track of all projectiles, updates
 * their positions, checks for collisions with targets, and handles the removal
 * of projectiles when necessary.
 * </p>
 */
public class ProjectileManager {

    private static ProjectileManager instance = null;
    private List<Projectile> projectiles = new ArrayList<>();

    /**
     * Retrieves the single instance of {@code ProjectileManager}.
     * <p>
     * If no instance exists, a new one is created. This ensures that all parts of
     * the game use the same instance of the manager.
     * </p>
     *
     * @return the single instance of {@code ProjectileManager}.
     */
    public ProjectileManager getInstance() {
        if (instance == null) {
            instance = new ProjectileManager();
        }
        return instance;
    }

    /**
     * Adds a projectile to the manager's list of active projectiles.
     *
     * @param projectile the projectile to be added to the active list.
     */
    public void addProjectile(Projectile projectile) {
        projectiles.add(projectile);
    }

    /**
     * Updates all active projectiles.
     * <p>
     * This method iterates through the list of projectiles, updates their positions,
     * and checks if any projectile has reached its target or if the target is dead.
     * Projectiles that meet these conditions are removed from the active list.
     * </p>
     */
    public void updateProjectiles() {
        List<Projectile> removeProjectiles = new ArrayList<>();

        for (Projectile projectile : projectiles) {
            projectile.updatePosition();
            if (checkIfProjectileReached(projectile)) {
                projectile.damage();
                removeProjectiles.add(projectile);
            }
            if (projectile.IfTargetDead()) {
                removeProjectiles.add(projectile);
            }
        }
        for (Projectile projectile : removeProjectiles) {
            projectiles.remove(projectile);
        }
    }

    /**
     * Retrieves the list of active projectiles.
     *
     * @return a {@code List} of active projectiles.
     */
    public List<Projectile> getProjectiles() {
        return projectiles;
    }

    /**
     * Checks if a projectile has reached its target.
     * <p>
     * A projectile is considered to have reached its target if the distance
     * between its current position and the target's position is less than or equal
     * to a predefined threshold (e.g., 15 units).
     * </p>
     *
     * @param projectile the projectile to be checked.
     * @return {@code true} if the projectile has reached its target;
     *         {@code false} otherwise.
     */
    private boolean checkIfProjectileReached(Projectile projectile) {
        return projectile.getTargetPosition().distance(projectile.getCurrentPosition()) <= 15;
    }
}