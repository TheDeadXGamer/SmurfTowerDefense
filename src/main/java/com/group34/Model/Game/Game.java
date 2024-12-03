package com.group34.Model.Game;
import com.group34.Model.Shop.ShopController;
import com.group34.Model.Shop.ShopModel;

import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Game extends JFrame implements Runnable {

    private Thread gameThread;

    private final double FPS_SET = 120.0;
    private final double UPS_SET = 60.0;
    


    public Game(){
        setTitle("Smurf Tower Defence");
        setSize(815, 635);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);


        // CardLayout to handle different views, welcomescreen, game, (shop/upgrades))
        CardLayout cardLayout = new CardLayout();
        JPanel container = new JPanel(cardLayout);

        // Initializing MVC components
        GameModel model = new GameModel();
        GameController controller = new GameController(model);

        ShopModel shopModel = new ShopModel(model.getPlayer());
        ShopController shopController = new ShopController(shopModel);

        GameView gameView = new GameView(controller, shopController);

        WelcomeScreen welcomeScreen = new WelcomeScreen(controller, cardLayout, container);

        container.add(welcomeScreen, "WELCOME_SCREEN");
        container.add(gameView,"GAME_VIEW");

        cardLayout.show(container, getName());
        setContentPane(container); // Set container as content
        setVisible(true);

        
    }

    private void start(){
        gameThread = new Thread(this){};
        gameThread.start();
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
        

        while (true){   

            now = System.nanoTime();
            //Render
            if (now - lastFrame >= timePerFrame) {
                repaint();
                lastFrame = now;
                frames ++;
            }

            //Update
            if (now - lastUpdate >= timePerUpdate){
                updateGame();
                lastUpdate = now;
                updates ++;
            }

            if (System.currentTimeMillis() - lastTimeCheck >= 1000) {
                System.out.println("FPS: " + frames + " | UPS: " + updates);
                frames = 0;
                updates = 0;
                lastTimeCheck = System.currentTimeMillis();
            }

        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Game game = new Game();
            game.start();
        });
        
    }
}