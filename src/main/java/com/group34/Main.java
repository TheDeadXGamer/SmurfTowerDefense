package com.group34;

import java.awt.Dimension;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.swing.JFrame;

import com.group34.Model.Board.Board;
import com.group34.Model.Cash.CashVault;
import com.group34.Model.Enemy.EnemyFactory;
import com.group34.Model.Enemy.GargamelFactory;
import com.group34.Model.Game.Game;
import com.group34.Model.Game.Player;
import com.group34.Model.Road.RoadBuilder;
import com.group34.Model.Road.RoadSpawn;
import com.group34.Model.Round.Round;
import com.group34.Model.Round.RoundBuilder;
import com.group34.Model.Round.RoundEvent;
import com.group34.Model.Tower.LightningSmurfFactory;
import com.group34.Model.Tower.Tower;
import com.group34.View.BoardView;




class TowerDefenceBuilder {
    Board board;
    List<Round> rounds;
    CashVault cashVault;
    Game game;
    Player player;
    RoadSpawn roadSpawn;

    public TowerDefenceBuilder setBoard(Board board) {
        this.board = board;
        return this;
    }

    public TowerDefenceBuilder setPlayer(Player player) {
        this.player = player;
        return this;
    }

    public TowerDefenceBuilder setRounds(List<Round> rounds) {
        this.rounds = rounds;
        return this;
    }

    public TowerDefenceBuilder setCashVault(CashVault cashVault) {
        this.cashVault = cashVault;
        return this;
    }

    public TowerDefenceBuilder setGame(Game game) {
        this.game = game;
        return this;
    }

    public TowerDefenceBuilder setRoadSpawn(RoadSpawn roadSpawn) {
        this.roadSpawn = roadSpawn;
        return this;
    }

    public TowerDefence build() {
        return new TowerDefence(this);
    }
}

class TowerDefence extends JFrame implements Runnable {
    static final int FPS = 60;
    private CashVault cashVault;
    private Game game;
    private Board board;
    private List<Round> rounds;
    private Player player;
    private RoadSpawn roadSpawn;

    public TowerDefence(TowerDefenceBuilder builder) {

        this.board = builder.board;
        this.cashVault = builder.cashVault;
        this.game = builder.game;
        this.rounds = builder.rounds;
        this.player = builder.player;
        this.roadSpawn = builder.roadSpawn;
    
        setTitle("Tower Defence");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
        setLocationRelativeTo(null);
        
        BoardView boardView = new BoardView(this.board, this.game);
        add(boardView);
        
        pack();
        setVisible(true);

    }


    @Override
    public void run() {
        for (Round round : rounds) {
            while (round.eventsLeft() > 0 || game.enemiesLeft() > 0) {
                if (player.isAlive()) {
                    Optional<EnemyFactory> spawn = round.spawn();
                    if (spawn.isPresent()) {
                        roadSpawn.spawn(spawn.get());
                    }

                    game.update();
                    board.update();
                    repaint();
                    try {
                        Thread.sleep(1000 / FPS);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        System.out.println("Game Over");
    }

}

public class Main {
    public static void main (String[] args) throws Exception {
    
        List<Round> rounds = new ArrayList<>();
    
        Point2D position = new Point2D.Double(100, 100);
        Tower tower = new LightningSmurfFactory(position).createTower();
        Board board = new Board(new Dimension(815, 635));
        Player player = new Player(30);
        CashVault cashVault = new CashVault(100);
        Game game = new Game();

        Round round = new RoundBuilder()
            .addEvent(new RoundEvent(
                new GargamelFactory(game, cashVault),
                 0)
            ).build();

        rounds.add(round);


        RoadSpawn spawn = new RoadBuilder(board, player)
            .add(new Point2D.Double(600., 0.))
            .add(new Point2D.Double(300., 100.))
            .add(new Point2D.Double(100., 200.))
            .add(new Point2D.Double(400., 300.))
            .add(new Point2D.Double(100., 400.))
            .add(new Point2D.Double(200., 635.))
            .build();

        board.addTower(tower);
      

        TowerDefence towerDefence = new TowerDefenceBuilder()
            .setBoard(board)
            .setRounds(rounds)
            .setPlayer(player)
            .setCashVault(cashVault)
            .setGame(game)
            .setRoadSpawn(spawn)
            .build();

        Thread thread = new Thread(towerDefence);
        thread.start();

    }
}
