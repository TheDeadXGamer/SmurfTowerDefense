package com.group34.Model.Game;
import com.group34.Model.Shop.IShopItem;
import com.group34.Model.Shop.ShopController;
import com.group34.Model.Tower.Tower;

import javax.swing.*;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.dnd.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.Graphics;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Objects;


public class GameView extends JPanel {
    private Image backgroundImage;
    private Image shopImage;
    private Image gargamelImage;
    private Image lightningTowerImage;
    private GameController controller;
    private ShopController shopController;

    // colors
    private final Color rightPanelColor = new Color(255, 201, 54); // orange
    private final Color borderColor = Color.BLACK; // black

    // other constants
    private final int gameWidth = 800;
    private final int gameHeight = 600;
    private final int rightPanelWidth = gameWidth - 600;
    private final int buttonSize = 30;
    private final int towerSize = 80;

    public GameView(GameController controller, ShopController shopController) {
        this.controller = controller;
        this.shopController = shopController;

        setLayout(new BorderLayout());

        // load background image
        try {
            InputStream imageStream = getClass().getResourceAsStream("/assets/Maps/BaseMap.png");
            if (imageStream == null) {
                throw new IOException("Background image not found.");
            }
            backgroundImage = ImageIO.read(imageStream);
        } catch (IOException e) {
            System.out.println("Error loading background image");
        }

        // main game panel
        JPanel gamePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                }
                for (Tower tower : shopController.getShopModel().getPlayer().getTowers()) {
                    Point2D position = tower.getPosition();
                    Image image = loadImage(tower.getTowerImagePath()).getScaledInstance(towerSize, towerSize, Image.SCALE_SMOOTH);
                    g.drawImage(image, (int) position.getX(), (int) position.getY(), this);
                }
            }
        };

        // difficulty buttons
        JButton easyButton = new JButton("Easy");
        JButton mediumButton = new JButton("Medium");
        JButton hardButton = new JButton("Hard");

        easyButton.addActionListener(e -> controller.setDifficulty(Difficulty.EASY));
        mediumButton.addActionListener(e -> controller.setDifficulty(Difficulty.MEDIUM));
        hardButton.addActionListener(e -> controller.setDifficulty(Difficulty.HARD));

        gamePanel.add(easyButton);
        gamePanel.add(mediumButton);
        gamePanel.add(hardButton);

        // game panel size
        Dimension gamePanelSize = new Dimension(gameWidth, gameHeight);
        gamePanel.setPreferredSize(gamePanelSize);
        gamePanel.setMinimumSize(gamePanelSize);
        gamePanel.setMaximumSize(gamePanelSize);

        // right panel divided into player info and shop
        JPanel rightPanel = new JPanel();
        rightPanel.setPreferredSize(new Dimension(rightPanelWidth, gameHeight));
        rightPanel.setLayout(new BorderLayout());

        // panel for additional buttons (they don't do anything right now)
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        // settings button
        ImageIcon settingsIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/assets/Miscellaneous/settings-icon.png")));
        Image settingsImage = settingsIcon.getImage().getScaledInstance(buttonSize, buttonSize, Image.SCALE_SMOOTH);
        settingsIcon = new ImageIcon(settingsImage);
        JButton settingsButton = new JButton(settingsIcon);

        // fast-forward button
        ImageIcon fastForwardIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/assets/Miscellaneous/fastforward-icon.png")));
        Image fastForwardImage = fastForwardIcon.getImage().getScaledInstance(buttonSize, buttonSize, Image.SCALE_SMOOTH);
        fastForwardIcon = new ImageIcon(fastForwardImage);
        JButton fastForwardButton = new JButton(fastForwardIcon);

        buttonPanel.setBackground(rightPanelColor); // background color
        buttonPanel.setPreferredSize(new Dimension(rightPanelWidth, 50)); // fixed size

        buttonPanel.add(settingsButton);
        buttonPanel.add(fastForwardButton);

        // shop panel
        JPanel shopPanel = new JPanel();
        shopPanel.setLayout(new BorderLayout());

        // shop title
        JLabel shopTitle = new JLabel("<html><u>Towers</u></html>", SwingConstants.CENTER);
        shopTitle.setBackground(rightPanelColor); // background color
        shopTitle.setPreferredSize(new Dimension(rightPanelWidth, 30));
        shopTitle.setFont(new Font("Arial", Font.BOLD, 20));

        // panel to hold shop items
        JPanel shopItemsPanel = new JPanel();
        shopItemsPanel.setLayout(new GridBagLayout());
        shopItemsPanel.setBackground(rightPanelColor);
        shopItemsPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0)); // Add 10px top padding

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 0, 10, 0); // Add vertical padding between items
        gbc.fill = GridBagConstraints.HORIZONTAL; // Make items fill the cell horizontally
        gbc.weightx = 1.0;
        gbc.gridx = 0; // Single column
        gbc.anchor = GridBagConstraints.NORTH; // Anchor items to the top

        ArrayList<IShopItem> items = shopController.getShopModel().getItems();
        for (int i = 0; i < items.size(); i++) {
            IShopItem item = items.get(i);
            JPanel itemPanel = getTowerJPanel(shopController, item);
            itemPanel.setBackground(rightPanelColor);

            gbc.gridy = i; // New row for each item

            shopItemsPanel.add(itemPanel, gbc);
        }

        // Create a JScrollPane and add the shopItemsPanel to it
        JScrollPane scrollPane = new JScrollPane(shopItemsPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // Add the scrollPane to the shopPanel
        shopPanel.setBackground(rightPanelColor);
        shopPanel.add(shopTitle, BorderLayout.NORTH);
        shopPanel.add(scrollPane, BorderLayout.CENTER);

        rightPanel.add(buttonPanel, BorderLayout.NORTH);
        //rightPanel.add(playerInfoPanel, BorderLayout.CENTER);
        rightPanel.add(shopPanel, BorderLayout.CENTER);
        rightPanel.setBorder(BorderFactory.createLineBorder(borderColor));

        // making layered pane
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(gameWidth, gameHeight));

        gamePanel.setBounds(0, 0, gameWidth, gameHeight);
        rightPanel.setBounds(gameWidth - rightPanelWidth, 0, rightPanelWidth, gameHeight);

        layeredPane.add(gamePanel, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(rightPanel, JLayeredPane.PALETTE_LAYER);

        setBackground(Color.BLACK);
        add(layeredPane, BorderLayout.CENTER);

        // drag and drop for towers
        createTowerDropTarget(layeredPane);
    }

    /**
     * @return Drag and drop for towers.
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

    private IShopItem findItemByName(String name) {
        for (IShopItem item : shopController.getShopModel().getItems()) {
            if (item.getTowerTypeFactory().getTowerReference().getTowerType().equals(name)) {
                return item;
            }
        }
        return null;
    }

    /**
     * @return JPanel for tower in the shop.
    **/
    private JPanel getTowerJPanel(ShopController shopController, IShopItem item) {
        JPanel itemPanel = new JPanel();
        itemPanel.setLayout(new FlowLayout());

        JLabel itemImageLabel = new JLabel();
        Image towerImage = loadImage(item.getTowerTypeFactory().getTowerReference().getTowerImagePath());
        if (towerImage != null) {
            Image image = towerImage.getScaledInstance(towerSize, towerSize, Image.SCALE_SMOOTH);
            itemImageLabel.setIcon(new ImageIcon(image));
        } else {
            itemImageLabel.setIcon(createPlaceholderIcon());
        }
        itemImageLabel.setBorder(BorderFactory.createLineBorder(borderColor));
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

    public void updateView() {
        revalidate();
        repaint();
    }

    /**
     * @return Placeholder icon for towers (a red square).
    **/
    private Icon createPlaceholderIcon() {
        int width = towerSize;
        int height = towerSize;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = image.createGraphics();
        g2.setColor(Color.RED);
        g2.fillRect(0, 0, width, height);
        g2.dispose();
        return new ImageIcon(image);
    }
}