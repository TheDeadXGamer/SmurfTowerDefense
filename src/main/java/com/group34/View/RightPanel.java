package com.group34.View;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;


public class RightPanel extends JLabel {

    ButtonPanel buttonPanel;

    public RightPanel(ShopPanel shopPanel, StatusPanel statusPanel) {
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
        topPanel.add(statusPanel, BorderLayout.SOUTH);

        add(topPanel, BorderLayout.NORTH);

        // Add ShopPanel
        add(shopPanel, BorderLayout.CENTER);
    }

    public ButtonPanel getButtonPanel() {
        return buttonPanel;
    }
}