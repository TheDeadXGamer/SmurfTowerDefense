package com.group34.View;

import com.group34.View.Game.ShopPanel;
import com.group34.View.Shop.ShopController;
import com.group34.View.ViewConstants;
import jdk.jshell.Snippet;

import javax.swing.*;
import java.awt.*;

public class RightPanel extends JPanel {
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
    public void updateStatusPanel() {
        statusPanel.updateLabels();
    }
}