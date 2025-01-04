
package com.group34.View;


import com.group34.Controller.TowerSell;
import com.group34.Controller.TowerUpgrade;
import com.group34.Model.Tower.Tower;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.group34.Model.Shop.Shop.REFUND_FACTOR;

/**
 * UpgradePanel class is a JPanel that contains the upgrade and sell buttons for the tower.
 */
public class UpgradePanel extends JPanel {
    Tower currentTower;
    GridBagConstraints gbc = new GridBagConstraints();
    JLabel towerTitleLabel = new JLabel();
    JLabel upgradePriceLabel = new JLabel();
    JLabel sellPriceLabel = new JLabel();

    public UpgradePanel(TowerUpgrade towerUpgrade, TowerSell sellTower) {
        // Set the layout manager
        setLayout(new GridBagLayout());

        setBackground(ViewConstants.RIGHT_PANEL_COLOR);
        setBorder(BorderFactory.createLineBorder(ViewConstants.BORDER_COLOR));

        // Create the button
        JButton upgradeButton = new JButton("Upgrade Tower");

        upgradeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                towerUpgrade.upgradeTower(currentTower);
                updatePrices();
            }
        });

        // Create the "Sell Tower" button
        JButton sellButton = new JButton("Sell Tower");
        sellButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sellTower.sellTower(currentTower);
            }
        });

        // Add the label to the panel using GridBagLayout
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10); // Add padding around the label
        gbc.fill = GridBagConstraints.HORIZONTAL;  // Make the label stretch horizontally
        add(towerTitleLabel, gbc);

        gbc.anchor = GridBagConstraints.NORTH; // Align the label to the top of the panel

        gbc.gridy = 1;
        gbc.insets = new Insets(10, 10, 10, 10);
        add(upgradeButton, gbc);

        gbc.gridy = 2;
        gbc.insets = new Insets(0, 10, 0, 10);
        upgradePriceLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(upgradePriceLabel, gbc);

        // Add the "Sell Tower" button at the bottom
        gbc.gridy = 3; // Position below the "Upgrade Tower" button
        gbc.insets = new Insets(100, 10, 10, 10); // Add more padding above the button
        add(sellButton, gbc);

        gbc.gridy = 4;
        gbc.insets = new Insets(0, 10, 0, 10);
        sellPriceLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(sellPriceLabel, gbc);
    }

    /**
     * Create the title for the tower that is selected.
     * @return void
     */
    public void createTowerTitle() {
        towerTitleLabel.removeAll();
        String towerName = currentTower.getTowerType();
        // Create the JLabel to display text at the top
        towerTitleLabel.setText("<html><div style='text-align: center;'>" + towerName + "<br>Upgrade options</div></html>");
        towerTitleLabel.setFont(new Font("Arial", Font.BOLD, 16)); // Set font size and style if needed

        // Create GridBagConstraints for the label

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10); // Add padding around the label
    }

    /**
     * Populate the upgrade panel with the tower that is selected.
     * @param tower The tower that is selected.
     * @return void
     */
    public void populateUpgradePanel(Tower tower) {
        this.currentTower = tower;
        createTowerTitle();
        updatePrices();
    }

    /**
     * Update the upgrade and sell price of the tower.
     * @return void
     */
    public void updatePrices() {
        int upgradeCost = currentTower.getUpgradeCost();
        if (upgradeCost == 0) {
            upgradePriceLabel.setText("Not available");
        } else {
            upgradePriceLabel.setText("Cost: $" + upgradeCost);
        }
        sellPriceLabel.setText("Refund: $" + (int) (currentTower.getCost() * REFUND_FACTOR));
    }
}
