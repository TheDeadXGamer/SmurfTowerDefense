package com.group34.Model.Shop;

import com.group34.Model.Tower.Attack;
import com.group34.Model.Tower.Tower;
import com.group34.Model.Tower.TowerFactory;

public class TowerShopItem implements IShopItem {
    private TowerFactory towerTypeFactory;

    public TowerShopItem(TowerFactory towerTypeFactory) {
        this.towerTypeFactory = towerTypeFactory;
    }

    @Override
    public TowerFactory getTowerTypeFactory() {
        return towerTypeFactory;
    }

    @Override
    public String getToolTipText() {
        Attack tower = towerTypeFactory.getTowerReference();
        return String.format("<html>Type: %s<br>Cost: %d<br>Range: %d<br>Damage: %d<br>Attack Speed: %d</html>",
                tower.getTowerType(), tower.getCost(), tower.getRange(), tower.getDamage(), tower.getAttackSpeed());
    }
}
