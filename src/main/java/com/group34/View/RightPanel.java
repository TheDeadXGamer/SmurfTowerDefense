package com.group34.View;

import com.group34.Model.Tower.Tower;

import java.awt.*;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * RightPanel class is a JPanel that contains the ButtonPanel, ShopPanel, and UpgradePanel.
 */
public class RightPanel extends JLabel {

    private ButtonPanel buttonPanel;
    private CardLayout cardLayout; // For switching between panels
    private JPanel cardPanel; // Container for ShopPanel and UpgradePanel
    private UpgradePanel upgradePanel;

    public RightPanel(ShopPanel shopPanel, StatusPanel statusPanel, UpgradePanel upgradePanel) {
        this.upgradePanel = upgradePanel;
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

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // Add ShopPanel
        cardPanel.add(shopPanel,"SHOP_PANEL");

        // Add UpgradePanel
        cardPanel.add(upgradePanel,"UPGRADE_PANEL");

        add(cardPanel);
        cardLayout.show(cardPanel,"SHOP_PANEL");
    }

    /**
     * Method to display the shop panel.
     * @return void
     */
    public void displayShopPanel() {
        cardLayout.show(cardPanel,"SHOP_PANEL");
    }

    /**
     * Method to display the upgrade panel.
     * @param tower Tower object
     * @return void
     */
    public void displayUpgradePanel(Tower tower) {
        cardLayout.show(cardPanel,"UPGRADE_PANEL");
        upgradePanel.populateUpgradePanel(tower);
    }
    
    /**
     * Method to get the ButtonPanel object.
     * @return ButtonPanel
     */
    public ButtonPanel getButtonPanel() {
        return buttonPanel;
    }
}
