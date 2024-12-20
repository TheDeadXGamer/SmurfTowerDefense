package com.group34.Model.Shop;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Iterator;

import com.group34.Model.Board.Board;
import com.group34.Model.Board.InvalidRemovalError;
import com.group34.Model.Board.PlacementError;
import com.group34.Model.Tower.Tower;

/**
 * The shop class
 * This class is responsible for managing the shop
 * It allows the player to purchase and refund towers
 */
public class Shop {
    private ArrayList<ShopItem> items = new ArrayList<>();
    private CashVault cashVault;
    private Board board;
    private static final double REFUND_FACTOR = 0.7;

    public Shop(CashVault cashVault, Board board) {
        this.cashVault = cashVault;
        this.board = board;
    }

    /**
     * Add an item to the shop
     * @param item The item to add
     * @return The shop object
     */
    public Shop addItem(ShopItem item) {
        items.add(item);
        return this;
    }

    /**
     * Purchase an item from the shop
     * @param name The name of the item
     * @param cost The cost of the item
     * @return void
     */
    public void purchaseItem(String itemAsString, Point2D position) throws OverDraftError, PlacementError {
        ShopItem item = stringToShopItem(itemAsString);

        Tower tower = item.factory.createTower(position);
        if (cashVault.ableToBuy(item.getCost()) && board.addTower(tower)) {
            cashVault.withdraw(item.getCost());
        }
    }

    /**
     * Refund a tower
     * @param tower The tower to refund
     * @throws InvalidRemovalError
     * @return void
     */
    public void refundTower(Tower tower) throws InvalidRemovalError {
        cashVault.deposit((int) (tower.getCost() * REFUND_FACTOR));
        board.removeTower(tower);
    }

    /**
     * Get the items in the shop
     * @return An iterator of the items in the shop
     */
    public Iterator<ShopItem> getItems() {
        return items.iterator();
    }

    /**
     * Convert a string to a shop item
     * @param item The item to convert
     * @return The shop item
     */
    private ShopItem stringToShopItem(String item) {
        for (ShopItem shopItem: items) {
            if (item.equals(shopItem.getName())) {
                return shopItem;
            }
        }
        return null;
    }
}