package com.group34.View;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.Objects;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.group34.Model.Game.Player;
import com.group34.Model.Game.PlayerSubscriber;
import com.group34.Model.Shop.CashVault;
import com.group34.Model.Shop.CashVaultObserver;

public class StatusPanel extends JPanel implements PlayerSubscriber, CashVaultObserver {
    private final Image healthImage = new ImageIcon(
            Objects.requireNonNull(getClass().getResource(ViewConstants.HEALTH_ICON_PATH))
    )
            .getImage()
            .getScaledInstance(
                    ViewConstants.STATUS_ICON_SIZE,
                    ViewConstants.STATUS_ICON_SIZE,
                    Image.SCALE_SMOOTH
            );

    private final Image coinImage = new ImageIcon(
            getClass().getResource(ViewConstants.COIN_ICON_PATH)
    )
            .getImage()
            .getScaledInstance(
                    ViewConstants.STATUS_ICON_SIZE,
                    ViewConstants.STATUS_ICON_SIZE,
                    Image.SCALE_SMOOTH
            );

    private JLabel healthLabel;
    private JLabel cashLabel;
    private CashVault cashVault;
    private Player player;

    public StatusPanel(
        CashVault cashVault,
        Player player
    ) {

        this.cashVault = cashVault;
        this.player = player;
        cashVault.subscribe(this);
        player.subscribe(this);


        // size, layout, background
        setPreferredSize(ViewConstants.STATUS_PANEL_SIZE);
        setLayout(new GridLayout(2, 2));
        setBackground(ViewConstants.RIGHT_PANEL_COLOR);



        // images and text
        JLabel healthImageLabel = new JLabel(new ImageIcon(healthImage));
        healthLabel = new JLabel("" + player.getHealth());

        JLabel cashImageLabel = new JLabel(new ImageIcon(coinImage));
        cashLabel = new JLabel("" + cashVault.getBalance());
        // update();

        // font
        Font labelFont = new Font("Arial", Font.PLAIN, 16);
        healthLabel.setFont(labelFont);
        cashLabel.setFont(labelFont);

        // finalizing
        add(cashImageLabel);
        add(cashLabel);
        add(healthImageLabel);
        add(healthLabel);
        setBorder(BorderFactory.createLineBorder(ViewConstants.BORDER_COLOR));
    }

    @Override
    public void updateHealth(int health) {
        healthLabel.setText("" + health);
    }


    @Override
    public void updateCash(int cash) {
        cashLabel.setText("" + cash);
    }

}
