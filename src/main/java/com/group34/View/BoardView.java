package com.group34.View;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import com.group34.Model.Board.Board;

public class BoardView extends JPanel {
    private Board board;
    private Image backgroundImage;
    private int width;
    private int height;

    public void Board() {
        
        backgroundImage = loadImage("/assets/Maps/BaseMap.png");
    }

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

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        g.drawImage(
            backgroundImage, 
            0, 
            0, 
            getWidth(),
            getHeight(), 
        this);

    }

}
