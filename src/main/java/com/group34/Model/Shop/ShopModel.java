package com.group34.Model.Shop;

import java.util.ArrayList;

import com.group34.Model.Player.Player;
import com.group34.Model.Tower.*;
import com.group34.Model.Tower.Factory.LightningSmurfFactory;

public class ShopModel {
    private Player player;
    private ArrayList<IShopItem> items;

    public ShopModel(Player player) {
        this.player = player;
        items = new ArrayList<>();
        // example for testing
        items.add(new TowerShopItem(new LightningSmurfFactory()));
        //items.add(new TowerShopItem(new LightningSmurfFactory()));

    }

    /**
     * @return list of items in shop.
     **/
    public ArrayList<IShopItem> getItems() {
        return items;
    }

    /**
     * Purchases an item from the shop.
     * @param shopItem the item to be purchased.
     * @return true if player has enough money to purchase item while subtracting cost from player's money, false otherwise.
     **/
    public boolean purchaseItem(IShopItem shopItem) {
        int playerMoney = player.getMoney();
        int cost = shopItem.getTowerTypeFactory().getTowerReference().getCost();

        // if player has enough money, purchase item and return true
        if (playerMoney >= cost) {
            player.setMoney(playerMoney - cost);  // subtract cost from players money
            return true;
        } else {
            System.out.println("You don't have enough money for this item");  // temporary
            // TODO: replace println with equivalent in UI
            return false;
        }
    }

    /**
     * @return the player.
     **/
    public Player getPlayer() {
        return player;
    }
}