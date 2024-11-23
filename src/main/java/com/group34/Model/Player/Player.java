package com.group34.Model.Player;

import com.group34.Model.Tower.Tower;

import java.util.ArrayList;
import java.util.List;


public class Player implements PlayerInterface {
    private int health;
    private int money;
    private List<Tower> towers;

    public Player(int startingHealth, int startingMoney) {
        this.health = startingHealth;
        this.money = startingMoney;
        this.towers = new ArrayList<>();
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public void setHealth(int health) {
        this.health = health;
        System.out.println("Player health set to " + health);

    }

    @Override
    public int getMoney() {
        return money;
    }

    @Override
    public void setMoney(int money) {
        this.money = money;
        System.out.println("Player money set to " + money);
    }


    @Override
    public List<Tower> getTowers() {
        
        return towers;
    }

    @Override
    public void addTower(Tower tower) {
        towers.add(tower);
    }

    @Override
    public void removeTower(Tower tower) {
        towers.remove(tower);
    }

    @Override
    public boolean isAlive() {
        return health > 0;
    }
}
