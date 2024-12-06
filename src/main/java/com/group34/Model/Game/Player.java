package com.group34.Model.Game;

public class Player {
    private int health;

    public Player(int startingHealth) {
        this.health = startingHealth;
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

}
