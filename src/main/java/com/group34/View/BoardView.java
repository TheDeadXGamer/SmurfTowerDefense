package com.group34.View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.util.Iterator;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import com.group34.Controller.Shop.ShopController;
import com.group34.Model.Board.Board;
import com.group34.Model.Enemy.Enemy;
import com.group34.Model.Game.Game;
import com.group34.Model.Projectile.Projectile;
import com.group34.Model.Tower.Tower;

public class BoardView extends JPanel {
    private String temporaryMessage = null;
    private boolean showTemporaryMessage = false;
    private Timer errorTimer;
    public Board board;
    public Game game;

    private Map<String, Image> enemyImages = Map.of(
        "Gargamel", new ImageIcon(
            getClass().getResource(ViewConstants.GARGAMEL_IMAGE))
            .getImage()
            .getScaledInstance(
                ViewConstants.TOWER_SIZE,
                ViewConstants.TOWER_SIZE,
                Image.SCALE_SMOOTH)
    );

    private Map<String, Image> towerImages = Map.of(
        "LightningSmurf", new ImageIcon(
            getClass().getResource(ViewConstants.LIGHTNINGSMURF_IMAGE))
            .getImage()
            .getScaledInstance(
                ViewConstants.TOWER_SIZE,
                ViewConstants.TOWER_SIZE,
                Image.SCALE_SMOOTH)
    );

    private Map<String, Image> projectileImages = Map.of(
        "LightningBolt", new ImageIcon(
            getClass().getResource(ViewConstants.LIGHTNINGBOLT_IMAGE))
            .getImage()
            .getScaledInstance(
                ViewConstants.TOWER_SIZE,
                ViewConstants.TOWER_SIZE,
                Image.SCALE_SMOOTH)
    );

    final Image backgroundImage = new ImageIcon(
        getClass().getResource(ViewConstants.BASE_MAP_IMAGE_PATH)
    ).getImage();


    public BoardView(Board board, Game game, ShopController shopController) {
        
        this.board = board;
        this.game = game;
        setLayout(new BorderLayout());
        setPreferredSize(board.getDimension());

        RightPanel rightPanel = new RightPanel(shopController);
        add(rightPanel, BorderLayout.EAST);

        // Enable drop target, TODO: show image of tower being dragged
        setDropTarget(new DropTarget() {
            @Override
            public synchronized void drop(DropTargetDropEvent dtde) {
                try {
                    dtde.acceptDrop(dtde.getDropAction());
                    Transferable transferable = dtde.getTransferable();
                    String towerType = (String) transferable.getTransferData(DataFlavor.stringFlavor);

                    // Create and place the tower on the board
                    Point dropPoint = dtde.getLocation();
                    String checkPurchase = shopController.purchaseTower(towerType, dropPoint);

                    switch(checkPurchase) {

                        case "PlacedOnAnotherTower":
                            showTemporaryMessage("Cannot place on another tower!");
                            break;
                        case "NotEnoughMoney"  :
                            showTemporaryMessage("Not enough money!");
                    }

                    repaint(); // Repaint to show the new tower
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void showTemporaryMessage(String message) {
        temporaryMessage = message;
        showTemporaryMessage = true;
        repaint(); // Trigger repaint to show the message

        if (errorTimer == null || !errorTimer.isRunning()) {
            // Hide the message after 2 seconds
            errorTimer = new Timer(2000, e -> {
                showTemporaryMessage = false;
                repaint(); // Trigger repaint to remove the message
            });
            errorTimer.setRepeats(false);
            errorTimer.start();
        }


    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        
        // Enable anti-aliasing for smoother graphics
        if (true) {
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        }

        renderBackground(g);
        renderTowers(g);
        renderEnemies(g);
        renderProjectiles(g);

        // Draw temporary message if visible
        if (showTemporaryMessage) {
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 25));
            int messageWidth = g.getFontMetrics().stringWidth(temporaryMessage);
            g.drawString(
                    temporaryMessage,
                    ViewConstants.GAME_WIDTH/4 + 35,
                    ViewConstants.GAME_HEIGHT/2 -100
            );
        }
    }

    private void renderProjectiles(Graphics g) {
        Iterator<Projectile> iterProjectile = board.getProjectileManager().getProjectiles().iterator();
        while (iterProjectile.hasNext()) {
            Projectile p = iterProjectile.next();

            g.drawImage(
                    projectileImages.get(p.getClass().getSimpleName()),
                    (int) p.getCurrentPosition().getX() - ViewConstants.TOWER_SIZE / 2,
                    (int) p.getCurrentPosition().getY() - ViewConstants.TOWER_SIZE / 2,
                    ViewConstants.TOWER_SIZE,
                    ViewConstants.TOWER_SIZE,
                    this
            );
        }
    }

    private void renderEnemies(Graphics g) {
        Iterator<Enemy> iterEnemy = game.getEnemies();
        while (iterEnemy.hasNext()) {
            Enemy e = iterEnemy.next();
            g.drawImage(
                    enemyImages.get(e.getClass().getSimpleName()),
                    (int) e.getPosition().getX() - ViewConstants.TOWER_SIZE / 2,
                    (int) e.getPosition().getY() - ViewConstants.TOWER_SIZE / 2,
                    ViewConstants.TOWER_SIZE,
                    ViewConstants.TOWER_SIZE,
                    this
            );

            g.setColor(ViewConstants.HEALTH_BAR_COLOR);
            g.setFont(g.getFont().deriveFont(20f));
            g.drawString(
                    Integer.toString(e.getHealth()),
                    (int) e.getPosition().getX() - ViewConstants.TOWER_SIZE / 2,
                    (int) e.getPosition().getY() + ViewConstants.TOWER_SIZE / 2
            );
        }
    }

    private void renderTowers(Graphics g) {
        Iterator<Tower> iter = board.getTowers();
        while (iter.hasNext()) {
            Tower t = iter.next();
            g.drawImage(
                    towerImages.get(t.getTowerType()),
                    (int) t.getPosition().getX() - ViewConstants.TOWER_SIZE / 2,
                    (int) t.getPosition().getY() - ViewConstants.TOWER_SIZE / 2,
                    ViewConstants.TOWER_SIZE,
                    ViewConstants.TOWER_SIZE,
                    this
            );
        }
    }

    private void renderBackground(Graphics g) {
        g.drawImage(
                backgroundImage,
                0,
                0,
                (int) board.getDimension().getWidth(),
                (int) board.getDimension().getHeight(),
                this
        );
    }
}