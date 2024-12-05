package com.group34.View;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import com.group34.Model.Board.Board;

public class BoardView extends JPanel {
    public Board board;
    final Image backgroundImage = new ImageIcon(
        getClass().getResource(ViewConstants.BASE_MAP_IMAGE_PATH))
        .getImage();

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

    }

}
