package com.group34.Model.Projectile;

import com.group34.Model.Positionable;
import com.group34.Model.Tower.Attack;

public class WaterdropFactory extends ProjectileFactory {
    private Attack tower;
    private String projectileType = "Waterdrop";

    public WaterdropFactory(Attack tower) {
        this.tower = tower;
    }

    @Override
    public Projectile createProjectile(Positionable enemy) {
        return new Waterdrop(12, tower.getPosition(), tower.getDamage(), projectileType, enemy);
    }
}
