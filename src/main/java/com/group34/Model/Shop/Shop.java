package com.group34.Model.Shop;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Iterator;

import com.group34.Model.Board.Board;
import com.group34.Model.Board.InvalidRemovalError;
import com.group34.Model.Board.PlacementError;
import com.group34.Model.Tower.Tower;

public class Shop {
    private ArrayList<ShopItem> items = new ArrayList<>();
    private CashVault cashVault;
    private Board board;
    private static final double REFUND_FACTOR = 0.7;

    public Shop(CashVault cashVault, Board board) {
        this.cashVault = cashVault;
        this.board = board;
    }

    public Shop addItem(ShopItem item) {
        items.add(item);
        return this;
    }

    public void purchaseItem(String itemAsString, Point2D position) throws OverDraftError, PlacementError {

            ShopItem item = stringToShopItem(itemAsString);

            Tower tower = item.factory.createTower(position);
            if (cashVault.ableToBuy(item.getCost()) && board.addTower(tower)) {
                cashVault.withdraw(item.getCost());
            }


    }


    public void refundTower(Tower tower, ShopItem item) throws InvalidRemovalError {
        cashVault.deposit((int) (item.getCost() * REFUND_FACTOR));
        board.removeTower(tower);
    }

    public Iterator<ShopItem> getItems() {
        return items.iterator();
    }

    private ShopItem stringToShopItem(String item) {
        for (ShopItem shopItem: items) {
            if (item.equals(shopItem.getName())) {
                return shopItem;
            }
        }
        return null;
    }
}