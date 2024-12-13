package com.group34.View.Shop;

import com.group34.Model.Tower.TowerFactory;

public class TowerShopItem implements IShopItem {
    private TowerFactory towerTypeFactory;
    private int cost;
    private String towerTypeName;

    public TowerShopItem(TowerFactory towerTypeFactory, int cost) {
        this.towerTypeFactory = towerTypeFactory;
        this.cost = cost;
        this.towerTypeName = towerTypeFactory.createTower(null).getTowerType(); // maybe bad to create a tower just to get its type
    }

    /**
     * @return the factory for the tower type.
     */
    @Override
    public TowerFactory getFactory() {
        return towerTypeFactory;
    }

    @Override
    public int getCost() {
        return cost;
    }

    @Override
    public String getTypeName() {
        return towerTypeName;
    }

    @Override
    public String getToolTipText() {
        return "Cost: " + cost; // TODO: more info needed
    }
}
