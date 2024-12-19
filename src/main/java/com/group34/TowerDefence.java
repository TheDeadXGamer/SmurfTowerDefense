package com.group34;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.swing.JFrame;

import com.group34.Controller.TowerPurchase;
import com.group34.Controller.TowerSell;
import com.group34.Controller.TowerUpgrade;
import com.group34.Model.Board.Board;
import com.group34.Model.Enemy.Enemy;
import com.group34.Model.Enemy.EnemyFactory;
import com.group34.Model.Game.Game;
import com.group34.Model.Game.Player;
import com.group34.Model.Road.RoadSpawn;
import com.group34.Model.Road.RoadToken;
import com.group34.Model.Round.Round;
import com.group34.Model.Shop.CashVault;
import com.group34.Model.Shop.Shop;
import com.group34.Model.Shop.ShopItem;
import com.group34.View.*;


public class TowerDefence extends JFrame implements Runnable {
    static final int FPS = 60;
    private CashVault cashVault;
    private Game game;
    private Board board;
    private Player player;
    private RoadSpawn roadSpawn;
    private List<Round> rounds;
    private GameSpeed gameSpeed;
    private Shop shop;
    private boolean isPaused = false;
    private GameView gameView = new GameView();

    public TowerDefence(TowerDefenceBuilder builder) {
        this.cashVault = builder.cashVault;
        this.game = builder.game;
        this.board = builder.board;
        this.player = builder.player;
        this.roadSpawn = builder.roadSpawn;
        this.rounds = builder.rounds;
        this.gameSpeed = builder.gameSpeed;
        this.shop = builder.shop;

        setTitle("Tower Defence");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
        setLocationRelativeTo(null);

        // TODO: explain

        List<ShopButtonComponent> buttons = new ArrayList<>();
        Iterator<ShopItem> shopItems = shop.getItems();
        while (shopItems.hasNext()) {
            buttons.add(new ShopButtonComponent(shopItems.next()));
        }

        TowerUpgrade towerUpgrade = new TowerUpgrade();
        towerUpgrade.setCashVault(cashVault);
        TowerSell towerSell = new TowerSell(shop);

        UpgradePanel upgradeScreen = new UpgradePanel(towerUpgrade,towerSell);
        ShopPanel shopPanel = new ShopPanel(buttons);
        StatusPanel statusPanel = new StatusPanel(cashVault, player);
        RightPanel rightPanel = new RightPanel(shopPanel, statusPanel,upgradeScreen);

        BoardView boardView = new BoardView(
            this.board,
            this.game,
            rightPanel
        );
        towerSell.subscribe(boardView);
        towerUpgrade.setBoardView(boardView);

        TowerPurchase purchaseController = new TowerPurchase(
            buttons,
            boardView,
            shop
        );

        //GameView gameView = new GameView();
        //gameView.add(boardView);
        //gameView.pack();
        //gameView.setVisible(true);

        add(boardView);
        pack();
        setVisible(true);


    }

    @Override
    public void run() {
        renderWelcomeScreen();

        for (Round round : rounds) {
            while (!round.isRoundOver() || game.enemiesLeft() > 0) {
                if (player.isAlive()) {
                    Optional<EnemyFactory> spawn = round.spawn();

                    if (spawn.isPresent()) {
                        RoadToken token = new RoadToken(roadSpawn);;
                        game.addEnemy(spawn.get().createEnemy(token));
                    }

                    List<Enemy> killed = game.update();
                    for (Enemy enemy : killed) {
                        cashVault.deposit(enemy.getReward());
                    }
                    
                    board.update();
                    //gameView.repaint();
                    repaint();

                    try {
                        Thread.sleep(1000 / gameSpeed.getSpeed());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (isPaused) { renderPausedScreen(); }
            }
        }
        renderGameOverScreen();
        System.out.println("Game Over");
    }

    private void renderWelcomeScreen() {
        while (true) {
            break;
        }
    }

    private void renderPausedScreen() {
        while (true) {
            break;
        }
    }

    private void renderGameOverScreen() {
        while (true) {
            break;
        }
    }

}
