package com.group34;

import java.awt.Dimension;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import com.group34.Model.Board.Board;
import com.group34.Model.Cash.CashVault;
import com.group34.Model.Enemy.GargamelFactory;
import com.group34.Model.Game.Game;
import com.group34.Model.Game.Player;
import com.group34.Model.Game.GameState.GameState;
import com.group34.Model.Game.GameState.MidRoundState;
import com.group34.Model.Road.RoadBuilder;
import com.group34.Model.Road.RoadSpawn;
import com.group34.Model.Round.Round;
import com.group34.Model.Round.RoundBuilder;
import com.group34.Model.Round.RoundEvent;
import com.group34.Model.Round.RoundConfig;
import com.group34.Model.Tower.LightningSmurfFactory;
import com.group34.Model.Tower.Tower;
import com.group34.View.Shop.ShopModel;


class TowerDefenceBuilder {
    Board board;
    CashVault cashVault;
    Game game;
    Player player;
    RoadSpawn roadSpawn;
    ShopModel shopModel;
    List<Round> rounds;
    GameState startState;
    GameSpeed gameSpeed;

    public TowerDefenceBuilder setBoard(Board board) {
        this.board = board;
        return this;
    }

    public TowerDefenceBuilder setGameSpeed(GameSpeed gameSpeed) {
        this.gameSpeed = gameSpeed;
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

    public TowerDefenceBuilder setRounds (List<Round> rounds) {
        this.rounds = rounds;
        return this;
    }

    public TowerDefenceBuilder setStartState (GameState startState) {
        this.startState = startState;
        return this;
    }

    public TowerDefence build() {
        return new TowerDefence(this);
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
        Game game = new Game();       
        List<Round> rounds = RoundConfig.createRounds(cashVault);

        RoadSpawn spawn = new RoadBuilder(board, player)
                .add(new Point2D.Double(500., 3.))
                .add(new Point2D.Double(493., 63.))
                .add(new Point2D.Double(480., 116.))
                .add(new Point2D.Double(453., 155.))
                .add(new Point2D.Double(392., 150.))
                .add(new Point2D.Double(342., 128.))
                .add(new Point2D.Double(297., 109.))
                .add(new Point2D.Double(248., 98.))
                .add(new Point2D.Double(191., 92.))
                .add(new Point2D.Double(147., 98.))
                .add(new Point2D.Double(90., 112.))
                .add(new Point2D.Double(57., 134.))
                .add(new Point2D.Double(27., 171.))
                .add(new Point2D.Double(15., 225.))
                .add(new Point2D.Double(8., 281.))
                .add(new Point2D.Double(12., 340.))
                .add(new Point2D.Double(29., 397.))
                .add(new Point2D.Double(58., 448.))
                .add(new Point2D.Double(111., 481.))
                .add(new Point2D.Double(164., 500.))
                .add(new Point2D.Double(242., 502.))
                .add(new Point2D.Double(309., 500.))
                .add(new Point2D.Double(360., 489.))
                .add(new Point2D.Double(384., 445.))
                .add(new Point2D.Double(376., 406.))
                .add(new Point2D.Double(337., 389.))
                .add(new Point2D.Double(291., 399.))
                .add(new Point2D.Double(236., 402.))
                .add(new Point2D.Double(192., 397.))
                .add(new Point2D.Double(152., 389.))
                .add(new Point2D.Double(110., 373.))
                .add(new Point2D.Double(87., 336.))
                .add(new Point2D.Double(68., 292.))
                .add(new Point2D.Double(60., 249.))
                .add(new Point2D.Double(80., 209.))
                .add(new Point2D.Double(117., 180.))
                .add(new Point2D.Double(167., 166.))
                .add(new Point2D.Double(226., 173.))
                .add(new Point2D.Double(278., 182.))
                .add(new Point2D.Double(309., 193.))
                .add(new Point2D.Double(316., 219.))
                .add(new Point2D.Double(294., 233.))
                .add(new Point2D.Double(260., 238.))
                .add(new Point2D.Double(225., 243.))
                .add(new Point2D.Double(187., 251.))
                .add(new Point2D.Double(158., 262.))
                .add(new Point2D.Double(154., 292.))
                .add(new Point2D.Double(183., 310.))
                .add(new Point2D.Double(227., 313.))
                .add(new Point2D.Double(274., 317.))
                .add(new Point2D.Double(309., 319.))
                .add(new Point2D.Double(361., 316.))
                .add(new Point2D.Double(408., 315.))
                .add(new Point2D.Double(459., 307.))
                .add(new Point2D.Double(511., 306.))
                .add(new Point2D.Double(557., 303.))
                .add(new Point2D.Double(598., 303.))
                .add(new Point2D.Double(778., 303.))
                .build();


        board.addTower(tower);
        board.addTower(tower2);

        TowerDefence towerDefence = new TowerDefenceBuilder()
            .setCashVault(cashVault)
            .setGame(game)
            .setBoard(board)
            .setPlayer(player)
            .setRoadSpawn(spawn)
            .setRounds(rounds)
            .setStartState(new MidRoundState())
            .setGameSpeed(GameSpeed.SLOW)
            .build();

        Thread thread = new Thread(towerDefence);
        thread.start();

    }
}
