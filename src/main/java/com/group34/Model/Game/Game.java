package com.group34.Model.Game;
import com.group34.Model.Shop.ShopController;
import com.group34.Model.Shop.ShopModel;
import com.group34.View.GameView;
import com.group34.View.WelcomeScreen;

import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Game extends JFrame {
    /**
     * Initiates the game.
    **/
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Game());
    }
}