package com.group34.Model.Enemy;

import java.awt.geom.Point2D;

public abstract class BaseEnemy implements EnemyInterface {
    private String name;
    private int health;
    private int speed;
    private Point2D position;
    private final int reward;
    private final int scoreValue;

    protected BaseEnemy(String name, int health, int speed, int reward, int scoreValue) {
        this.name = name;
        this.health = health;
        this.speed = speed;
        this.position = new Point2D.Double(0, 0);
        this.reward = reward;
        this.scoreValue = scoreValue;
    }

    @Override public String getName() { return name; }
    @Override public void setName(String name) { this.name = name; }
    @Override public double getX() { return position.getX(); }
    @Override public double getY() { return position.getY(); }
    @Override public int getHealth() { return health; }
    @Override public void setHealth(int health) { if(health > 0) this.health = health; }
    @Override public int getSpeed() { return speed; }
    @Override public void setSpeed(int speed) { if(speed > 0) this.speed = speed; }
    @Override public void setPosition(Point2D position) { this.position = position; }
    @Override public int getReward() { return reward; }
    @Override public int getScoreValue() { return scoreValue; }
    @Override public void takeDamage(int damage) { this.health -= damage; }
    @Override public boolean isAlive() { return this.health > 0; }
    @Override public void move() { /*TODO*/ }
}