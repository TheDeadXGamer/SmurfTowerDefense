package com.group34.View;

import com.group34.Model.Cash.CashVault;
import com.group34.View.Shop.ShopController;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class StatusPanel extends JPanel {
    final Image healthImage = new ImageIcon(
            Objects.requireNonNull(getClass().getResource(ViewConstants.HEALTH_ICON_PATH))
    )
            .getImage()
            .getScaledInstance(
                    ViewConstants.STATUS_ICON_SIZE,
                    ViewConstants.STATUS_ICON_SIZE,
                    Image.SCALE_SMOOTH
            );

    final Image coinImage = new ImageIcon(
            getClass().getResource(ViewConstants.COIN_ICON_PATH)
    )
            .getImage()
            .getScaledInstance(
                    ViewConstants.STATUS_ICON_SIZE,
                    ViewConstants.STATUS_ICON_SIZE,
                    Image.SCALE_SMOOTH
            );

    JLabel healthLabel;
    JLabel cashLabel;
    ShopController shopController;

    public StatusPanel(ShopController shopController) {
        this.shopController = shopController;

        setPreferredSize(ViewConstants.STATUS_PANEL_SIZE); // fixed size

        setLayout(new GridLayout(2, 2));
        setBackground(ViewConstants.RIGHT_PANEL_COLOR); // background color
        int cashBalance = shopController.getCashVaultBalance();
        int health = shopController.getPlayerHealth();

        JLabel healthImageLabel = new JLabel(new ImageIcon(healthImage));
        healthLabel = new JLabel("" + health);
        JLabel cashImageLabel = new JLabel(new ImageIcon(coinImage));
        cashLabel = new JLabel("" + cashBalance);

        Font labelFont = new Font("Arial", Font.PLAIN, 16);
        healthLabel.setFont(labelFont);
        cashLabel.setFont(labelFont);

        add(cashImageLabel);
        add(cashLabel);
        add(healthImageLabel);
        add(healthLabel);
        setBorder(BorderFactory.createLineBorder(ViewConstants.BORDER_COLOR));
    }
    public void updateLabels() {
        int health = shopController.getPlayerHealth();
        int cashBalance = shopController.getCashVaultBalance();

        healthLabel.setText("" + health);
        cashLabel.setText("" + cashBalance);
    }

}
