package com.group34.Controller;

import com.group34.Model.Board.InvalidRemovalError;
import com.group34.Model.Shop.Shop;
import com.group34.Model.Tower.Tower;

public class TowerSell {
    private Shop shop;
    public TowerSell(Shop shop) {
        this.shop = shop;
    }

    public void sellTower(Tower tower) {
        try {
            shop.refundTower(tower);
        } catch (InvalidRemovalError e) {
            System.out.println("asdsad"); // TODO: handle exception
        }
    }
}
