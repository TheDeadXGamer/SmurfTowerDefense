package com.group34;

import com.group34.Model.Game.DependencyManager;
import com.group34.View.MainView;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DependencyManager dependencyManager = new DependencyManager(new JPanel());

            // Start mainview
            MainView mainView = new MainView(
                dependencyManager.getGameController(),
                dependencyManager.getShopController()
            );  

            //Start
            dependencyManager.getGameController().startGame();

            mainView.setVisible(true);
        });
    }
}
