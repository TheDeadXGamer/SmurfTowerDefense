package com.group34.View;

import com.group34.Model.Shop.IShopItem;
import com.group34.Model.Shop.ShopController;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
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

        // Grid stuff, not sure that it works
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 0, 10, 0); // Add vertical padding between items
        gbc.fill = GridBagConstraints.HORIZONTAL; // Make items fill the cell horizontally
        gbc.weightx = 1.0;
        gbc.gridx = 0; // Single column
        gbc.anchor = GridBagConstraints.NORTH; // Anchor items to the top

        // Adds items to the itemsPanel from the shopModel
        ArrayList<IShopItem> items = shopController.getShopModel().getItems();
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
        ShopItemComponent itemPanel = new ShopItemComponent(item);
        itemPanel.setLayout(new FlowLayout());

        JLabel itemImageLabel = new JLabel();
        Image towerImage = loadImage(item.getTowerTypeFactory().getTowerReference().getTowerImagePath());
        if (towerImage != null) {
            Image image = towerImage.getScaledInstance(ViewConstants.TOWER_SIZE, ViewConstants.TOWER_SIZE, Image.SCALE_SMOOTH);
            itemImageLabel.setIcon(new ImageIcon(image));
        } else {
            itemImageLabel.setIcon(createPlaceholderIcon());
        }
        itemImageLabel.setBorder(BorderFactory.createLineBorder(ViewConstants.BORDER_COLOR));
        itemPanel.add(itemImageLabel);

        itemPanel.setTransferHandler(new TransferHandler("text") {
            @Override
            protected Transferable createTransferable(JComponent c) {
                return new StringSelection(item.getTowerTypeFactory().getTowerReference().getTowerType());
            }

            @Override
            public int getSourceActions(JComponent c) {
                return COPY;
            }
        });

        itemPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                JComponent comp = (JComponent) e.getSource();
                TransferHandler handler = comp.getTransferHandler();
                handler.exportAsDrag(comp, e, TransferHandler.COPY);
            }
        });

        return itemPanel;
    }

    /**
     * TODO: This is code reuse (also in GameView).
     * @param path Path to image.
     * @return Image from path.
     **/
    private Image loadImage(String path) {
        try {
            InputStream imageStream = getClass().getResourceAsStream(path);
            if (imageStream == null) {
                throw new IOException("Image not found at path: " + path);
            }
            return ImageIO.read(imageStream);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error loading image: " + path);
            return null;
        }
    }

    /**
     *  Creates a placeholder icon, a red square.
     * @return a placeholder icon.
    **/
    private Icon createPlaceholderIcon() {
        int width = ViewConstants.TOWER_SIZE;
        int height = ViewConstants.TOWER_SIZE;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = image.createGraphics();
        g2.setColor(Color.RED);
        g2.fillRect(0, 0, width, height);
        g2.dispose();
        return new ImageIcon(image);
    }
}