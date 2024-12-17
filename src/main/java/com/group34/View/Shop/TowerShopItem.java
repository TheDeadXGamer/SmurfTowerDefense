package com.group34.View.Shop;

import com.group34.Model.Tower.Attack;
import com.group34.Model.Tower.Tower;
import com.group34.Model.Tower.TowerFactory;

public class TowerShopItem implements IShopItem {
    private TowerFactory towerTypeFactory;
    private int cost;
    private String towerTypeName;

    public TowerShopItem(TowerFactory towerTypeFactory, int cost) {
        this.towerTypeFactory = towerTypeFactory;
        this.cost = cost;
        this.towerTypeName = towerTypeFactory.createTower(null).getTowerType();
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
        Attack tower = (Attack) towerTypeFactory.createTower(null); // temporary instance to get information
        String toolTipText = "<html>" + tower.getTowerType() +
                "<br>Damage: " + tower.getDamage() +
                "<br>Attack Speed: " + tower.getAttackSpeed() +
                "<br>Range: " + tower.getRange() + "</html>";
        return toolTipText;
    }
}
