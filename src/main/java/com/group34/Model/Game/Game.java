package com.group34.Model.Game;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.group34.Model.Enemy.Enemy;

public class Game {

    private TowerNotifier notifier = new TowerNotifier();
    private final List<Enemy> enemies = new ArrayList<>();

    public void subscribe(Enemy enemy){
        enemies.add(enemy);
    }

    public void unsubscribe(Enemy enemy){
        enemies.remove(enemy);
    }

    public Iterator<Enemy> getEnemies() {
        return enemies.iterator();
    }

    public int enemiesLeft() {
        return enemies.size();
    }

    public void update() {
        for (Enemy enemy : enemies) {
            enemy.move();
            notifier.getInstance().notifyThatEnemyMoved(enemy);
        }
    }

}
