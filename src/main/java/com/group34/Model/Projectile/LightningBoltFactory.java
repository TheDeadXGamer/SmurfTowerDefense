package com.group34.Model.Projectile;

import com.group34.Model.Positionable;
import com.group34.Model.Tower.Attack;

public class LightningBoltFactory extends  ProjectileFactory{
    private Attack tower;

    String projectileType = "LightningBolt";
    public LightningBoltFactory(Attack tower) {
        this.tower = tower;
    }

    /**
     * Creates a LightningBolt projectile
     * @param enemy the enemy to target
     * @return the LightningBolt projectile
     */
    @Override
    public Projectile createProjectile(Positionable enemy) {
        return new LightningBolt(15, tower.getPosition(), tower.getDamage(), projectileType,enemy);
    }
}
