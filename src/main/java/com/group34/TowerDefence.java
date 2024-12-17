package com.group34;

import java.util.List;
import java.util.Optional;
import javax.swing.JFrame;
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
import com.group34.View.RightPanel;
import com.group34.View.ShopPanel;


public class TowerDefence extends JFrame implements Runnable {
    static final int FPS = 60;
    private CashVault cashVault;
    private Game game;
    private Board board;
    private Player player;
    private RoadSpawn roadSpawn;
    private List<Round> rounds;
    private GameSpeed gameSpeed;


    public TowerDefence(TowerDefenceBuilder builder) {

        this.cashVault = builder.cashVault;
        this.game = builder.game;
        this.board = builder.board;
        this.player = builder.player;
        this.roadSpawn = builder.roadSpawn;
        this.rounds = builder.rounds;
        this.gameSpeed = builder.gameSpeed;
        
        setTitle("Tower Defence");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);

        Shop shop = new Shop(cashVault);
        ShopPanel shopPanel = new ShopPanel(shop);
        RightPanel rightPanel = new RightPanel(shopPanel);

        BoardView boardView = new BoardView(
            this.board, 
            this.game, 
            rightPanel
        );
       
        add(boardView);

        pack();
        setVisible(true);
    }


    @Override
    public void run() {
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
                    repaint();
                    try {
                        Thread.sleep(1000 / gameSpeed.getSpeed());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        System.out.println("Game Over");
    }

}
