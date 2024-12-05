package com.group34.Model.Enemy;

import com.group34.Model.Cash.CashVault;
import com.group34.Model.Game.Game;
import com.group34.Model.Road.RoadToken;

public abstract class EnemyFactory {
    private CashVault cashVault;
    private Game game;
    private RoadToken point;

    public EnemyFactory(Game game, CashVault cashVault, RoadToken point) {
        this.game = game;
        this.cashVault = cashVault;
        this.point = point;
    }

    public abstract Enemy createEnemy();

    public CashVault getCashVault() {
        return cashVault;
    }

    public Game getGame() {
        return game;
    }

    public RoadToken getPoint() {
        return point;
    }

}
