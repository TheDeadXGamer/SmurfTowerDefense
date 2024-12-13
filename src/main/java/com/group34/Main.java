package com.group34;

import java.awt.Dimension;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import com.group34.Model.Board.Board;
import com.group34.Model.Cash.CashVault;
import com.group34.Model.Enemy.GargamelFactory;
import com.group34.Model.Game.Game;
import com.group34.Model.Game.GameBuilder;
import com.group34.Model.Game.Player;
import com.group34.Model.Game.Repaintable;
import com.group34.Model.Road.RoadBuilder;
import com.group34.Model.Road.RoadSpawn;
import com.group34.Model.Round.Round;
import com.group34.Model.Round.RoundBuilder;
import com.group34.Model.Round.RoundEvent;
import com.group34.Model.Tower.LightningSmurfFactory;
import com.group34.Model.Tower.Tower;
import com.group34.View.BoardView;
import com.group34.View.Shop.ShopController;
import com.group34.View.Shop.ShopModel;


class TowerDefenceBuilder {
    Board board;
    CashVault cashVault;
    Game game;
    Player player;
    RoadSpawn roadSpawn;
    ShopModel shopModel;

    public TowerDefenceBuilder setBoard(Board board) {
        this.board = board;
        return this;
    }

    public TowerDefenceBuilder setPlayer(Player player) {
        this.player = player;
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

class TowerDefence extends JFrame implements Runnable, Repaintable {
    static final int FPS = 60;
    private CashVault cashVault;
    private Game game;
    private Board board;
    private Player player;

    public TowerDefence(TowerDefenceBuilder builder) {

        this.cashVault = builder.cashVault;
        this.game = builder.game;
        this.board = builder.board;
        this.player = builder.player;
    
        setTitle("Tower Defence");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
        setLocationRelativeTo(null);
        game.setRepaintable(this);

        // TODO: maybe not the best usage of shop stuff like this, change later
        ShopModel shopModel = new ShopModel(player, cashVault);
        ShopController shopController = new ShopController(shopModel);
        
        BoardView boardView = new BoardView(this.board, this.game, shopController);
        add(boardView);
        
        pack();
        setVisible(true);

    }


    @Override
    public void run() {
        game.update();
    }

    @Override
    public void repaint() {
        super.repaint();
        try {
            Thread.sleep(1000 / Repaintable.FPS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class Main {
    public static void main (String[] args) throws Exception {
    
        Point2D position = new Point2D.Double(100, 100);
        Tower tower = new LightningSmurfFactory().createTower(position);
        Tower tower2 = new LightningSmurfFactory().createTower(new Point2D.Double(400,300));
        Player player = new Player(30);
        CashVault cashVault = new CashVault(100);
        Board board = new Board(new Dimension(815, 635));

        Round round = new RoundBuilder()
            .addEvent(new RoundEvent(
                new GargamelFactory(cashVault), 0))
            .addEvent(new RoundEvent(
                new GargamelFactory(cashVault),1))
            .build();


        List<Round> rounds = new ArrayList<>();
        rounds.add(round);
        
        

        RoadSpawn spawn = new RoadBuilder(board, player)
            .add(new Point2D.Double(200., 0.))
            .add(new Point2D.Double(300., 100.))
            .add(new Point2D.Double(100., 200.))
            .add(new Point2D.Double(400., 300.))
            .add(new Point2D.Double(100., 400.))
            .add(new Point2D.Double(200., 635.))
            .build();

        Game game = new GameBuilder()
        .setBoard(board)
        .setPlayer(player)
        .setCashVault(cashVault)
        .setRoadSpawn(spawn)
        .setRounds(rounds)
        .build();

        board.addTower(tower);
        board.addTower(tower2);

        TowerDefence towerDefence = new TowerDefenceBuilder()
            .setCashVault(cashVault)
            .setGame(game)
            .setBoard(board)
            .setPlayer(player)
            .build();

        Thread thread = new Thread(towerDefence);
        thread.start();

    }
}
