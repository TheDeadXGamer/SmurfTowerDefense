package com.group34.View;
import com.group34.Model.Game.GameController;
import com.group34.Model.Shop.IShopItem;
import com.group34.Model.Shop.ShopController;
import com.group34.Model.Tower.Tower;

import javax.swing.*;
import javax.imageio.ImageIO;
import javax.swing.text.View;
import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.dnd.*;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.awt.Graphics;
import java.io.InputStream;
import java.util.Objects;


public class GameView extends JPanel {
    private Image backgroundImage;
    private Image shopImage;
    private Image gargamelImage;
    private GameController controller;
    private ShopController shopController;

    /**
     * Constructor for GameView.
     * Makes the layout for the game and fills it with contents.
     * @param controller The game controller.
     * @param shopController The shop controller.
    **/
    public GameView(GameController controller, ShopController shopController) {
        this.controller = controller;
        this.shopController = shopController;

        setLayout(new BorderLayout());

        // Load background image
        backgroundImage = loadImage(ViewConstants.BASE_MAP_IMAGE_PATH);

        // Main game panel
        JPanel gamePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                }
                for (Tower tower : shopController.getShopModel().getPlayer().getTowers()) {
                    Point2D position = tower.getPosition();
                    Image image = loadImage(tower.getTowerImagePath()).getScaledInstance(ViewConstants.TOWER_SIZE, ViewConstants.TOWER_SIZE, Image.SCALE_SMOOTH);
                    g.drawImage(image, (int) position.getX(), (int) position.getY(), this);
                }
            }
        };

        // Game panel size
        Dimension gamePanelSize = new Dimension(ViewConstants.GAME_WIDTH, ViewConstants.GAME_HEIGHT);
        gamePanel.setPreferredSize(gamePanelSize);
        gamePanel.setMinimumSize(gamePanelSize);
        gamePanel.setMaximumSize(gamePanelSize);

        // Right panel, divided into buttons and shop
        JPanel rightPanel = new JPanel();
        rightPanel.setPreferredSize(new Dimension(ViewConstants.RIGHT_PANEL_WIDTH, ViewConstants.GAME_HEIGHT));
        rightPanel.setLayout(new BorderLayout());

        // Add the ButtonPanel to the right panel
        ButtonPanel buttonPanel = new ButtonPanel();

        // Add the ShopPanel to the right panel
        ShopPanel shopPanel = new ShopPanel(shopController);

        rightPanel.add(buttonPanel, BorderLayout.NORTH);
        rightPanel.add(shopPanel, BorderLayout.CENTER);

        // Making layered pane
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(ViewConstants.GAME_WIDTH, ViewConstants.GAME_HEIGHT));

        // Setting bounds for the panels
        gamePanel.setBounds(0, 0, ViewConstants.GAME_WIDTH, ViewConstants.GAME_HEIGHT);
        rightPanel.setBounds(ViewConstants.GAME_WIDTH - ViewConstants.RIGHT_PANEL_WIDTH, 0, ViewConstants.RIGHT_PANEL_WIDTH, ViewConstants.GAME_HEIGHT);

        // Adding the panels to the layered pane
        layeredPane.add(gamePanel, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(rightPanel, JLayeredPane.PALETTE_LAYER);

        // Finalizing
        setBackground(Color.BLACK);
        add(layeredPane, BorderLayout.CENTER);

        // Drag and drop for towers
        createTowerDropTarget(layeredPane);
    }

    /**
     * Drag and drop for towers.
     * @param gamePanel The main game panel, the panel on which the tower is to be dropped.
    **/
    private void createTowerDropTarget(JLayeredPane gamePanel) {
        // drag and drop for towers
        new DropTarget(gamePanel, new DropTargetAdapter() {
            @Override
            public void drop(DropTargetDropEvent dtde) {
                try {
                    Transferable transferable = dtde.getTransferable();
                    if (transferable.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                        dtde.acceptDrop(DnDConstants.ACTION_COPY);
                        String itemName = (String) transferable.getTransferData(DataFlavor.stringFlavor);
                        Point dropPoint = dtde.getLocation();
                        IShopItem item = findItemByName(itemName);
                        if (item != null && shopController.purchaseItem(item)) {
                            Tower tower = item.getTowerTypeFactory().createTower(dropPoint);
                            System.out.println("Dropped " + tower.getTowerType() + " at " + dropPoint);
                            shopController.getShopModel().getPlayer().addTower(tower);
                            updateView();
                        }
                        dtde.dropComplete(true);
                    } else {
                        dtde.rejectDrop();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    dtde.rejectDrop();
                }
            }
        });
    }

    /**
     * @param path Path to image.
     * @return Image from path.
     **/
    private Image loadImage(String path){
        try {
            InputStream imageStream = getClass().getResourceAsStream(path);
            if (imageStream == null) {
                throw new IOException("Image not found at path: " + path);
            }
            return ImageIO.read(imageStream);
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error loading image: " + path);
            return null;
        }
    }

    /**
     * @param name Name of the item.
     * @return The item with the given name.
    **/
    private IShopItem findItemByName(String name) {
        for (IShopItem item : shopController.getShopModel().getItems()) {
            if (item.getTowerTypeFactory().getTowerReference().getTowerType().equals(name)) {
                return item;
            }
        }
        return null;
    }

    /**
     * Simple method for updating the view.
    **/
    public void updateView() {
        revalidate();
        repaint();
    }
}