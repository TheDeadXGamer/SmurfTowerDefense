package com.group34.Model.Tower.Targeting;


import java.awt.geom.Point2D;
import java.util.List;

import com.group34.Model.Enemy.Attackable;
import com.group34.Model.Enemy.Enemy;
import com.group34.Model.Positionable;
import com.group34.Model.Projectile.ProjectileFactory;
import com.group34.Model.Projectile.Projectile;
import com.group34.Model.Projectile.ProjectileManager;

public class ClosestAttack<enemies extends Positionable & Attackable> implements Targetings<enemies> {

    private ProjectileManager projectileManager = new ProjectileManager();
    private ProjectileFactory factory;
    private Point2D towerPosition;
    public ClosestAttack(ProjectileFactory projectileType, Point2D position) {
        this.factory = projectileType;
        this.towerPosition = position;
    }


    @Override
    public void attack(List<enemies> enemy) {

        enemies closestEnemy = closestEnemy(enemy);
        if (closestEnemy == null) {
            return;
        }
        Projectile projectile = factory.createProjectile(closestEnemy); //TODO Projectile should actually do something when created
        projectileManager.getInstance().addProjectile(projectile);
        closestEnemy.damage(projectile.getDamage());




    }

    enemies closestEnemy(List<enemies> enemies) {
        if (enemies.isEmpty()) {
            return null;
        }

        enemies closestEnemy = null;

        for (enemies enemy2: enemies) {

            double distance = enemy2.getPosition().distance(towerPosition);
            if (closestEnemy == null) {
                closestEnemy = enemy2;
            }

            else if (closestEnemy.getPosition().distance(towerPosition) > distance) {
                closestEnemy = enemy2;
            }
        }
        return closestEnemy;
    }
}
