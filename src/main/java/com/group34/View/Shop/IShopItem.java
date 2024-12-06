package com.group34.View.Shop;

import com.group34.Model.Tower.TowerFactory;

public interface IShopItem {
    TowerFactory getTowerTypeFactory();
    String getToolTipText();
}
