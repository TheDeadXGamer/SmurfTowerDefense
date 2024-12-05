package com.group34.Model.Enemy;


import com.group34.Model.Cash.CashVault;
import com.group34.Model.Game.Game;
import com.group34.Model.Road.RoadToken;

public class GargamelFactory extends EnemyFactory {
    final Game game;
    final CashVault cashVault;

    public GargamelFactory(Game game, CashVault cashVault) {
        super(game, cashVault);
        this.game = game;
        this.cashVault = cashVault;
    }

    @Override
    public Enemy createEnemy(RoadToken token) {
        Gargamel gargamel = new Gargamel(game, cashVault, token);
        game.subscribe(gargamel);
        return gargamel;
    }
    
}
