package com.group34.Model.Shop;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Iterator;

import com.group34.Model.Board.PlacementError;
import com.group34.Model.Tower.Tower;

public class Shop {
    private ArrayList<ShopItem> items = new ArrayList<>();
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

    public Iterator<ShopItem> getItems() {
        return items.iterator();
    }

}