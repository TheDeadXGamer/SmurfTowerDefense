package com.group34;

import java.awt.CardLayout;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.swing.JFrame;
import javax.swing.JPanel;

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
import com.group34.View.BoardView;
import com.group34.View.ButtonPanel;
import com.group34.View.RightPanel;
import com.group34.View.ShopPanel;
import com.group34.View.StatusPanel;
import com.group34.View.WelcomePanel;


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
    
    private GameState currentState;
    private CardLayout cardLayout;
    private JPanel cardPanel;


    public TowerDefence(TowerDefenceBuilder builder) {

        this.cashVault = builder.cashVault;
        this.game = builder.game;
        this.board = builder.board;
        this.player = builder.player;
        this.roadSpawn = builder.roadSpawn;
        this.rounds = builder.rounds;
        this.gameSpeed = builder.gameSpeed;
        this.shop = builder.shop;
        
        setTitle("Smurf Tower Defence");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
        setLocationRelativeTo(null);

        ShopPanel shopPanel = new ShopPanel(shop);
        StatusPanel statusPanel = new StatusPanel(cashVault, player);
        RightPanel rightPanel = new RightPanel(shopPanel, statusPanel);
        WelcomePanel welcomePanel = new WelcomePanel();

        BoardView boardView = new BoardView(
            this.board, 
            this.game, 
            rightPanel
        );

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        cardPanel.add(welcomePanel, "Welcome");
        cardPanel.add(boardView, "Game");

        add(cardPanel);

        currentState = GameState.WELCOME;

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
        System.out.println("Game Over");
    }

    private void handleActive(){
        cardLayout.show(cardPanel, "Game");
        Iterator<Round> roundIterator = rounds.iterator();
        while(roundIterator.hasNext()) {
            Round round = roundIterator.next();
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
                if(roundIterator.hasNext()) {
                    currentState = GameState.BETWEEN_ROUND;
                    handleBetweenRound();
                }
                currentState = GameState.GAME_OVER;
        }
    }

    private void handleBetweenRound() {
        cardLayout.show(cardPanel, "Game");
        while(currentState == GameState.BETWEEN_ROUND) {
            try {
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
            if(currentState == GameState.BETWEEN_ROUND) {
                setState(GameState.ACTIVE_ROUND);
            }

            if(currentState == GameState.ACTIVE_ROUND) {
                
                if(gameSpeed == GameSpeed.FAST) {
                    gameSpeed = GameSpeed.NORMAL;
                }
                else if(gameSpeed == GameSpeed.NORMAL) {
                    gameSpeed = GameSpeed.FAST;
                }
            }
        });
    }

}
