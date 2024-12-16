package com.group34.View;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.util.Iterator;

import javax.swing.*;

import com.group34.Model.Board.Board;
import com.group34.Model.Enemy.Enemy;
import com.group34.Model.Game.Game;
import com.group34.Model.Projectile.Projectile;
import com.group34.Model.Tower.Tower;
import com.group34.View.Shop.ShopController;

public class BoardView extends JPanel {
    private String temporaryMessage = null;
    private boolean showTemporaryMessage = false;
    private Timer errorTimer;
    public Board board;
    public Game game;

    final Image backgroundImage = new ImageIcon(
        getClass().getResource(ViewConstants.BASE_MAP_IMAGE_PATH)
    ).getImage();

    final Image smurfImage = new ImageIcon(
        getClass().getResource(ViewConstants.LIGHTNINGSMURF_IMAGE)
    )
        .getImage()
        .getScaledInstance(
            ViewConstants.TOWER_SIZE,
            ViewConstants.TOWER_SIZE,
            Image.SCALE_SMOOTH
    );

    final Image gargamelImage = new ImageIcon(
        getClass().getResource(ViewConstants.GARGAMEL_IMAGE)
    )
        .getImage()
        .getScaledInstance(
            ViewConstants.TOWER_SIZE,
            ViewConstants.TOWER_SIZE,
            Image.SCALE_SMOOTH
    );

    public BoardView(Board board, Game game, ShopController shopController) {
        this.board = board;
        this.game = game;
        setPreferredSize(board.getDimension());
        setLayout(new BorderLayout());

        RightPanel rightPanel = new RightPanel(shopController);
        add(rightPanel, BorderLayout.EAST);

        // Enable drop target
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

                    rightPanel.updateStatusPanel(); // update the labels for health and cash
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

        g.drawImage(
                backgroundImage,
                0,
                0,
                (int) board.getDimension().getWidth(),
                (int) board.getDimension().getHeight(),
                this
        );

        // Draw towers
        Iterator<Tower> iter = board.getTowers();
        while (iter.hasNext()) {
            Tower t = iter.next();
            g.drawImage(
                    smurfImage,
                    (int) t.getPosition().getX() - ViewConstants.TOWER_SIZE / 2,
                    (int) t.getPosition().getY() - ViewConstants.TOWER_SIZE / 2,
                    ViewConstants.TOWER_SIZE,
                    ViewConstants.TOWER_SIZE,
                    this
            );
        }

        // Draw enemies
        Iterator<Enemy> iterEnemy = game.getEnemies();
        while (iterEnemy.hasNext()) {
            Enemy e = iterEnemy.next();
            g.drawImage(
                    gargamelImage,
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

        // Draw projectiles
        Iterator<Projectile> iterProjectile = board.getProjectileManager().getProjectiles().iterator();
        while (iterProjectile.hasNext()) {
            Projectile p = iterProjectile.next();
            Image projectileImage = new ImageIcon(
                    getClass().getResource(ViewConstants.getProjectileImage(p.getProjectileType()))
            ).getImage();
            g.drawImage(
                    projectileImage,
                    (int) p.getCurrentPosition().getX() - ViewConstants.TOWER_SIZE / 2,
                    (int) p.getCurrentPosition().getY() - ViewConstants.TOWER_SIZE / 2,
                    ViewConstants.TOWER_SIZE,
                    ViewConstants.TOWER_SIZE,
                    this
            );
        }

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
}