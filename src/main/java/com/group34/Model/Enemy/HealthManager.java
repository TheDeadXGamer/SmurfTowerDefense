package com.group34.Model.Enemy;


public class HealthManager {
    private int health;

    public HealthManager(int health) {
        this.health = health;
    }

    // What happens when the enemy dies? A decorator added? Register to some DeathManager? Force the owner to delete the object? Change a flag? 
    public void damage(int damage) {
        this.health -= damage;
    }
}
