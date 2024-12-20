package com.group34;

import java.awt.CardLayout;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.swing.JFrame;
import javax.swing.JPanel;

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
import com.group34.Model.Round.RoundConfig;
import com.group34.Model.Shop.CashVault;
import com.group34.Model.Shop.Shop;
import com.group34.Model.Shop.ShopItem;
import com.group34.View.*;

public class TowerDefence extends JFrame implements Runnable {
    private CashVault cashVault;
    private Game game;
    private Board board;
    private Player player;
    private RoadSpawn roadSpawn;
    private List<Round> rounds;
    private GameSpeed gameSpeed;
    private Shop shop;
    private GameState currentState;
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private GameView gameView;

    public TowerDefence(TowerDefenceBuilder builder) {
        this.cashVault = builder.cashVault;
        this.game = builder.game;
        this.board = builder.board;
        this.player = builder.player;
        this.roadSpawn = builder.roadSpawn;
        this.gameSpeed = builder.gameSpeed;
        this.shop = builder.shop;
        
        setTitle("Smurf Tower Defence");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
        setLocationRelativeTo(null);

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
        WelcomePanel welcomePanel = new WelcomePanel();
        RightPanel rightPanel = new RightPanel(shopPanel, statusPanel,upgradeScreen);

        BoardView boardView = new BoardView(
            this.board,
            this.game,
            rightPanel
        );


        towerSell.subscribe(boardView);

        gameView = new GameView(boardView,rightPanel);
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        cardPanel.add(welcomePanel, "Welcome");
        cardPanel.add(gameView, "Game");

        add(cardPanel);

        currentState = GameState.WELCOME;

        towerUpgrade.setBoardView(boardView);

        TowerPurchase purchaseController = new TowerPurchase(
            buttons,
            boardView,
            shop
        );

        pack();
        setVisible(true);

        addButtonListeners(welcomePanel, rightPanel);
    }

    @Override
    public void run() {
        while (true) {
            switch (currentState) {
                case WELCOME:
                    handleWelcome();
                    break;
            
                case BETWEEN_ROUND:
                    handleBetweenRound();
                    break;

                case ACTIVE_ROUND:
                    handleActive();
                    break;

                case PAUSED:
                    handlePaused();
                    break;

                case GAME_OVER:
                    handleGameOver();
                    break;
            }
        }
    }

    private void handleWelcome() {
        cardLayout.show(cardPanel, "Welcome");
    }

    private void handlePaused() {
        while (true) {
            break;
        }
    }

    private void handleGameOver() {
        while (true) {
            break;
        }
    }

    private void handleActive(){
        cardLayout.show(cardPanel, "Game");
            Round round = RoundConfig.createRound();
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
                } else {
                    currentState = GameState.GAME_OVER;
                    break;
                }
            }

            currentState = GameState.BETWEEN_ROUND;
            handleBetweenRound();
    }

    private void handleBetweenRound() {
        cardLayout.show(cardPanel, "Game");
        while(currentState == GameState.BETWEEN_ROUND) {
            try {
                board.update();

                Thread.sleep(1000 / gameSpeed.getSpeed());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void setState(GameState state) {
        currentState = state;
    }

    private void addButtonListeners(WelcomePanel welcomePanel, RightPanel rightPanel) {
        welcomePanel.getPlayButton().addActionListener(e -> {
            setState(GameState.BETWEEN_ROUND);
        });

        ButtonPanel buttonPanel = rightPanel.getButtonPanel();
        buttonPanel.getFastForwardButton().addActionListener(e -> {
            if(currentState == GameState.ACTIVE_ROUND) {
                
                if(gameSpeed == GameSpeed.FAST) {
                    gameSpeed = GameSpeed.NORMAL;
                }
                else if(gameSpeed == GameSpeed.NORMAL) {
                    gameSpeed = GameSpeed.FAST;
                }
            }

            if(currentState == GameState.BETWEEN_ROUND) {
                setState(GameState.ACTIVE_ROUND);
            }
        });
    }

}
