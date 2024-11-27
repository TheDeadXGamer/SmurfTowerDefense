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
        ProjectileInterface projectile = factory.createProjectile(); //TODO Projectile should actually do something when created
        closestEnemy.damage(projectile.getDamage());




    }

    public Enemy closestEnemy(List<Enemy> enemies) {
        Enemy closestEnemy = null;
        double minDistance = 0;
        for (Enemy enemy2: enemies) {
            double deltaY = enemy2.getPosition().getY() - towerPosition.getY();
            double deltaX = enemy2.getPosition().getX() - towerPosition.getX();
            double distance = Math.sqrt(Math.pow(deltaY,2)+Math.pow(deltaX,2));
            if (distance < minDistance) {
                minDistance = distance;
                closestEnemy = enemy2;
            }
        }
        return closestEnemy;
    }
}
