
package com.group34.View;


import com.group34.Controller.TowerSell;
import com.group34.Controller.TowerUpgrade;
import com.group34.Model.Tower.Tower;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpgradePanel extends JPanel {
    Tower currentTower;
    GridBagConstraints gbc = new GridBagConstraints();
    JLabel towerTitleLabel = new JLabel();
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
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(10, 10, 10, 10); // Add padding around the button

        add(upgradeButton, gbc);

        // Add the "Sell Tower" button at the bottom
        gbc.gridy = 1; // Position below the "Upgrade Tower" button
        gbc.insets = new Insets(100, 10, 10, 10); // Add more padding above the button

        add(sellButton, gbc);
    }
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
    public void populateUpgradePanel(Tower tower) {
        this.currentTower = tower;
        createTowerTitle();
    }
}
