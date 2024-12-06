package com.group34.Model.Enemy;

import com.group34.Model.Cash.CashVault;
import com.group34.Model.Game.Game;
import com.group34.Model.Road.RoadToken;

public class Gargamel extends BaseEnemy {
    
    static int speed = 2;
    static int health = 30;
    static int reward = 10;

    public Gargamel(
        Game game, 
        CashVault cashVault, 
        RoadToken point
    ) {
        super(health, game, cashVault, speed, reward, point);
    }
}
