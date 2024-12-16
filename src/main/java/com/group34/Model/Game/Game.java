package com.group34.Model.Game;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.group34.Model.Enemy.Enemy;

public class Game {

    private TowerNotifier notifier = new TowerNotifier();
    private final List<Enemy> enemies = new ArrayList<>();

    public void addEnemy(Enemy enemy) {
        enemies.add(enemy);
    }

    public Iterator<Enemy> getEnemies() {
        return enemies.iterator();
    }

    public int enemiesLeft() {
        return enemies.size();
    }

    public List<Enemy> update() {

        List<Enemy> killedEnemies = new ArrayList<>();
        for (Enemy enemy : enemies) {
            if (!enemy.isAlive()) {
                killedEnemies.add(enemy);
            }
            enemy.move();
            notifier.getInstance().notifyThatEnemyMoved(enemy);
        }

        for (Enemy enemy: killedEnemies) {
            notifier.getInstance().notifyThatEnemyDied(enemy);
            enemies.remove(enemy);
        }

        return killedEnemies;
    }

}
