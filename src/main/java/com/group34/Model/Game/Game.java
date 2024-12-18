package com.group34.Model.Game;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.group34.Model.Enemy.Enemy;

public class Game {
    private TowerNotifier notifier = new TowerNotifier().getInstance();
    private final List<Enemy> enemies = new ArrayList<>();

    public void addEnemy(Enemy enemy) {
        enemies.add(enemy);
    }

    public Iterator<Enemy> getEnemies() {
        return enemies.iterator();
    }

    public void removeEnemy(Enemy enemy) {
        enemies.remove(enemy);
    }

    public TowerNotifier getNotifier() {
        return notifier;
    }

    public int enemiesLeft() {
        return enemies.size();
    }

    public List<Enemy> update() {
        List<Enemy> killedEnemies = new ArrayList<>();
        List<Enemy> finishedEnemies = new ArrayList<>();
        for (Enemy enemy : enemies) {
            if (!enemy.isAlive()) {
                killedEnemies.add(enemy);
            }
            if (enemy.isFinished()) {
                finishedEnemies.add(enemy);
            }
            enemy.move();
            notifier.getInstance().notifyThatEnemyMoved(enemy);
        }
        for (Enemy enemy: killedEnemies) {
            notifier.getInstance().notifyThatEnemyDied(enemy);
            enemies.remove(enemy);
        }

        for (Enemy enemy: finishedEnemies) {
            notifier.getInstance().notifyThatEnemyDied(enemy);
            enemies.remove(enemy);
        }

        return killedEnemies;
    }

}
