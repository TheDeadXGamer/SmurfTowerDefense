package com.group34.View;

import java.awt.FlowLayout;
import java.awt.Image;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import com.group34.Model.Shop.ShopItem;

/**
 * ShopButtonComponent is a JComponent that represents a shop item in the shop panel.
 */
public class ShopButtonComponent extends JComponent {
    private ShopItem item;

    // Map of tower images
    private static final Map<String, Image> towerImages = Map.of(
        "LightningSmurf", new ImageIcon(
            ShopButtonComponent.class.getResource(ViewConstants.LIGHTNINGSMURF_IMAGE))
            .getImage()
            .getScaledInstance(
                ViewConstants.TOWER_SIZE,
                ViewConstants.TOWER_SIZE,
                Image.SCALE_SMOOTH),
        "FireSmurf", new ImageIcon(
            ShopButtonComponent.class.getResource(ViewConstants.FIRESMURF_IMAGE))
            .getImage()
            .getScaledInstance(
                ViewConstants.TOWER_SIZE,
                ViewConstants.TOWER_SIZE,
                Image.SCALE_SMOOTH),
        "WaterSmurf", new ImageIcon(
            ShopButtonComponent.class.getResource(ViewConstants.WATERSMURF_IMAGE))
            .getImage()
            .getScaledInstance(
                ViewConstants.TOWER_SIZE,
                ViewConstants.TOWER_SIZE,
                Image.SCALE_SMOOTH)
    );

    public ShopButtonComponent(ShopItem item) {
        this.item = item;
        setToolTipText(item.getName());

        setLayout(new FlowLayout());

        JLabel itemImageLabel = new JLabel();
        Image image = towerImages.get(item.getName()); 
        setBackground(ViewConstants.RIGHT_PANEL_COLOR);

        itemImageLabel.setIcon(new ImageIcon(image));
        itemImageLabel.setBorder(
            BorderFactory.createLineBorder(ViewConstants.BORDER_COLOR));
        add(itemImageLabel);
    }

    /**
     * Get the shop item.
     * @return ShopItem
     */
    public ShopItem getItem() {
        return item;
    }
}