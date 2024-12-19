package com.group34.Controller;

import com.group34.Model.Board.InvalidRemovalError;
import com.group34.Model.Shop.Shop;
import com.group34.Model.Tower.Tower;
import com.group34.View.SellTowerListener;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class TowerSell {
    private Shop shop;
    private ArrayList<SellTowerListener> observers = new ArrayList<>();
    public TowerSell(Shop shop) {
        this.shop = shop;
    }

    public void sellTower(Tower tower) {
        try {
            shop.refundTower(tower);
            notifySubsribers(tower);
        } catch (InvalidRemovalError e) {
             // TODO: handle exception
        }
    }

    public void subscribe(SellTowerListener observer) {
        observers.add(observer);
    }
    public void unsubscribe(SellTowerListener observer) {
        observers.remove(observer);
    }
    public void notifySubsribers(Tower tower) {
        for (SellTowerListener observer: observers) {
            observer.update(tower);
        }
    }

}
