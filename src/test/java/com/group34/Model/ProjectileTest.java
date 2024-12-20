package com.group34.Model;

import com.group34.Model.Enemy.*;
import com.group34.Model.Projectile.LightningBoltFactory;
import com.group34.Model.Projectile.Projectile;
import com.group34.Model.Projectile.ProjectileFactory;
import com.group34.Model.Projectile.ProjectileManager;
import com.group34.Model.Road.RoadSpawn;
import com.group34.Model.Road.RoadToken;
import com.group34.Model.Tower.*;
import com.group34.Model.Tower.Targeting.ClosestAttack;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProjectileTest {
    private ProjectileFactory projectileFactory;
    private ProjectileManager projectileManager;
    private ClosestAttack<Enemy> closestAttack;
    private Point2D towerPosition;
    private List<Enemy> enemies;

    @BeforeEach
    void setUp() {
        projectileManager = new ProjectileManager();
        towerPosition = new Point2D.Double(0, 0);
        closestAttack = new ClosestAttack<>(projectileFactory, towerPosition);
        enemies = new ArrayList<>();
    }

    @Test
    void testAttackWithNoEnemies() {
        closestAttack.attack(enemies);
        assertTrue(projectileManager.getProjectiles().isEmpty(), "No projectiles should be created");
    }
}
