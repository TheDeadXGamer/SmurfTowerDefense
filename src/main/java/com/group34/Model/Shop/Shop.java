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
    private static final double refundFactor = 0.7;

    public Shop(CashVault cashVault, Board board) {
        this.cashVault = cashVault;
        this.board = board;
    }

    public void purchaseItem(ShopItem item, Point2D position) 
        throws OverDraftError, PlacementError 
    {
            cashVault.withdraw(item.getCost());
            Tower tower = item.factory.createTower(position);
            board.addTower(tower);
    }

    public void refundTower(Tower tower, ShopItem item) throws InvalidRemovalError {
        cashVault.deposit((int) (item.getCost() * refundFactor));
        board.removeTower(tower);
    }

    public Iterator<ShopItem> getItems() {
        return items.iterator();
    }

}