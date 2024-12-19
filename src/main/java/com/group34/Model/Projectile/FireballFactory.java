package com.group34.Model.Projectile;

import com.group34.Model.Positionable;
import com.group34.Model.Tower.Attack;

public class FireballFactory extends ProjectileFactory {
    private Attack tower;
    private String projectileType = "Fireball";

    public FireballFactory(Attack tower) {
        this.tower = tower;
    }

    @Override
    public Projectile createProjectile(Positionable enemy) {
        return new Fireball(10, tower.getPosition(), tower.getDamage(), projectileType, enemy);
    }
}
