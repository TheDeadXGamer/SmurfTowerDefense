package com.group34.Controller;

import com.group34.Model.Board.InvalidRemovalError;
import com.group34.Model.Shop.Shop;
import com.group34.Model.Tower.Tower;
import com.group34.View.SellTowerListener;

import java.util.ArrayList;

public class TowerSell {
    private Shop shop;
    private ArrayList<SellTowerListener> observers = new ArrayList<>();

    /**
     * Constructor for TowerSell
     * @param shop Shop
     */
    public TowerSell(Shop shop) {
        this.shop = shop;
    }

    /**
     * Sell a tower
     * @param tower Tower
     * @throws InvalidRemovalError
     * @return void
     */
    public void sellTower(Tower tower) {
        try {
            shop.refundTower(tower);
            notifySubsribers(tower);
        } catch (InvalidRemovalError e) {
             // TODO: handle exception
        }
    }

    /**
     * Subscribe to the SellTowerListener
     * @param observer SellTowerListener
     * @return void
     */
    public void subscribe(SellTowerListener observer) {
        observers.add(observer);
    }

    /**
     * Unsubscribe to the SellTowerListener
     * @param observer SellTowerListener
     * @return void
     */
    public void unsubscribe(SellTowerListener observer) {
        observers.remove(observer);
    }

    /**
     * Notify the subscribers of the tower being sold
     * @param tower Tower
     * @return void
     */
    public void notifySubsribers(Tower tower) {
        for (SellTowerListener observer: observers) {
            observer.update(tower);
        }
    }

}
