package com.group34.View.Game;
import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Game extends JFrame {
    public Game(){
        setTitle("Smurf Tower Defence");
        setSize(1000, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // CardLayout to handle different views, welcomescreen, game, (shop/upgrades))
        CardLayout cardLayout = new CardLayout();
        JPanel container = new JPanel(cardLayout);

        // Initializing MVC components
        //GameModel model = new GameModel();
        //GameController controller = new GameController(model);
        //GameView gameView = new GameView(controller);
        //WelcomeScreen welcomeScreen = new WelcomeScreen(controller, cardLayout, container);

        //container.add(welcomeScreen, "WELCOME_SCREEN");
        //container.add(gameView,"GAME_VIEW");
        //cardLayout.show(container, getName());
        //setContentPane(container); // Set container as content
        //setVisible(true);
    }
}