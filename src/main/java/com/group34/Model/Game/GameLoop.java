package com.group34.Model.Game;

import javax.swing.JPanel;



public class GameLoop implements Runnable{
    private Thread gameThread;
    private  JPanel gamePanel;

    private double FPS_SET = 120.0;
    private double UPS_SET = 60.0;

    private boolean running = false;


    public GameLoop(JPanel gamePanel) {
        this.gamePanel = gamePanel;
    }


    public void start(){
        if (running) return; // Prevent multiple threads
        running = true;
        gameThread = new Thread(this, "Game-loop thread");
        gameThread.start();
    }

    public void stop() {
        running = false;
        if (gameThread != null) {
            try {
                gameThread.join(); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            gameThread = null;
        }
    }


    private void updateGame(){
        // Game update logic
        // System.out.println("Game updated!");
    }

    @Override
    public void run(){

        double timePerFrame = 1000000000.0 / FPS_SET;
        double timePerUpdate = 1000000000.0 / UPS_SET;

        long lastFrame = System.nanoTime();
        long lastUpdate = System.nanoTime();
        long lastTimeCheck = System.currentTimeMillis();

        int frames = 0;
        int updates = 0;

        long now;


        while (running){

            now = System.nanoTime();
            //Render
            if (now - lastFrame >= timePerFrame) {
                gamePanel.repaint();
                lastFrame = now;
                frames ++;
            }

            //Update
            if (now - lastUpdate >= timePerUpdate){
                updateGame();
                lastUpdate = now;
                updates ++;
            }

            // UPS and FPS logging
            if (System.currentTimeMillis() - lastTimeCheck >= 1000) {
                System.out.println("FPS: " + frames + " | UPS: " + updates);
                frames = 0;
                updates = 0;
                lastTimeCheck = System.currentTimeMillis();
            }

        }
    }

}
