package com.group34.Model.Shop;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Iterator;

import com.group34.Model.Board.Board;
import com.group34.Model.Board.PlacementError;
import com.group34.Model.Tower.Tower;

public class Shop {
    private ArrayList<ShopItem> items = new ArrayList<>();
    private CashVault cashVault;
    private Board board;

    public Shop(CashVault cashVault, Board board) {
        this.cashVault = cashVault;
        this.board = board;
    }

    public void purchaseItem(ShopItem item, Point2D position) 
        throws OverDraftError, PlacementError 
    {
            cashVault.reduce(item.getCost());
            Tower tower = item.factory.createTower(position);
            board.addTower(tower);
    }

    public Iterator<ShopItem> getItems() {
        return items.iterator();
    }

}