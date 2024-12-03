package com.group34.View.Game;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import com.group34.Controller.Game.GameController;



public class GameView extends JPanel {
    private Image backgroundImage;
    private Image shopImage;
    private Image gargamelImage;
    private Image lightningTowerImage;
    private GameController controller;

    public GameView(GameController controller){
        this.controller = controller;

        backgroundImage = loadImage("/assets/Maps/BaseMap.png");
        shopImage = loadImage("/assets/Shop/Shop.png");
        
         
        
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

        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);

        } 
        
        if (shopImage != null){
            int shopWidth = getWidth() / 3; 
            int shopHeight = getHeight();  
            int shopX = getWidth() - shopWidth; 
            int shopY = 0; 
            g.drawImage(shopImage, shopX, shopY, shopWidth, shopHeight, this);
        }
    }
}
