package com.group34.Model.Enemy;


import com.group34.Model.Cash.CashVault;
import com.group34.Model.Road.RoadToken;

public class GargamelFactory extends EnemyFactory {

    private final CashVault cashVault;

    public GargamelFactory(CashVault cashVault) {
        super(cashVault);
        this.cashVault = cashVault;
    }

    @Override
    public Enemy createEnemy(RoadToken token) {
        Gargamel gargamel = new Gargamel(cashVault, token);
        return gargamel;
    }
    
}
