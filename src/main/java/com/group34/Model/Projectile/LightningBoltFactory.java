package com.group34.Model.Projectile;

import com.group34.Model.Enemy.Enemy;
import com.group34.Model.Tower.Attack;

import java.awt.*;

public class LightningBoltFactory extends  ProjectileFactory{

    Attack tower;

    Image art;
    public LightningBoltFactory(Attack tower) {
        this.tower = tower;
    }
    @Override
    public Projectile createProjectile(Enemy enemy) {
        return new LightningBolt(1, tower.getPosition(), tower.getDamage(), art,enemy.getPosition());
    }
}
