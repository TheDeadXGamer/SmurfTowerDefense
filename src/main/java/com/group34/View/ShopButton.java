package com.group34.View;

import java.awt.FlowLayout;
import java.awt.Image;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import com.group34.Model.Shop.ShopItem;


public class ShopButton extends JComponent {
    private ShopItem item;

    private static final Map<String, Image> towerImages = Map.of(
        "LightningSmurf", new ImageIcon(
            ShopButton.class.getResource(ViewConstants.LIGHTNINGSMURF_IMAGE))
            .getImage()
            .getScaledInstance(
                ViewConstants.TOWER_SIZE,
                ViewConstants.TOWER_SIZE,
                Image.SCALE_SMOOTH)
    );

    public ShopButton(ShopItem item) {
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

    public ShopItem getItem() {
        return item;
    }   
  
}