package com.group34.View;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;
import javax.swing.JToolTip;
import javax.swing.ToolTipManager;

import com.group34.Model.Shop.ShopItem;


public class ShopItemTooltip extends JComponent {
    private ShopItem shopItem;

    public ShopItemTooltip(ShopItem shopItem) {
        this.shopItem = shopItem;
        setToolTipText(shopItem.getName());

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                JToolTip tooltip = createToolTip();
                tooltip.setTipText(shopItem.getName());
                ToolTipManager.sharedInstance().registerComponent(ShopItemTooltip.this);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                ToolTipManager.sharedInstance().unregisterComponent(ShopItemTooltip.this);
            }
        });
    }

  
}