package com.group34.Model.Game;

import com.group34.Model.IObservable;
import com.group34.Model.IObserver;

import java.util.ArrayList;

public class Player implements IObservable {
    private ArrayList<IObserver> observers;
    private int health;

    public Player(int startingHealth) {
        this.health = startingHealth;
        observers = new ArrayList<>();
    }

    public int getHealth() {
        return health;
    }

    public void reduceHealth(int damage) {
        health -= damage;
    }

    public boolean isAlive() {
        return health > 0;
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
