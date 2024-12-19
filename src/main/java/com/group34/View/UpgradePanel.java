
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

    public UpgradePanel(TowerUpgrade towerUpgrade, TowerSell sellTower) {
        // Set the layout manager
        setLayout(new GridBagLayout());

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

        // Add the button to the center with constraints
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; // Center horizontally
        gbc.gridy = 0; // Center vertically
        gbc.insets = new Insets(10, 10, 10, 10); // Add padding around the button

        add(upgradeButton, gbc);

        // Add the "Sell Tower" button at the bottom
        gbc.gridy = 1; // Position below the "Upgrade Tower" button
        gbc.insets = new Insets(20, 10, 10, 10); // Add more padding above the button

        add(sellButton, gbc);
    }
    public void setCurrentTower(Tower tower) {
        this.currentTower = tower;
    }
}
