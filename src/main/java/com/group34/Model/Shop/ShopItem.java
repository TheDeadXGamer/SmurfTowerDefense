package com.group34.Model.Shop;

import com.group34.Model.Tower.TowerFactory;

public class ShopItem {
    int cost;
    TowerFactory factory;

    public ShopItem(int cost) {
        assert cost >= 0;
        this.cost = cost;
    }

    public int getCost() {
        return cost;
    }

    public String getName() {
        return factory.getClass().getSimpleName().replace("Factory", "");
    }

}
