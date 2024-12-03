package com.group34.Model.Shop;

import com.group34.Model.Tower.Tower;
import com.group34.Model.Tower.TowerFactory;

public class TowerShopItem implements IShopItem {
    private TowerFactory towerTypeFactory;

    public TowerShopItem(TowerFactory towerTypeFactory) {
        this.towerTypeFactory = towerTypeFactory;
    }

    /**
     * @return the factory for the tower type.
     */
    @Override
    public TowerFactory getTowerTypeFactory() {
        return towerTypeFactory;
    }
}
