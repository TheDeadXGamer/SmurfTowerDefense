package com.group34;

import java.awt.Dimension;
import java.util.List;

import javax.swing.JFrame;

import com.group34.Model.Board.Board;
import com.group34.Model.Cash.CashVault;
import com.group34.Model.Game.Game;
import com.group34.Model.Game.Player;
import com.group34.Model.Round.Round;
import com.group34.View.BoardView;
import com.group34.View.LightningSmurfView;



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


        init();

    }

    void init() {
        setTitle("Tower Defence");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        
        // Board board = new Board(new Dimension(815, 635));
        BoardView boardView = new BoardView(this.board);
        boardView.add(new LightningSmurfView(this.board));
        add(boardView);
        
        pack();
        setVisible(true);
    }

    @Override
    public void run() {
        while (player.isAlive()) {
            game.update();
            repaint();
        }
    }

    


}

public class Main {
    public static void main (String[] args){
    
        TowerDefence towerDefence = new TowerDefenceBuilder()
            .setBoard(new Board(new Dimension(815, 635)))
            .setRounds(null)
            .setPlayer(new Player(30))
            .setCashVault(new CashVault(300))
            .setGame(new Game())
            .build();

        Thread thread = new Thread(towerDefence);
        thread.start();

    }
}
