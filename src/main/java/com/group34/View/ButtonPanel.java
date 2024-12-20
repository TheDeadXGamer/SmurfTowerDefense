package com.group34.View;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

/**
 * ButtonPanel class is a JPanel that contains the settings and fast-forward buttons.
 */
public class ButtonPanel extends JPanel {

        private JButton settingsButton;
        private JButton fastForwardButton;

        // settings button image
        final Image settingsImage = new ImageIcon(
                getClass().getResource(ViewConstants.SETTINGS_ICON_PATH)
        )
        .getImage()
        .getScaledInstance(
                ViewConstants.BUTTON_SIZE,
                ViewConstants.BUTTON_SIZE,
                Image.SCALE_SMOOTH
        );

        public ButtonPanel() {
        setLayout(new FlowLayout());
        setBackground(ViewConstants.RIGHT_PANEL_COLOR); // background color
        setPreferredSize(ViewConstants.BUTTON_PANEL_SIZE); // fixed size


        // settings button
        ImageIcon settingsIcon = new ImageIcon(settingsImage);
        settingsButton = new JButton(settingsIcon);

        // fast-forward button
        ImageIcon fastForwardIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource(ViewConstants.FASTFORWARD_ICON_PATH)));
        Image fastForwardImage = fastForwardIcon.getImage().getScaledInstance(ViewConstants.BUTTON_SIZE, ViewConstants.BUTTON_SIZE, Image.SCALE_SMOOTH);
        fastForwardIcon = new ImageIcon(fastForwardImage);
        fastForwardButton = new JButton(fastForwardIcon);

        add(settingsButton);
        add(fastForwardButton);
        setBorder(BorderFactory.createLineBorder(ViewConstants.BORDER_COLOR));
        }

        /**
         * Getter for settings button
         * @return settingsButton
         */
        public JButton getSettingsButton() {
            return settingsButton;
        }

        /**
         * Getter for fast-forward button
         * @return fastForwardButton
         */
        public JButton getFastForwardButton() {
            return fastForwardButton;
        }
}