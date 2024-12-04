package com.group34.Model.Shop;

import com.group34.Model.Tower.Factory.TowerFactory;

public interface IShopItem {
    TowerFactory getTowerTypeFactory();
    String getToolTipText();
}
