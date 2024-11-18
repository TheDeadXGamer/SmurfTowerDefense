package com.group34.Model.Player;
import java.util.List;
import com.group34.Model.Tower.*;

interface PlayerInterface{
    // Arvid: should be split into smaller interfaces (interface segregation principle), see similar methods in EnemyInterface

    // Getters and setters for player attributes
    int getMoney();
    void setMoney(int money);

    int getHealth();
    void setHealth(int health);


    // Manage towers owned py player
    List<Tower> getTowers();
    void addTower(Tower tower);
    void removeTower(Tower tower);

    // Checks if the player is still alive, i.e checking if health is above 0
    boolean isAlive();

}