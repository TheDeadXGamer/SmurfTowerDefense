package com.group34.Model.Enemy;

import java.awt.geom.Point2D;

import com.group34.Model.Road.RoadToken;

public abstract class BaseEnemy implements Enemy {
    private int health;
    private int reward;
    private int speed;
    private final RoadToken point;

    public BaseEnemy(
        int health,
        int speed, 
        int reward,
        RoadToken point
    ) {
        this.health = health;
        this.speed = speed;
        this.reward = reward;
        this.point = point;
    }

    /**
     * Damage the enemy
     * @param damage the amount of damage
     * @return void
     */
    @Override
    public void damage(int damage) {
        this.health -= damage;
    }

    /**
     * Move the enemy
     * @return void
     */
    @Override
    public void move() {
        point.advance(speed);
    }

    /**
     * Check if the enemy is alive
     * @return true if the enemy is alive
     */
    @Override
    public boolean isAlive() {
        return health > 0;
    }

    /**
     * Get the health of the enemy
     * @return the health of the enemy
     */
    @Override
    public int getHealth() {
        return health;
    }

    /**
     * Get the speed of the enemy
     * @return the speed of the enemy
     */
    @Override
    public int getSpeed() {
        return speed;
    }

    /**
     * Get the reward
     * @return the reward
     */
    @Override
    public int getReward() {
        return reward;
    }

    /**
     * Get the position of the enemy
     * @return the position of the enemy
     */
    @Override
    public Point2D getPosition() {
        return point.getPosition();
    }

    /**
     * Check if the enemy has reached the end of the path
     * @return true if the enemy has reached the end of the path
     */
    @Override
    public boolean isFinished() {
        return point.isFinished();
    }

}
