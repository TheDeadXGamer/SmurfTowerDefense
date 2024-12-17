package com.group34.Controller.Shop;

import java.awt.geom.Point2D;
import java.util.ArrayList;

import com.group34.Model.Game.Player;
import com.group34.Model.Shop.CashVault;
import com.group34.Model.Shop.IShopItem;
import com.group34.Model.Shop.ShopModel;

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
    public CashVault getCashVault() {
        return shopModel.getCashVault();
    }
    public Player getPlayer() {
        return shopModel.getPlayer();
    }
}
