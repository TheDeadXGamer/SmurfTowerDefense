package com.group34.View;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.Objects;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.group34.Controller.Shop.ShopController;
import com.group34.Model.Game.Player;
import com.group34.Model.IObserver;
import com.group34.Model.Shop.CashVault;

public class StatusPanel extends JPanel implements IObserver {
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
    private ShopController shopController;
    private CashVault cashVault;
    private Player player;

    public StatusPanel(ShopController shopController) {
        this.shopController = shopController;

        // adding this as an observer to some observables
        cashVault = shopController.getCashVault();
        cashVault.addObserver(this);

        player = shopController.getPlayer();
        player.addObserver(this);

        // size, layout, background
        setPreferredSize(ViewConstants.STATUS_PANEL_SIZE);
        setLayout(new GridLayout(2, 2));
        setBackground(ViewConstants.RIGHT_PANEL_COLOR);

        // TODO: add label for rounds

        // images and text
        JLabel healthImageLabel = new JLabel(new ImageIcon(healthImage));
        healthLabel = new JLabel();
        JLabel cashImageLabel = new JLabel(new ImageIcon(coinImage));
        cashLabel = new JLabel();
        update();

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
    public void update() {
        int health = player.getHealth();
        int cashBalance = cashVault.getBalance();

        healthLabel.setText("" + health);
        cashLabel.setText("" + cashBalance);
    }
}
