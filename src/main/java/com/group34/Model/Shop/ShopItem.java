package com.group34.Model.Shop;

import com.group34.Model.Tower.TowerFactory;

public class ShopItem {
    private int cost;
    TowerFactory factory;

    public ShopItem(TowerFactory factory, int cost) {
        assert cost >= 0;
        this.cost = cost;
        this.factory = factory;
    }

    public int getCost() {
        return cost;
    }

    public String getName() {
        return factory.getClass().getSimpleName().replace("Factory", "");
    }

}
