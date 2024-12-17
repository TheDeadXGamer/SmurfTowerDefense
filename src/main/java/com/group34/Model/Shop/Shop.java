package com.group34.Model.Shop;

import java.awt.geom.Point2D;
import java.util.ArrayList;

import com.group34.Model.Board.PlacementError;
import com.group34.Model.Tower.Tower;

public class Shop {
    private ArrayList<ShopItem> items;
    private CashVault cashVault;

    public Shop(CashVault cashVault) {
        this.cashVault = cashVault;
    }

    public Tower purchaseItem(ShopItem item, Point2D position) 
        throws OverDraftError, PlacementError 
    {
            cashVault.reduce(item.getCost());
            return item.factory.createTower(position);
    }

}