package com.group34.View;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import com.group34.Model.Board.Board;
import com.group34.Model.Enemy.Enemy;
import com.group34.Model.Game.Game;
import com.group34.Model.Projectile.Projectile;
import com.group34.Model.Tower.Tower;
import com.group34.View.Shop.ShopController;

public class BoardView extends JPanel {
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
                    Tower tower = shopController.purchaseTower(towerType, dropPoint);

                    board.addTower(tower);
                    rightPanel.updateStatusPanel(); // update the labels for health and cash

                    repaint(); // Repaint to show the new tower
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        g.drawImage(
            backgroundImage, 
            0, 
            0, 
            (int) board.getDimension().getWidth(),
            (int) board.getDimension().getHeight(), 
            this
        );




        Iterator<Tower> iter = board.getTowers();
        Tower t;
    
        for (;iter.hasNext();) {
            t = iter.next();
            g.drawImage(
                smurfImage, 
                (int) t.getPosition().getX(), 
                (int) t.getPosition().getY(), 
                ViewConstants.TOWER_SIZE,
                ViewConstants.TOWER_SIZE,
                this);
        }

        Iterator<Enemy> iterEnemy = game.getEnemies();

        for (;iterEnemy.hasNext();) {
            Enemy e = iterEnemy.next();
            g.drawImage(
                gargamelImage, 
                (int) e.getPosition().getX(), 
                (int) e.getPosition().getY(), 
                ViewConstants.TOWER_SIZE,
                ViewConstants.TOWER_SIZE,
                this);
        }

        Iterator<Projectile> iterProjectile = board.getProjectileManager().getProjectiles().iterator();
        Projectile p;


        for (;iterProjectile.hasNext();) {
            p = iterProjectile.next();
            Image projectileImage = new ImageIcon(
                    getClass().getResource(ViewConstants.getProjectileImage(p.getProjectileType()))).getImage();


            g.drawImage(
                    projectileImage,
                    (int) p.getCurrentPosition().getX(),
                    (int) p.getCurrentPosition().getY(),
                    ViewConstants.TOWER_SIZE,
                    ViewConstants.TOWER_SIZE,
                    this);
        }

    }

}
