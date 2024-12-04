package com.group34.View;

import com.group34.Model.Game.GameController;
import com.group34.Model.Shop.IShopItem;
import com.group34.Model.Shop.ShopController;
import com.group34.Model.Tower.Tower;

import javax.swing.*;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.*;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class GameView extends JPanel {

    private final GameController gameController;
    private final ShopController shopController;

    private Image backgroundImage;
    private Image coinsIcon;
    private Image healthIcon;

    public GameView(GameController gameController, ShopController shopController) {
        this.gameController = gameController;
        this.shopController = shopController;

        setLayout(null); 
        setBackground(Color.BLACK);

        loadResources();

        createTowerDropTarget(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);        

        // Render content
        drawBackground(g);
        drawPlayerStats(g);
        drawTowers(g);
    }

    private void loadResources() {
        backgroundImage = loadImage("/assets/Maps/BaseMap.png");
        coinsIcon = loadImage("/assets/Miscellaneous/Smurfcoins.png");
        healthIcon = loadImage("/assets/Miscellaneous/Smurfhealth.png");
    }

    private void drawBackground(Graphics g) {
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        } else {
            g.setColor(Color.DARK_GRAY);
            g.fillRect(0, 0, getWidth(), getHeight());
        }
    }

    private void drawPlayerStats(Graphics g) {
        int iconWidth = getWidth() / 16;
        int iconHeight = getHeight() / 10;

        int coinsIconX = getWidth() / 50;
        int coinsIconY = getHeight() / 50;

        drawIcon(g, coinsIcon, coinsIconX, coinsIconY, iconWidth, iconHeight);
        g.drawString(" " + gameController.getPlayerMoney(), coinsIconX + iconWidth + 10, coinsIconY + iconHeight / 2);

        int healthIconX = coinsIconX + getWidth() / 6;
        int healthIconY = coinsIconY;

        drawIcon(g, healthIcon, healthIconX, healthIconY, iconWidth, iconHeight);
        g.drawString(" " + gameController.getPlayerHealth(), healthIconX + iconWidth + 10, healthIconY + iconHeight / 2);
    }

    private void drawTowers(Graphics g) {
        List<Tower> towers = shopController.getShopModel().getPlayer().getTowers();
        if (towers != null && !towers.isEmpty()) {
            for (Tower tower : towers) {
                Point2D position = tower.getPosition();
                if (position != null) {
                    Image image = loadImage(tower.getTowerImagePath())
                            .getScaledInstance(ViewConstants.TOWER_SIZE, ViewConstants.TOWER_SIZE, Image.SCALE_SMOOTH);

                    if (image != null) {
                        g.drawImage(image, (int) position.getX(), (int) position.getY(), this);
                    } else {
                        System.out.println("Tower image is null for: " + tower.getTowerType());
                    }
                } else {
                    System.out.println("Tower position is null for: " + tower.getTowerType());
                }
            }
        } else {
            System.out.println("No towers to render.");
        }
    }

    private void createTowerDropTarget(JPanel panel) {
        new DropTarget(panel, new DropTargetAdapter() {
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
                            tower.setPosition(new Point2D.Double(dropPoint.getX(), dropPoint.getY()));

                            shopController.getShopModel().getPlayer().addTower(tower);

                            System.out.println("Tower placed at: " + dropPoint);

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

    private Image loadImage(String path) {
        try {
            InputStream imageStream = getClass().getResourceAsStream(path);
            if (imageStream == null) {
                System.out.println("Image not found at: " + path);
                return null;
            }
            return ImageIO.read(imageStream);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void drawIcon(Graphics g, Image icon, int x, int y, int width, int height) {
        if (icon != null) {
            g.drawImage(icon, x, y, width, height, this);
        } else {
            System.out.println("Warning: Attempted to draw a null icon.");
        }
    }


    
    private IShopItem findItemByName(String name) {
        for (IShopItem item : shopController.getShopModel().getItems()) {
            if (item.getTowerTypeFactory().getTowerReference().getTowerType().equals(name)) {
                return item;
            }
        }
        return null;
    }

    public void updateView() {
        revalidate();
        repaint();
    }
}
