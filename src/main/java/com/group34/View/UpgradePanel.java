
package com.group34.View;


import com.group34.Controller.TowerUpgrade;
import com.group34.Model.Tower.NoUpgradeError;
import com.group34.Model.Tower.Tower;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpgradePanel extends JPanel {

    Tower currTower;
    public UpgradePanel(TowerUpgrade towerUpgrade) {
        // Set the layout manager
        setLayout(new GridBagLayout());

        // Create the button
        JButton upgradeButton = new JButton("Upgrade Tower");
        upgradeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                towerUpgrade.upgradeTower(currTower);
            }


        });
        // Add the button to the center with constraints
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; // Center horizontally
        gbc.gridy = 0; // Center vertically
        gbc.insets = new Insets(10, 10, 10, 10); // Add padding around the button

        add(upgradeButton, gbc);
    }
    public void setCurrTower(Tower tower) {
        this.currTower = tower;
    }
}
