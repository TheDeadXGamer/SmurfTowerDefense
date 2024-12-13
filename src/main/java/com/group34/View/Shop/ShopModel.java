package com.group34.View.Shop;

import java.awt.geom.Point2D;
import java.util.ArrayList;

import com.group34.Model.Cash.CashVault;
import com.group34.Model.Cash.OverDraftError;
import com.group34.Model.Game.Player;
import com.group34.Model.Tower.LightningSmurfFactory;
import com.group34.Model.Tower.Tower;

public class ShopModel {
    private ArrayList<IShopItem> items;
    private Player player;
    private CashVault cashVault;

    public ShopModel(Player player, CashVault cashVault) {
        this.player = player;
        this.cashVault = cashVault;

        // adds items to the shop as a factory and a cost
        items = new ArrayList<>();
        items.add(new TowerShopItem(new LightningSmurfFactory(), 50));
    }

    public ArrayList<IShopItem> getItems() {
        return items;
    }

    public CashVault getCashVault() {
        return cashVault;
    }

    public Player getPlayer() {
        return player;
    }

    public Tower purchaseItem(IShopItem item, Point2D position) {
        try {
            cashVault.reduce(item.getCost());
            return item.getFactory().createTower(position);
        } catch (OverDraftError e) {
            // TODO: Handle the overdraft error better
            System.err.println(e.getMessage());
            return null;
        }
    }

}