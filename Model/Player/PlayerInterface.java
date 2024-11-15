package Player;

import java.util.List;
import Game.*;

import Tower.*;
interface PlayerInterface{

    // Getters and setters for player attributes
    int getMoney();
    void setMoney(int money);

    int getHealth();
    void setHealth(int health);

    int getRoundNumber();
    void setRoundNumber(int roundNumber);
    
    // Add or subtract money
    void modifyMoney(int amount);


    // Manage towers owned py player
    List<Tower> getTowers();
    void addTower(Tower tower);
    void removeTower(Tower tower);

    // Check or update the players satus ingame, won? lost? round
    GameStatus getStatus();
    void setStatus(GameStatus status);

    Difficulty getDifficulty();

    // Applies modifiers depending on the selected difficulty 
    void applyDifficultyModifiers();

    // Checks if the player is still alive, i.e checking if health is above 0
    boolean isAlive();

}