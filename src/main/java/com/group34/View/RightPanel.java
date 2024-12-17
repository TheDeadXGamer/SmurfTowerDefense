package com.group34.View;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;


public class RightPanel extends JLabel {
    private ButtonPanel buttonPanel;
    private StatusPanel statusPanel;


    public RightPanel(ShopPanel shopPanel) {
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
        statusPanel = new StatusPanel();
        topPanel.add(statusPanel, BorderLayout.SOUTH);

        add(topPanel, BorderLayout.NORTH);

        // Add ShopPanel
        add(shopPanel, BorderLayout.CENTER);
    }
}