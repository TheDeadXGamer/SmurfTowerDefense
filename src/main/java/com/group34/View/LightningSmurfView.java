package com.group34.View;

import java.awt.Graphics;
import java.awt.Image;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import com.group34.Model.Board.Board;
import com.group34.Model.Tower.Tower;

public class LightningSmurfView extends JLabel {
    public Board board;
    final Image smurfImage = new ImageIcon(getClass().getResource(ViewConstants.LIGHTNINGSMURF_IMAGE))
        .getImage()
        .getScaledInstance(ViewConstants.TOWER_SIZE, ViewConstants.TOWER_SIZE, Image.SCALE_SMOOTH);


    public LightningSmurfView(Board board) {
        this.board = board;
    }

    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
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
