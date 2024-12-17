package com.group34;

import java.util.List;

import com.group34.Model.Board.Board;
import com.group34.Model.Game.Game;
import com.group34.Model.Game.Player;
import com.group34.Model.Road.RoadSpawn;
import com.group34.Model.Round.Round;
import com.group34.Model.Shop.CashVault;
import com.group34.Model.Shop.ShopModel;
import com.group34.TowerDefenceStates.GameState;

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