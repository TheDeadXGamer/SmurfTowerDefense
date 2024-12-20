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

/**
 * ShopPanel class is a JPanel that contains the shop buttons.
 */
public class ShopPanel extends JPanel {
    List<ShopButtonComponent> shopButtonComponents = new ArrayList<>();

    /**
     * Constructor for ShopPanel.
     */
    public ShopPanel(List<ShopButtonComponent> buttons) {
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

    /**
     * Get the buttons in the shop.
     * @return Iterator of ShopButtonComponent.
     */
    public Iterator<ShopButtonComponent> getButtons() {
        return shopButtonComponents.iterator();
    }

    /**
     * Get the scroll pane for the shop.
     * @param itemsPanel JPanel containing the shop buttons.
     * @return JScrollPane.
     */
    private JScrollPane getScrollPane(JPanel itemsPanel) {
        JScrollPane scrollPane = new JScrollPane(itemsPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        return scrollPane;
    }

    /**
     * Get the panel containing the shop buttons.
     * @return JPanel.
     */
    private JPanel getItemsPanel() {
        JPanel itemsPanel = new JPanel();
        itemsPanel.setLayout(new GridBagLayout());
        itemsPanel.setBackground(ViewConstants.RIGHT_PANEL_COLOR);
        itemsPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        return itemsPanel;
    }

    /**
     * Populate the shop panel with shop buttons.
     * @param itemsPanel JPanel containing the shop buttons.
     * @param buttons List of ShopButtonComponent.
     */
    private void populateItems(
        JPanel itemsPanel, 
        List<ShopButtonComponent> buttons) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 0, 10, 0); // Add vertical padding between items
        gbc.fill = GridBagConstraints.HORIZONTAL; // Make items fill the cell horizontally
        gbc.weightx = 1.0;
        gbc.gridx = 0; // Single column
        gbc.anchor = GridBagConstraints.NORTH;

        for (int i = 0; i < buttons.size(); i++) {
            ShopButtonComponent itemPanel = buttons.get(i);
            itemPanel.setBackground(ViewConstants.RIGHT_PANEL_COLOR);
            gbc.gridy = i;

            // Create a panel to hold the item and its price
            JPanel itemWithPricePanel = new JPanel(new BorderLayout(5, 0)); // Add horizontal gap between image and price
            itemWithPricePanel.setBackground(ViewConstants.RIGHT_PANEL_COLOR);

            // Add the item panel
            itemWithPricePanel.add(itemPanel, BorderLayout.WEST);

            // Create and add the price label
            JLabel priceLabel = new JLabel("$" + itemPanel.getCost());
            priceLabel.setFont(new Font("Arial", Font.BOLD, 14));
            priceLabel.setHorizontalAlignment(SwingConstants.LEFT);
            itemWithPricePanel.add(priceLabel, BorderLayout.CENTER);

            itemsPanel.add(itemWithPricePanel, gbc);
        }
    }

    /**
     * Create the title for the shop panel.
     * @return JLabel.
     */
    private JLabel createTitle() {
        JLabel shopTitle = new JLabel("<html><u>Towers</u></html>", SwingConstants.CENTER);
        shopTitle.setBackground(ViewConstants.RIGHT_PANEL_COLOR);
        shopTitle.setPreferredSize(new Dimension(ViewConstants.RIGHT_PANEL_WIDTH, 30));
        shopTitle.setFont(new Font("Arial", Font.BOLD, 20));
        return shopTitle;
    }
}