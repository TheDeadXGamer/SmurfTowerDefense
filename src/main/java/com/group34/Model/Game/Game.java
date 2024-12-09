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

    public void update() {
        for (Enemy enemy : enemies) {
            if (!enemy.isAlive()) {
                enemies.remove(enemy);
            }
            enemy.move();
            notifier.getInstance().notifyThatEnemyMoved(enemy);
        }
    }

}
