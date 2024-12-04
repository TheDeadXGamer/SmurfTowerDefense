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

    /**
     * Returns the health of the player
     * @return the health of the player
     */
    @Override
    public int getHealth() {
        return health;
    }

    /**
     * Sets the health of the player
     * @param health the new health of the player
     */
    @Override
    public void setHealth(int health) {
        this.health = health;
        System.out.println("Player health set to " + health);

    }

    /**
     * Returns the money of the player
     * @return the money of the player
     */
    @Override
    public int getMoney() {
        return money;
    }

    /**
     * Sets the money of the player
     * @param money the new money of the player
     */
    @Override
    public void setMoney(int money) {
        this.money = money;
        System.out.println("Player money set to " + money);
    }

    /**
     * Returns the towers of the player
     * @return the towers of the player
     */
    @Override
    public List<Tower> getTowers() {
        return towers;
    }

    /**
     * Adds a tower to the player
     * @param tower the tower to be added
     */
    @Override
    public void addTower(Tower tower) {
        towers.add(tower);
    }

    /**
     * Removes a tower from the player
     * @param tower the tower to be removed
     */
    @Override
    public void removeTower(Tower tower) {
        towers.remove(tower);
    }
    
    /**
     * Returns whether the player is alive
     * @return true if the player is alive, false otherwise
     */
    @Override
    public boolean isAlive() {
        return health > 0;
    }
}
