package com.group34.Model.Game;

import javax.swing.JPanel;
import com.group34.Model.Game.GameController;
import com.group34.Model.Game.GameModel;
import com.group34.Model.Shop.ShopController;
import com.group34.Model.Shop.ShopModel;


public class DependencyManager {
    private final GameModel gameModel;
    private final GameController gameController;
    private final GameLoop gameLoop;

    private final ShopModel shopModel;
    private final ShopController shopController;

    public DependencyManager(JPanel gamePanel) {
        this.gameModel = new GameModel();
        this.gameLoop = new GameLoop(gamePanel); 
        this.gameController = new GameController(gameModel, gameLoop);

        this.shopModel = new ShopModel(gameModel.getPlayer());
        this.shopController = new ShopController(shopModel);

    }

    public GameController getGameController() {
        return gameController;
    }

    public ShopController getShopController() {
        return shopController;
    }
}
