package com.group34.Model.Game;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.group34.Model.Enemy.Enemy;
import com.group34.Model.Game.GameState.GameState;

public class Game {

    private TowerNotifier notifier = new TowerNotifier();
    private final List<Enemy> enemies = new ArrayList<>();

    public void addEnemy(Enemy enemy) {
        enemies.add(enemy);
    }

    public void removeEnemy(Enemy enemy) {
        enemies.remove(enemy);
    }

    public Iterator<Enemy> getEnemies() {
        return enemies.iterator();
    }

    public TowerNotifier getNotifier() {
        return notifier;
    }

    public int enemiesLeft() {
        return enemies.size();
    }
}
