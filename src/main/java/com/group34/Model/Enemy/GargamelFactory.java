package com.group34.Model.Enemy;


import com.group34.Model.Cash.CashVault;
import com.group34.Model.Game.Game;
import com.group34.Model.Road.RoadToken;

public class GargamelFactory extends EnemyFactory {

    public GargamelFactory(Game game, CashVault cashVault, RoadToken point) {
        super(game, cashVault, point);
    }

    @Override
    public Enemy createEnemy() {
        return new Gargamel(getGame(), getCashVault(), getPoint());
    }
    
}
