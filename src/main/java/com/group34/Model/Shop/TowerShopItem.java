package com.group34.Model.Shop;

import com.group34.Model.Tower.TowerFactory;

public class TowerShopItem implements IShopItem {
    private TowerFactory towerTypeFactory;
    private int cost;
    private String towerTypeName;
    private String imagePath;

    public TowerShopItem(TowerFactory towerTypeFactory, int cost, String imagePath) {
        this.towerTypeFactory = towerTypeFactory;
        this.cost = cost;
        this.towerTypeName = towerTypeFactory.createTower(null).getTowerType(); // maybe bad to create a tower just to get its type
        this.imagePath = imagePath;
    }

    /**
     * @return the factory for the tower type.
     */
    @Override
    public TowerFactory getFactory() {
        return towerTypeFactory;
    }

    @Override
    public int getCost() {
        return cost;
    }

    @Override
    public String getTypeName() {
        return towerTypeName;
    }

    @Override
    public String getToolTipText() {
        return "Cost: " + cost; // TODO: more info needed
    }

    @Override
    public String getImagePath() {
        return imagePath; // Returnera den sparade bildsökvägen
    }
}
