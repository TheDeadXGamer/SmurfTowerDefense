package com.group34.Model.Projectile;

import com.group34.Model.Tower.Tower;

import java.awt.*;

public class LightningBoltFactory extends  ProjectileFactory{

    Tower tower;

    Image art;
    public LightningBoltFactory(Tower tower) {
        this.tower = tower;
    }
    @Override
    public ProjectileInterface createProjectile() {
        return new LightningBolt(1, tower.getPosition(), tower.getDamage(), art);
    }
}
