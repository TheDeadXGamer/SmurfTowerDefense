package com.group34.View;

import java.awt.Graphics;
import java.awt.Image;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import com.group34.Model.Board.Board;
import com.group34.Model.Tower.Tower;

public class BoardView extends JPanel {
    public Board board;
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

    public BoardView(Board board) {
        this.board = board;
        setPreferredSize(board.getDimension());
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

    }

}
