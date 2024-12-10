package com.group34.Model.Enemy;

import java.awt.geom.Point2D;

import com.group34.Model.Cash.CashVault;
import com.group34.Model.Road.RoadToken;

public abstract class BaseEnemy implements Enemy {
    private int health;
    private CashVault cashVault;
    private int reward;
    private int speed;
    private RoadToken point;

    public BaseEnemy(
        int health,
        CashVault cashVault, 
        int speed, 
        int reward,
        RoadToken point
    ) {
        this.health = health;
        this.cashVault = cashVault;
        this.speed = speed;
        this.reward = reward;
        this.point = point;
    }

    @Override
    public void damage(int damage) {
        this.health -= damage;
        if (!isAlive()) {
            this.cashVault.deposit(getReward());
        }
    }

    @Override
    public void move() {
        point.advance(speed);
    }

    @Override
    public boolean isAlive() {
        return health > 0;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public int getSpeed() {
        return speed;
    }

    @Override
    public int getReward() {
        return reward;
    }

    @Override
    public Point2D getPosition() {
        return point.getPosition();
    }


}
