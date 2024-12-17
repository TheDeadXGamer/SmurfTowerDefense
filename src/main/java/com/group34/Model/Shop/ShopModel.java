package com.group34.Model.Shop;

import java.awt.geom.Point2D;
import java.util.ArrayList;

import com.group34.Model.Board.Board;
import com.group34.Model.Board.PlacementError;
import com.group34.Model.Game.Player;
import com.group34.Model.Tower.LightningSmurfFactory;
import com.group34.Model.Tower.Tower;
import com.group34.View.ViewConstants;

public class ShopModel {
    private ArrayList<IShopItem> items;
    private CashVault cashVault;

    public ShopModel(Player player, CashVault cashVault,Board board) {
        this.cashVault = cashVault;
        // adds items to the shop as a factory and a cost
        items = new ArrayList<>();
        // Add towere
        items.add(new TowerShopItem(new LightningSmurfFactory(), 50, ViewConstants.LIGHTNINGSMURF_IMAGE));
        // Add new towers here
    }

    public Tower purchaseItem(IShopItem item, Point2D position) 
        throws OverDraftError, PlacementError 
    {
            cashVault.reduce(item.getCost());
            return item.getFactory().createTower(position);
    }

}