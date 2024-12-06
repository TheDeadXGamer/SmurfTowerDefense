package com.group34.View;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class ButtonPanel extends JPanel {

    public ButtonPanel() {
        setLayout(new FlowLayout());
        setBackground(ViewConstants.RIGHT_PANEL_COLOR); // background color
        setPreferredSize(ViewConstants.BUTTON_PANEL_SIZE); // fixed size

        // settings button
        ImageIcon settingsIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/assets/Miscellaneous/settings-icon.png")));
        Image settingsImage = settingsIcon.getImage().getScaledInstance(ViewConstants.BUTTON_SIZE, ViewConstants.BUTTON_SIZE, Image.SCALE_SMOOTH);
        settingsIcon = new ImageIcon(settingsImage);
        JButton settingsButton = new JButton(settingsIcon);

        // fast-forward button
        ImageIcon fastForwardIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/assets/Miscellaneous/fastforward-icon.png")));
        Image fastForwardImage = fastForwardIcon.getImage().getScaledInstance(ViewConstants.BUTTON_SIZE, ViewConstants.BUTTON_SIZE, Image.SCALE_SMOOTH);
        fastForwardIcon = new ImageIcon(fastForwardImage);
        JButton fastForwardButton = new JButton(fastForwardIcon);

        add(settingsButton);
        add(fastForwardButton);
        setBorder(BorderFactory.createLineBorder(ViewConstants.BORDER_COLOR));
    }
}