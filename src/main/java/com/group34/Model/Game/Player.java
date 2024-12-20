package com.group34.Model.Game;

import java.util.ArrayList;

public class Player {
    private ArrayList<PlayerSubscriber> observers;
    private int health;

    public Player(int startingHealth) {
        this.health = startingHealth;
        observers = new ArrayList<>();
    }

    /**
     * Returns the player's health
     * @return int
     */
    public int getHealth() {
        return health;
    }

    /**
     * Reduces the player's health by the given amount
     * @param damage Amount to reduce health by
     * @return void
     */
    public void reduceHealth(int damage) {
        health -= damage;
        notifyObservers();
    }

    /**
     * Returns whether the player is alive
     * @return boolean
     */
    public boolean isAlive() {
        return health > 0;
    }

    /**
     * Adds an observer to the player
     * @param observer Observer to add
     * @return void
     */
    public void subscribe(PlayerSubscriber observer) {
        observers.add(observer);
    }

    /**
     * Removes an observer from the player
     * @param observer Observer to remove
     * @return void
     */
    public void unsubscribe(PlayerSubscriber observer) {
        observers.remove(observer);
    }

    /**
     * Notifies all observers of the player's health
     * @return void
     */
    public void notifyObservers() {
        for (PlayerSubscriber observer : observers) {
            observer.updateHealth(health);
        }
    }
}
