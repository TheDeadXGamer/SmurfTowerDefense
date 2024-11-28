package com.group34.Model.Tower.Targeting;


import java.awt.geom.Point2D;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;
import java.math.*;
import com.group34.Model.Enemy.Enemy;
import com.group34.Model.Projectile.ProjectileFactory;
import com.group34.Model.Projectile.ProjectileInterface;
import com.group34.Model.Tower.Attack;

public class ClosestAttack implements Targetings {

    ProjectileFactory factory;
    Point2D towerPosition;
    public ClosestAttack(ProjectileFactory projectileType, Point2D position) {
        this.factory = projectileType;
        this.towerPosition = position;
    }

    //MIGHT WANT TO BE SINGLETON?
    @Override
    public void attack(List<Enemy> enemy) {

        Enemy closestEnemy = closestEnemy(enemy);
        if (closestEnemy == null) {
            return;
        }
        ProjectileInterface projectile = factory.createProjectile(); //TODO Projectile should actually do something when created
        closestEnemy.damage(projectile.getDamage());




    }

    Enemy closestEnemy(List<Enemy> enemies) {
        if (enemies.isEmpty()) {
            return null;
        }

        Enemy closestEnemy = null;

        for (Enemy enemy2: enemies) {

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
