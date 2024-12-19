package com.group34.View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;


public class ShopPanel extends JPanel {

    List<ShopButton> shopButtons = new ArrayList<>();

    /**
     * Constructor for ShopPanel.
     */
    public ShopPanel(List<ShopButton> buttons) {
        setLayout(new BorderLayout());
        setPreferredSize(ViewConstants.SHOP_PANEL_SIZE);
        setBackground(ViewConstants.RIGHT_PANEL_COLOR);
        add(createTitle(), BorderLayout.NORTH);

        JPanel itemsPanel = getItemsPanel();
        populateItems(itemsPanel, buttons);
        JScrollPane scrollPane = getScrollPane(itemsPanel);
        add(scrollPane, BorderLayout.CENTER);
        setBorder(BorderFactory.createLineBorder(ViewConstants.BORDER_COLOR));
    }

    public Iterator<ShopButton> getButtons() {
        return shopButtons.iterator();
    }

    private JScrollPane getScrollPane(JPanel itemsPanel) {
        JScrollPane scrollPane = new JScrollPane(itemsPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        return scrollPane;
    }

    private JPanel getItemsPanel() {
        JPanel itemsPanel = new JPanel();
        itemsPanel.setLayout(new GridBagLayout());
        itemsPanel.setBackground(ViewConstants.RIGHT_PANEL_COLOR);
        itemsPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        return itemsPanel;
    }

    private void populateItems(
        JPanel itemsPanel,
        List<ShopButton> buttons)
    {
        // Grid stuff, not sure that it works
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 0, 10, 0); // Add vertical padding between items
        gbc.fill = GridBagConstraints.HORIZONTAL; // Make items fill the cell horizontally
        gbc.weightx = 1.0;
        gbc.gridx = 0; // Single column
        gbc.anchor = GridBagConstraints.NORTH;

        for (int i = 0; i < buttons.size(); i++) {
            ShopButton itemPanel = buttons.get(i);
            itemPanel.setBackground(ViewConstants.RIGHT_PANEL_COLOR);
            gbc.gridy = i;// New row for each item
            itemsPanel.add(itemPanel, gbc);
        }

    }

    private JLabel createTitle() {
        JLabel shopTitle = new JLabel("<html><u>Towers</u></html>", SwingConstants.CENTER);
        shopTitle.setBackground(ViewConstants.RIGHT_PANEL_COLOR);
        shopTitle.setPreferredSize(new Dimension(ViewConstants.RIGHT_PANEL_WIDTH, 30));
        shopTitle.setFont(new Font("Arial", Font.BOLD, 20));
        return shopTitle;
    }


}