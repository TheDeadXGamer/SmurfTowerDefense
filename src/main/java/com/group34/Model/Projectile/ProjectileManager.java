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
        List<Projectile> reachedTarget = new ArrayList<>();

        for (Projectile projectile: projectiles) {
            projectile.update();
            if (checkIfProjectileReached(projectile)) {
                System.out.println("Projectile position: " + projectile.getCurrentPosition() + " | Target position " + projectile.getTargetPosition() + " | Distance: " + projectile.getCurrentPosition().distance(projectile.getTargetPosition()));
                reachedTarget.add(projectile);
            }
        }
        for (Projectile projectile: reachedTarget) {
            projectiles.remove(projectile);
        }


    }
    public List<Projectile> getProjectiles() {return projectiles;}

    private boolean checkIfProjectileReached(Projectile projectile) {
        if (projectile.getTargetPosition().distance(projectile.getCurrentPosition()) <= 7) {
            return true;

        }
        return false;
    }
}
