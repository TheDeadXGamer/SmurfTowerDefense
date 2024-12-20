package com.group34.Model.Tower.Targeting;

import java.awt.geom.Point2D;
import java.util.List;

import com.group34.Model.Enemy.Attackable;
import com.group34.Model.Positionable;
import com.group34.Model.Projectile.ProjectileFactory;
import com.group34.Model.Projectile.ProjectileManager;

public class AreaAttack<enemies extends Positionable & Attackable> implements Targetings<enemies> {

    private ProjectileManager projectileManager = new ProjectileManager();
    private ProjectileFactory factory;
    private Point2D towerPosition;
    private int range;

    public AreaAttack(ProjectileFactory projectileType, Point2D position, int range) {
        this.factory = projectileType;
        this.towerPosition = position;
        this.range = range;
    }

    @Override
    public void attack(List<enemies> enemies) {
        for (enemies enemy : enemies) {
            if (checkIfInRange(enemy)) {
                projectileManager.getInstance().addProjectile(factory.createProjectile(enemy));
            }
        }
    }

    private boolean checkIfInRange(enemies enemy) {
        return towerPosition.distance(enemy.getPosition()) <= this.range;
    }


}