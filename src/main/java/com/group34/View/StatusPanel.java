package com.group34.View;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.Objects;

import javax.swing.*;

import com.group34.Model.Game.Game;
import com.group34.Model.Game.GameObserver;
import com.group34.Model.Game.Player;
import com.group34.Model.Game.PlayerSubscriber;
import com.group34.Model.Shop.CashVault;
import com.group34.Model.Shop.CashVaultObserver;

/**
 * StatusPanel class is a JPanel that contains the player's health and cash.
 */
public class StatusPanel extends JPanel implements GameObserver, PlayerSubscriber, CashVaultObserver {

        
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
        private Game game;
        private CashVault cashVault;
        private Player player;

        public StatusPanel(
                Game game,
                CashVault cashVault,
                Player player
        ) {
                this.game = game;
                this.cashVault = cashVault;
                this.player = player;

                game.subscribe(this);
                cashVault.subscribe(this);
                player.subscribe(this);

                // size, layout, background
                setPreferredSize(ViewConstants.STATUS_PANEL_SIZE);
                setLayout(new GridLayout(3, 2));
                setBackground(ViewConstants.RIGHT_PANEL_COLOR);

                // images and text
                JLabel roundTitleLabel = new JLabel("Round");
                roundTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);

                roundLabel = new JLabel("" + game.getRoundNumber());

                JLabel healthImageLabel = new JLabel(new ImageIcon(healthImage));
                healthLabel = new JLabel("" + player.getHealth());

                JLabel cashImageLabel = new JLabel(new ImageIcon(coinImage));
                cashLabel = new JLabel("" + cashVault.getBalance());

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

        /**
         * Update the health label of the player.
         * @param health The new health of the player.
         * @return void
         */
        @Override
        public void updateHealth(int health) {
                healthLabel.setText("" + health);
        }

        /**
         * Update the cash label of the player.
         * @param cash The new cash of the player.
         * @return void
         */
        @Override
        public void updateCash(int cash) {
                cashLabel.setText("" + cash);
        }

        /**
         * Update the round label of the game.
         * @param round The new round of the game.
         * @return void
         */
        @Override
        public void updateRoundNumber(int round) {
                roundLabel.setText("" + round);
        }
}
