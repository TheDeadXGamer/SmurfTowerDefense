package com.group34;

import java.awt.Dimension;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import com.group34.Model.Board.Board;
import com.group34.Model.Board.PlacementError;
import com.group34.Model.Cash.CashVault;
import com.group34.Model.Game.Game;
import com.group34.Model.Game.Player;
import com.group34.Model.Round.Round;
import com.group34.Model.Round.RoundBuilder;
import com.group34.Model.Tower.LightningSmurfFactory;
import com.group34.Model.Tower.Tower;
import com.group34.View.BoardView;




class TowerDefenceBuilder {
    Board board;
    List<Round> rounds;
    CashVault cashVault;
    Game game;
    Player player;

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

    public TowerDefence(TowerDefenceBuilder builder) {

        this.board = builder.board;
        this.cashVault = builder.cashVault;
        this.game = builder.game;
        this.rounds = builder.rounds;
        this.player = builder.player;


    
        setTitle("Tower Defence");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
        setLocationRelativeTo(null);
        
        BoardView boardView = new BoardView(this.board);
        add(boardView);
        
        pack();
        setVisible(true);

    }


    @Override
    public void run() {
        for (Round round : rounds) {
            while (round.eventsLeft() > 0 && game.enemiesLeft() > 0) {
                if (player.isAlive()) {
                    // game.update();
                    repaint();

                    try {
                        Thread.sleep(10000 / FPS);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Round Over");
                }
            }
        }
        //game.update();
        repaint();
        System.out.println("Game Over");
    }

}

public class Main {
    public static void main (String[] args){
    
        List<Round> rounds = new ArrayList<>();
    
        
        RoundBuilder roundBuilder = new RoundBuilder();
        Round round = roundBuilder.build();


        Point2D position = new Point2D.Double(100, 100);
        Tower tower = new LightningSmurfFactory(position).createTower();
        Board board = new Board(new Dimension(815, 635));
        try {
            board.addTower(tower);
        } catch (PlacementError e) {
            System.out.println(e.getMessage());
        }

        TowerDefence towerDefence = new TowerDefenceBuilder()
            .setBoard(board)
            .setRounds(new ArrayList<>())
            .setPlayer(new Player(30))
            .setCashVault(new CashVault(300))
            .setGame(new Game())
            .build();

        Thread thread = new Thread(towerDefence);
        thread.start();

    }
}
