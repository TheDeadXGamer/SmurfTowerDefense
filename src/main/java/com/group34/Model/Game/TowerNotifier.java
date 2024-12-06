package com.group34.Model.Game;

import com.group34.Model.Enemy.Enemy;
import com.group34.Model.Tower.Tower;
import com.group34.Model.Tower.TowerListener;

import java.util.ArrayList;
import java.util.List;

public class TowerNotifier {

    private static TowerNotifier instance = null;

    public  TowerNotifier getInstance() {
        if (instance == null) {
            instance = new TowerNotifier();
        }
        return instance;
    }

    private List<TowerListener> towers = new ArrayList<>();

    public void subscribe(Tower tower) {
        towers.add(tower);

    }
    public void unsubscribe(Tower tower) {
        towers.remove(tower);
    }
    public void notifyAllTowers(Enemy enemy) {


        for (TowerListener tower: towers) {
            tower.notifyAllTowers(enemy);
        }
    }
}
