package com.group34.Model.Shop;

import com.group34.Model.Tower.Tower;
import com.group34.Model.Tower.TowerFactory;

public interface IShopItem {
    TowerFactory getTowerTypeFactory();
    String getToolTipText();
}
