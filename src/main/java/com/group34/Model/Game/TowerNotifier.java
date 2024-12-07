package com.group34.Model.Game;

import com.group34.Model.Enemy.Enemy;
import com.group34.Model.Tower.TowerListener;

import java.util.ArrayList;
import java.util.List;

public class TowerNotifier {

    private static TowerNotifier instance = null;

    private List<TowerListener> listeners = new ArrayList<>();

    public TowerNotifier getInstance() {
        if (instance == null) {
            instance = new TowerNotifier();
        }
        return instance;
    }

    public void subscribe(TowerListener tower) {

        listeners.add(tower);

    }
    public void unsubscribe(TowerListener tower) {
        listeners.remove(tower);
    }
    public void notifyThatEnemyMoved(Enemy enemy) {
        for (TowerListener tower: listeners) {

            tower.notifyTower(enemy);
        }
    }
}
