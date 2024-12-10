package com.group34.Model.Enemy;

import com.group34.Model.Cash.CashVault;
import com.group34.Model.Road.RoadToken;

public abstract class EnemyFactory {
    final CashVault cashVault;

    public EnemyFactory(CashVault cashVault) {
        this.cashVault = cashVault;
    }

    public abstract Enemy createEnemy(RoadToken token);

}
