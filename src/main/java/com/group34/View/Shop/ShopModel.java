package com.group34.View.Shop;

import java.util.ArrayList;

import com.group34.Model.Game.Player;

public class ShopModel {
    private Player player;
    private ArrayList<IShopItem> items;

    public ShopModel(Player player, ArrayList<IShopItem> items) {
        this.player = player;
        this.items = items;
    }

    public ArrayList<IShopItem> getItems() {
        return items;
    }

    // public boolean purchaseItem(IShopItem shopItem) {
    //     int playerMoney = player.getMoney();
    //     int cost = shopItem.getCost();

    //     // if player has enough money, purchase item and return true
    //     if (playerMoney >= cost) {
    //         player.setMoney(playerMoney - cost);  // subtract cost from players money
    //         return true;
    //     } else {
    //         System.out.println("You don't have enough money for a " + shopItem.getName());  // temporary
    //         // TODO: replace println with equivalent in UI
    //         return false;
    //     }
    // }
}