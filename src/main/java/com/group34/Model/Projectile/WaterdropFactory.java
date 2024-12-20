package com.group34.Model.Projectile;

import com.group34.Model.Positionable;
import com.group34.Model.Tower.Attack;


/**
 * Factory class for creating waterdrop projectiles.
 * <p>
 * This class extends the {@code ProjectileFactory} class and is used to create
 * waterdrop projectiles for the WaterTower.
 * </p>
 */
public class WaterdropFactory extends ProjectileFactory {
    private Attack tower;
    private String projectileType = "Waterdrop";

    public WaterdropFactory(Attack tower) {
        this.tower = tower;
    }

    /**
     * Creates a waterdrop projectile.
     * @param enemy The enemy that the waterdrop is attacking.
     * @return The waterdrop projectile.
     */
    @Override
    public Projectile createProjectile(Positionable enemy) {
        return new Waterdrop(12, tower.getPosition(), tower.getDamage(), projectileType, enemy);
    }
}
