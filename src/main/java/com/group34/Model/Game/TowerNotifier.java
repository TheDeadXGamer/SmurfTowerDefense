package com.group34.Model.Game;

import java.util.ArrayList;
import java.util.List;

import com.group34.Model.Enemy.Enemy;
import com.group34.Model.Tower.TowerListener;

public class TowerNotifier {
    private static TowerNotifier instance = null;

    private List<TowerListener> listeners = new ArrayList<>();

    /**
     * Singleton pattern for TowerNotifier
     * @return instance
     */
    public TowerNotifier getInstance() {
        if (instance == null) {
            instance = new TowerNotifier();
        }
        return instance;
    }

    /**
     * Subscribes to the tower
     * @param tower
     */
    public void subscribe(TowerListener tower) {
        listeners.add(tower);
    }

    /**
     * Unsubscribes from the tower
     * @param tower
     */
    public void unsubscribe(TowerListener tower) {
        listeners.remove(tower);
    }
    
    /**
     * Notifies the tower that the enemy has moved
     * @param enemy
     */
    public void notifyThatEnemyMoved(Enemy enemy) {
        for (TowerListener tower: listeners) {
            tower.notifyTower(enemy);
        }
    }

    /**
     * Notifies the tower that the enemy has died
     * @param enemy
     */
    public void notifyThatEnemyDied(Enemy enemy) {
        for (TowerListener tower: listeners) {
            tower.notifyOfDeath(enemy);
        }
    }
}
