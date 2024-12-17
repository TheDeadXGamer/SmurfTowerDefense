package com.group34.View;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.group34.Controller.Shop.ShopController;

public class RightPanel extends JLabel {
    private ButtonPanel buttonPanel;
    private StatusPanel statusPanel;
    private ShopPanel shopPanel;

    public RightPanel(ShopController shopController) {
        setLayout(new BorderLayout());
        setPreferredSize(ViewConstants.SHOP_PANEL_SIZE);
        setBackground(ViewConstants.RIGHT_PANEL_COLOR);

        // panel for the buttonPanel and statusPanel
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());

        // Add ButtonPanel
        buttonPanel = new ButtonPanel();
        topPanel.add(buttonPanel, BorderLayout.NORTH);

        // Add StatusPanel
        statusPanel = new StatusPanel(shopController);
        topPanel.add(statusPanel, BorderLayout.SOUTH);

        add(topPanel, BorderLayout.NORTH);

        // Add ShopPanel
        shopPanel = new ShopPanel(shopController);
        add(shopPanel, BorderLayout.CENTER);
    }
}