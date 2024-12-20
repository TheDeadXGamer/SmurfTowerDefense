package com.group34.Model.Shop;

import com.group34.Model.Tower.TowerFactory;

/**
 * A shop item
 * This class represents an item in the shop
 */
public class ShopItem {
    private int cost;
    TowerFactory factory;

    public ShopItem(TowerFactory factory, int cost) {
        assert cost >= 0;
        this.cost = cost;
        this.factory = factory;
    }

    /**
     * Get the cost of the item
     * @return The cost of the item
     */
    public int getCost() {
        return cost;
    }

    /**
     * Get the name of the item
     * @return The name of the item
     */
    public String getName() {
        return factory.getClass().getSimpleName().replace("Factory", "");
    }
}
