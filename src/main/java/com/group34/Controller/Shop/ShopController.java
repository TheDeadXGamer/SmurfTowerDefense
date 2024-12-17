package com.group34.Controller.Shop;

import java.awt.geom.Point2D;

import com.group34.Model.Shop.Shop;

public class ShopController {
    private Shop shopModel;
    public ShopController(Shop shopModel) {
        this.shopModel = shopModel;
    }

    public String purchaseTower(String towerType, Point2D position) {
        // find the item in the shop model, purchase it, and return it
        //for (IShopItem item : shopModel.getItems()) {
        //    if (item.getTypeName().equals(towerType)) {
        //        return shopModel.purchaseItem(item, position);
        //    }
        //}
        return null; // maybe throw some exception instead
    }
}
