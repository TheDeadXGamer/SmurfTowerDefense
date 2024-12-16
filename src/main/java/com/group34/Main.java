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
            .build();

        Thread thread = new Thread(towerDefence);
        thread.start();

    }
}
