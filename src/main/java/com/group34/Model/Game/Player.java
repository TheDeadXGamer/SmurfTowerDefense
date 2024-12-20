package com.group34.Model.Game;

import com.group34.Model.Enemy.Killable;

import java.util.ArrayList;

public class Player implements Killable {
    private ArrayList<PlayerSubscriber> observers;
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
        notifyObservers();
    }

    @Override
    public boolean isAlive() {
        return health > 0;
    }

    public void subscribe(PlayerSubscriber observer) {
        observers.add(observer);
    }

    public void unsubscribe(PlayerSubscriber observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for (PlayerSubscriber observer : observers) {
            observer.updateHealth(health);
        }
    }
}
