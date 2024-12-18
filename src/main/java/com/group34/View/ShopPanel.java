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
import java.util.Iterator;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.TransferHandler;

import com.group34.Model.Shop.Shop;
import com.group34.Model.Shop.ShopItem;


public class ShopPanel extends JPanel {

    private Map<String, Image> towerImages = Map.of(
        "LightningSmurf", new ImageIcon(
            getClass().getResource(ViewConstants.LIGHTNINGSMURF_IMAGE))
            .getImage()
            .getScaledInstance(
                ViewConstants.TOWER_SIZE,
                ViewConstants.TOWER_SIZE,
                Image.SCALE_SMOOTH)
    );
    

    /**
     * Constructor for ShopPanel.
     * @param shopController
     */
    public ShopPanel(Shop shop) {
        setLayout(new BorderLayout());
        setPreferredSize(ViewConstants.SHOP_PANEL_SIZE);
        setBackground(ViewConstants.RIGHT_PANEL_COLOR);

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
        gbc.anchor = GridBagConstraints.NORTH;

        // Adds items to the itemsPanel from the shopModel
        Iterator<ShopItem> items = shop.getItems();
        int counter = 0;
        while (items.hasNext()) {
            ShopItem item = items.next();
            ShopItemTooltip itemPanel = getTowerComponent(item);
            itemPanel.setBackground(ViewConstants.RIGHT_PANEL_COLOR);

            gbc.gridy = counter;
            counter ++; // New row for each item

            itemsPanel.add(itemPanel, gbc);
        }

        // Create a JScrollPane and add the itemsPanel to it
        JScrollPane scrollPane = new JScrollPane(itemsPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // Add the scrollPane to the shopPanel
        add(createTitle(), BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        setBorder(BorderFactory.createLineBorder(ViewConstants.BORDER_COLOR));
    }

     /**
     * @param item The item to create a JComponent for.
     * @return A ShopItemComponent, a specific JComponent, for the item.
     **/
    private ShopItemTooltip getTowerComponent(ShopItem item) {
        ShopItemTooltip itemComponent = new ShopItemTooltip(item);
        itemComponent.setLayout(new FlowLayout());

        JLabel itemImageLabel = new JLabel();
        Image towerImage = towerImages.get(item.getName()); 

        if (towerImage != null) {
            Image image = towerImage.getScaledInstance(ViewConstants.TOWER_SIZE, ViewConstants.TOWER_SIZE, Image.SCALE_SMOOTH);
            itemImageLabel.setIcon(new ImageIcon(image));
        } else {
            itemImageLabel.setIcon(ViewConstants.createPlaceholderIcon());
        }
        itemImageLabel.setBorder(BorderFactory.createLineBorder(ViewConstants.BORDER_COLOR));
        itemComponent.add(itemImageLabel);

        // price tag
        JLabel priceLabel = new JLabel("$" + item.getCost());
        priceLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        priceLabel.setHorizontalAlignment(SwingConstants.CENTER);
        itemComponent.add(priceLabel, BorderLayout.SOUTH);

        itemComponent.setTransferHandler(new TransferHandler("text") {
            @Override
            protected Transferable createTransferable(JComponent c) {
                return new StringSelection(item.getName());
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

    private JLabel createTitle() {
        JLabel shopTitle = new JLabel("<html><u>Towers</u></html>", SwingConstants.CENTER);
        shopTitle.setBackground(ViewConstants.RIGHT_PANEL_COLOR);
        shopTitle.setPreferredSize(new Dimension(ViewConstants.RIGHT_PANEL_WIDTH, 30));
        shopTitle.setFont(new Font("Arial", Font.BOLD, 20));
        return shopTitle;
    }


}