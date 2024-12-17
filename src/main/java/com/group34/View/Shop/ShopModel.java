package com.group34.View.Shop;

import java.awt.geom.Point2D;
import java.util.ArrayList;

import com.group34.Model.Board.Board;
import com.group34.Model.Board.PlacementError;
import com.group34.Model.Cash.CashVault;
import com.group34.Model.Cash.OverDraftError;
import com.group34.Model.Game.Player;
import com.group34.Model.Tower.LightningSmurfFactory;
import com.group34.Model.Tower.Tower;
import com.group34.View.ViewConstants;

public class ShopModel {
    private ArrayList<IShopItem> items;
    private Player player;
    private CashVault cashVault;
    private Board board;
    public ShopModel(Player player, CashVault cashVault,Board board) {
        this.player = player;
        this.cashVault = cashVault;
        this.board = board;
        // adds items to the shop as a factory and a cost
        items = new ArrayList<>();
        // Add towere
        items.add(new TowerShopItem(new LightningSmurfFactory(), 50, ViewConstants.LIGHTNINGSMURF_IMAGE));
        // Add new towers here
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

    public String purchaseItem(IShopItem item, Point2D position) {
        try {
            Tower newTower = item.getFactory().createTower(position);

            switch(board.addTower(newTower)) {
                case "PlacedOnAnotherTower":
                    return "PlacedOnAnotherTower";
                case "NotWithinBoard":
                    return "NotWithinBoard";
                case "Valid":
                    if (item.getCost() <= cashVault.getBalance()) {
                        return "Purchased";
                    }
                    else {return "NotEnoughMoney";}

            }


            return "";

        } catch (PlacementError e) {
            throw new RuntimeException(e);
        }
    }



}