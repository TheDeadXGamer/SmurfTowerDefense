package com.group34.Model.Game;

import java.util.List;

import com.group34.Model.Board.Board;
import com.group34.Model.Cash.CashVault;
import com.group34.Model.Road.RoadSpawn;
import com.group34.View.Shop.ShopModel;
import com.group34.Model.Round.Round;

public class GameBuilder {
    Board board;
    CashVault cashVault;
    Game game;
    Player player;
    RoadSpawn roadSpawn;
    List<Round> rounds;

    public GameBuilder setBoard(Board board) {
        this.board = board;
        return this;
    }

    public GameBuilder setPlayer(Player player) {
        this.player = player;
        return this;
    }

    public GameBuilder setCashVault(CashVault cashVault) {
        this.cashVault = cashVault;
        return this;
    }

    public GameBuilder setRoadSpawn(RoadSpawn roadSpawn) {
        this.roadSpawn = roadSpawn;
        return this;
    }

    public GameBuilder setRounds(List<Round> rounds) {
        this.rounds = rounds;
        return this;
    }

    public Game build() {
        return new Game(this);
    }
}
