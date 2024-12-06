package com.group34.Model.Enemy;

import com.group34.Model.Cash.CashVault;
import com.group34.Model.Game.Game;
import com.group34.Model.Road.RoadToken;

public abstract class EnemyFactory {
    final CashVault cashVault;
    final Game game;

    public EnemyFactory(Game game, CashVault cashVault) {
        this.game = game;
        this.cashVault = cashVault;
    }

    public abstract Enemy createEnemy(RoadToken token);

}
