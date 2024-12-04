package com.group34.View;

import com.group34.Model.Game.GameController;
import com.group34.Model.Shop.ShopController;

import javax.swing.*;
import java.awt.*;

public class MainView extends JFrame {

    private CardLayout cardLayout;
    private JPanel container;
    private JLayeredPane layeredPane;

    public MainView(GameController gameController, ShopController shopController) {
        // Set up JFrame
        setTitle("Smurf Tower Defence");
        setSize(815, 635);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Set up CardLayout
        cardLayout = new CardLayout();
        container = new JPanel(cardLayout);

        // Create views
        WelcomeScreen welcomeScreen = new WelcomeScreen(gameController, cardLayout, container);
        layeredPane = createGameLayeredPane(gameController, shopController);

        // Add views to CardLayout container
        container.add(welcomeScreen, "WELCOME_SCREEN");
        container.add(layeredPane, "GAME_VIEW");

        // Show initial view
        cardLayout.show(container, "WELCOME_SCREEN");

        // Set the container as the main content pane
        setContentPane(container);
        setVisible(true);
    }

    private JLayeredPane createGameLayeredPane(GameController gameController, ShopController shopController) {
        // Create GameView and ShopPanel
        GameView gameView = new GameView(gameController, shopController);
        ShopPanel shopPanel = new ShopPanel(shopController);

        // Set up JLayeredPane
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(ViewConstants.GAME_WIDTH, ViewConstants.GAME_HEIGHT));

        gameView.setBounds(0, 0, ViewConstants.GAME_WIDTH, ViewConstants.GAME_HEIGHT);
        shopPanel.setBounds(
            ViewConstants.GAME_WIDTH - ViewConstants.RIGHT_PANEL_WIDTH, 
            0, 
            ViewConstants.RIGHT_PANEL_WIDTH, 
            ViewConstants.GAME_HEIGHT
        );

        // Add panels to layeredPane
        layeredPane.add(gameView, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(shopPanel, JLayeredPane.PALETTE_LAYER);
        

        return layeredPane;
    }
}
