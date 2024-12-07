package com.group34.Model.Projectile;

import com.group34.Model.Enemy.Enemy;
import com.group34.Model.Tower.Attack;
import com.group34.View.ViewConstants;

import javax.swing.*;
import java.awt.*;

public class LightningBoltFactory extends  ProjectileFactory{

    Attack tower;

    String projectileType = "LightningBolt";
    public LightningBoltFactory(Attack tower) {
        this.tower = tower;
    }
    @Override
    public Projectile createProjectile(Enemy enemy) {
        return new LightningBolt(15, tower.getPosition(), tower.getDamage(), projectileType,enemy);
    }
}
