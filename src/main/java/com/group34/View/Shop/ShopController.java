package com.group34.View.Shop;

import com.group34.Model.Tower.Tower;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public class ShopController {
    private ShopModel shopModel;
    public ShopController(ShopModel shopModel) {
        this.shopModel = shopModel;
    }
    public ArrayList<IShopItem> getItems() {
        return shopModel.getItems();
    }
    public String purchaseTower(String towerType, Point2D position) {
        // find the item in the shop model, purchase it, and return it
        for (IShopItem item : shopModel.getItems()) {
            if (item.getTypeName().equals(towerType)) {
                return shopModel.purchaseItem(item, position);
            }
        }
        return null; // maybe throw some exception instead
    }
    public int getCashVaultBalance() {
        return shopModel.getCashVault().getBalance();
    }
    public int getPlayerHealth() {
        return shopModel.getPlayer().getHealth();
    }
}
