package com.group34.Model.Enemy;


public class HealthManager {
    private int health;

    public HealthManager(int health) {
        this.health = health;
    }

    /**
     * Damages the enemy by the given amount.
     * @param damage the amount to damage the enemy by
     */
    public void damage(int damage) { // What happens when the enemy dies? A decorator added? Register to some DeathManager? Force the owner to delete the object? Change a flag? 
        this.health -= damage;
    }
}
