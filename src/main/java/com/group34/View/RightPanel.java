package com.group34.View;

import com.group34.Model.Tower.Tower;

import java.awt.*;

import javax.swing.JLabel;
import javax.swing.JPanel;


public class RightPanel extends JLabel {
    private CardLayout cardLayout; // For switching between panels
    private JPanel cardPanel; // Container for ShopPanel and UpgradePanel
    private UpgradePanel upgradePanel;

    public RightPanel(ShopPanel shopPanel, StatusPanel statusPanel, UpgradePanel upgradeScreen) {
        this.upgradePanel = upgradeScreen;
        setLayout(new BorderLayout());
        setPreferredSize(ViewConstants.SHOP_PANEL_SIZE);
        setBackground(ViewConstants.RIGHT_PANEL_COLOR);

        // panel for the buttonPanel and statusPanel
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());

        // Add ButtonPanel
        ButtonPanel buttonPanel = new ButtonPanel();
        topPanel.add(buttonPanel, BorderLayout.NORTH);

        // Add StatusPanel
        topPanel.add(statusPanel, BorderLayout.SOUTH);

        add(topPanel, BorderLayout.NORTH);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // Add ShopPanel
        cardPanel.add(shopPanel,"SHOP_PANEL");

        // Add UpgradePanel
        cardPanel.add(upgradeScreen,"UPGRADE_PANEL");

        add(cardPanel);
        cardLayout.show(cardPanel,"SHOP_PANEL");
    }

    public void displayShopPanel() {
        cardLayout.show(cardPanel,"SHOP_PANEL");
    }

    public void displayUpgradePanel(Tower tower) {
        cardLayout.show(cardPanel,"UPGRADE_PANEL");
        upgradePanel.setCurrentTower(tower);
    }
}
