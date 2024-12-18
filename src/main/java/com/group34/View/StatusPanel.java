package com.group34.View;

import com.group34.Model.Cash.CashVault;
import com.group34.Model.Game.Player;
import com.group34.Model.IObserver;
import com.group34.View.Shop.ShopController;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

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
    private JLabel roundLabel;
    private JLabel healthLabel;
    private JLabel cashLabel;
    private ShopController shopController;
    private StatusModel statusModel;

    public StatusPanel(ShopController shopController) {
        this.shopController = shopController;

        // adding this as an observer to the status model (which in turn registers as an observer to a bunch of observables)
        statusModel = new StatusModel(shopController.getCashVault(), shopController.getPlayer(), shopController.getGame());
        statusModel.addObserver(this);

        // size, layout, background
        setPreferredSize(ViewConstants.STATUS_PANEL_SIZE);
        setLayout(new GridLayout(3, 2));
        setBackground(ViewConstants.RIGHT_PANEL_COLOR);



        // images and text
        JLabel roundTitleLabel = new JLabel("Round: ");
        roundTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        roundLabel = new JLabel();
        JLabel healthImageLabel = new JLabel(new ImageIcon(healthImage));
        healthLabel = new JLabel();
        JLabel cashImageLabel = new JLabel(new ImageIcon(coinImage));
        cashLabel = new JLabel();
        update();

        // font
        Font labelFont = new Font("Arial", Font.PLAIN, 16);
        roundTitleLabel.setFont(labelFont);
        roundLabel.setFont(labelFont);
        healthLabel.setFont(labelFont);
        cashLabel.setFont(labelFont);

        // finalizing
        add(roundTitleLabel);
        add(roundLabel);
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


    @Override
    public void updateCash(int cash) {
        cashLabel.setText("" + cash);
    }

}
