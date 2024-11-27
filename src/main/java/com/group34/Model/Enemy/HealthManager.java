package com.group34.Model.Enemy;


public class HealthManager {
    private int health;

    public HealthManager(int health) {
        this.health = health;
    }

    public void damage(int damage) {
        this.health -= damage;
    }
}
