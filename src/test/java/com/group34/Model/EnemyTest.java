package com.group34.Model;

import com.group34.Model.Enemy.Enemy;
import com.group34.Model.Enemy.EnemyFactory;
import com.group34.Model.Enemy.GargamelFactory;
import com.group34.Model.Enemy.Killable;
import com.group34.Model.Road.RoadSpawn;
import com.group34.Model.Road.RoadToken;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.geom.Point2D;

import static org.junit.jupiter.api.Assertions.*;

public class EnemyTest {
    private EnemyFactory enemyFactory;
    private RoadToken roadToken;
    private Enemy enemy;

    @BeforeEach
    public void setup() {
        enemyFactory = new GargamelFactory();
        roadToken = new RoadToken(new RoadSpawn(new Point(0, 0), null));
        testCreateEnemy();
    }

    @Test
    public void testCreateEnemy() {
        enemy = enemyFactory.createEnemy(roadToken);
        assertNotNull(enemy, "Enemy should be created");
    }

    @Test
    public void testAttackable() {
        int startingHealth = enemy.getHealth();
        int damage = 10;
        enemy.damage(damage);
        assertEquals(startingHealth - damage, enemy.getHealth());
    }

    @Test
    public void testKillableEnemy() {
        assertTrue(enemy.isAlive(), "Enemy should be alive");
        enemy.damage(enemy.getHealth());
        assertFalse(enemy.isAlive(), "Enemy should be dead");
    }
}
