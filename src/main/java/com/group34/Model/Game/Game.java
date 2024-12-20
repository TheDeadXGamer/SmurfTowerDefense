package com.group34.Model.Game;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.group34.Model.Enemy.Enemy;

public class Game {
    private TowerNotifier notifier = new TowerNotifier().getInstance();
    private final List<Enemy> enemies = new ArrayList<>();

    /**
     * Adds an enemy to the game
     * @param enemy Enemy to add
     * @return void
     */
    public void addEnemy(Enemy enemy) {
        enemies.add(enemy);
    }

    /**
     * Returns an iterator for the enemies in the game
     * @return Iterator<Enemy>
     */
    public Iterator<Enemy> getEnemies() {
        return enemies.iterator();
    }

    /**
     * Removes an enemy from the game
     * @param enemy Enemy to remove
     * @return void
     */
    public void removeEnemy(Enemy enemy) {
        enemies.remove(enemy);
    }

    /**
     * Returns the TowerNotifier instance
     * @return TowerNotifier
     */
    public TowerNotifier getNotifier() {
        return notifier;
    }

    /**
     * Returns the number of enemies left in the game
     * @return int
     */
    public int enemiesLeft() {
        return enemies.size();
    }

    /**
     * Updates the enemies in the game by moving them and checking if they are dead
     * @return List<Enemy> List of enemies that were killed
     */
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
