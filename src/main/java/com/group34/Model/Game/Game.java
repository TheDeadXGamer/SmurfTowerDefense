package com.group34.Model.Game;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.group34.Model.Enemy.Enemy;
import com.group34.Model.IObservable;
import com.group34.Model.IObserver;

public class Game implements IObservable {

    private TowerNotifier notifier = new TowerNotifier();
    private final List<Enemy> enemies = new ArrayList<>();
    private int roundNumber = -1;
    private ArrayList<IObserver> observers = new ArrayList<>();

    public int getRoundNumber() {
        return roundNumber;
    }

    public void setRoundNumber(int roundNumber) {
        this.roundNumber = roundNumber;
        notifyObservers();
    }

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

        List<Enemy> killedEnemies = new ArrayList<>();
        for (Enemy enemy : enemies) {
            if (!enemy.isAlive()) {
                killedEnemies.add(enemy);
                continue;
            }
            enemy.move();
            notifier.getInstance().notifyThatEnemyMoved(enemy);
        }
        for (Enemy enemy: killedEnemies) {
            notifier.getInstance().notifyThatEnemyDied(enemy);
            enemies.remove(enemy);

        }
    }

    @Override
    public void addObserver(IObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(IObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (IObserver observer : observers) {
            observer.update();
        }
    }
}
