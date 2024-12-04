package com.group34.View;

import com.group34.Model.Shop.IShopItem;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ShopItemComponent extends JComponent {
    private IShopItem shopItem;

    public ShopItemComponent(IShopItem shopItem) {
        this.shopItem = shopItem;
        setToolTipText(shopItem.getToolTipText());

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                JToolTip tooltip = createToolTip();
                tooltip.setTipText(shopItem.getToolTipText());
                ToolTipManager.sharedInstance().registerComponent(ShopItemComponent.this);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                ToolTipManager.sharedInstance().unregisterComponent(ShopItemComponent.this);
            }
        });
    }

}
