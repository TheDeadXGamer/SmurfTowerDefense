package com.group34.View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.TransferHandler;

import com.group34.Controller.Shop.ShopController;
import com.group34.Controller.Shop.ShopItemComponent;
import com.group34.Model.Shop.IShopItem;

public class ShopPanel extends JPanel {
    private ShopController shopController;

    final Image smurfImage = new ImageIcon(
        getClass().getResource(ViewConstants.LIGHTNINGSMURF_IMAGE))
        .getImage()
        .getScaledInstance(
            ViewConstants.TOWER_SIZE,
            ViewConstants.TOWER_SIZE,
            Image.SCALE_SMOOTH
    );

    /**
     * Constructor for ShopPanel.
     * @param shopController
     */
    public ShopPanel(ShopController shopController) {
        this.shopController = shopController;
        setLayout(new BorderLayout());
        setPreferredSize(ViewConstants.SHOP_PANEL_SIZE);
        setBackground(ViewConstants.RIGHT_PANEL_COLOR);

        // Shop title
        JLabel shopTitle = new JLabel("<html><u>Towers</u></html>", SwingConstants.CENTER);
        shopTitle.setBackground(ViewConstants.RIGHT_PANEL_COLOR);
        shopTitle.setPreferredSize(new Dimension(ViewConstants.RIGHT_PANEL_WIDTH, 30));
        shopTitle.setFont(new Font("Arial", Font.BOLD, 20));

        // A JPanel to hold shop items
        JPanel itemsPanel = new JPanel();
        itemsPanel.setLayout(new GridBagLayout());
        itemsPanel.setBackground(ViewConstants.RIGHT_PANEL_COLOR);
        itemsPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0)); // Add 10px top padding

        // Grid stuff, not sure that it works
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 0, 10, 0); // Add vertical padding between items
        gbc.fill = GridBagConstraints.HORIZONTAL; // Make items fill the cell horizontally
        gbc.weightx = 1.0;
        gbc.gridx = 0; // Single column
        gbc.anchor = GridBagConstraints.NORTH; // Anchor items to the top

        // Adds items to the itemsPanel from the shopModel
        ArrayList<IShopItem> items = shopController.getItems();
        for (int i = 0; i < items.size(); i++) {
            IShopItem item = items.get(i);
            ShopItemComponent itemPanel = getTowerComponent(item);
            itemPanel.setBackground(ViewConstants.RIGHT_PANEL_COLOR);

            gbc.gridy = i; // New row for each item

            itemsPanel.add(itemPanel, gbc);
        }

        // Create a JScrollPane and add the itemsPanel to it
        JScrollPane scrollPane = new JScrollPane(itemsPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // Add the scrollPane to the shopPanel
        add(shopTitle, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        setBorder(BorderFactory.createLineBorder(ViewConstants.BORDER_COLOR));
    }

    /**
     * @param item The item to create a JComponent for.
     * @return A ShopItemComponent, a specific JComponent, for the item.
     **/
    private ShopItemComponent getTowerComponent(IShopItem item) {
        ShopItemComponent itemComponent = new ShopItemComponent(item);
        itemComponent.setLayout(new FlowLayout());

        JLabel itemImageLabel = new JLabel();
        Image towerImage = smurfImage; // TODO: make this work for different tower images
        if (towerImage != null) {
            Image image = towerImage.getScaledInstance(ViewConstants.TOWER_SIZE, ViewConstants.TOWER_SIZE, Image.SCALE_SMOOTH);
            itemImageLabel.setIcon(new ImageIcon(image));
        } else {
            itemImageLabel.setIcon(ViewConstants.createPlaceholderIcon());
        }
        itemImageLabel.setBorder(BorderFactory.createLineBorder(ViewConstants.BORDER_COLOR));
        itemComponent.add(itemImageLabel);

        itemComponent.setTransferHandler(new TransferHandler("text") {
            @Override
            protected Transferable createTransferable(JComponent c) {
                return new StringSelection(item.getTypeName());
            }

            @Override
            public int getSourceActions(JComponent c) {
                return COPY;
            }
        });

        itemComponent.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                JComponent comp = (JComponent) e.getSource();
                TransferHandler handler = comp.getTransferHandler();
                handler.exportAsDrag(comp, e, TransferHandler.COPY);
            }
        });

        return itemComponent;
    }
}