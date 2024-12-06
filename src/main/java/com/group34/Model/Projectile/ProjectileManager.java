package com.group34.Model.Projectile;

import java.util.ArrayList;
import java.util.List;

public class ProjectileManager {

    private static ProjectileManager instance = null;

    private List<Projectile> projectiles = new ArrayList<>();

    public ProjectileManager getInstance() {
        if (instance == null) {
            instance = new ProjectileManager();
        }
        return instance;
    }
    public void addProjectile(Projectile projectile) {
        projectiles.add(projectile);
    }

    public void updateProjectiles() {

        for (Projectile projectile: projectiles) {
            projectile.update();
            if (checkIfProjectileReached(projectile)) {
                projectiles.remove(projectile);
            }

        }

    }

    private boolean checkIfProjectileReached(Projectile projectile) {
        if (projectile.getTargetPosition().distance(projectile.getCurrentPosition()) <= 20) {
            return true;

        }
        return false;
    }
}
