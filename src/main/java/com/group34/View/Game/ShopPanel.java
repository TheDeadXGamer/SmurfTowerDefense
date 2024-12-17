package com.group34.View.Game;

import com.group34.View.Shop.IShopItem;
import com.group34.View.Shop.ShopController;
import com.group34.View.Shop.ShopItemComponent;
import com.group34.View.ViewConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class ShopPanel extends JPanel {
    private ShopController shopController;

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

        // Grid stuff
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 0, 10, 0); // Add vertical padding between items
        gbc.fill = GridBagConstraints.HORIZONTAL;
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

        // Hämta rätt bild från item.getImagePath()
        Image towerImage = new ImageIcon(
            getClass().getResource(item.getImagePath()) // Hämta bild via getImagePath
        ).getImage().getScaledInstance(
            ViewConstants.TOWER_SIZE,
            ViewConstants.TOWER_SIZE,
            Image.SCALE_SMOOTH
        );

        // Skapa en JLabel för bilden
        JLabel itemImageLabel = new JLabel();
        if (towerImage != null) {
            itemImageLabel.setIcon(new ImageIcon(towerImage));
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

            @Override
            public void exportAsDrag(JComponent comp, InputEvent e, int action) {
                JLabel label = (JLabel) comp.getComponent(0);
                Image image = ((ImageIcon) label.getIcon()).getImage();

                int offsetX = -ViewConstants.TOWER_SIZE / 2;
                int offsetY = -ViewConstants.TOWER_SIZE / 2;

                setDragImage(image);
                setDragImageOffset(new Point(offsetX, offsetY));

                super.exportAsDrag(comp, e, action);
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