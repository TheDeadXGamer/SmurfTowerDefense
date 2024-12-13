package com.group34.Model.Game;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.group34.Model.Board.Board;
import com.group34.Model.Enemy.Enemy;
import com.group34.Model.Game.GameState.GameState;
import com.group34.Model.Game.GameState.MidRoundState;
import com.group34.Model.Road.RoadSpawn;
import com.group34.Model.Round.Round;

public class Game {

    private TowerNotifier notifier = new TowerNotifier();
    private final List<Enemy> enemies = new ArrayList<>();
    private GameState currentState;
    private Board board;
    private Player player;
    private List<Round> rounds;
    private RoadSpawn roadSpawn;
    private Repaintable repaintable;

    public Game(GameBuilder builder) {
        currentState = new MidRoundState();
        board = builder.board;
        player = builder.player;
        rounds = builder.rounds;
        roadSpawn = builder.roadSpawn;
    }

    public void addEnemy(Enemy enemy) {
        enemies.add(enemy);
    }

    public void removeEnemy(Enemy enemy) {
        enemies.remove(enemy);
    }

    public void setRounds(List<Round> rounds) {
        this.rounds = rounds;
    }

    public void setRepaintable(Repaintable repaintable) {
        this.repaintable = repaintable;
    }

    public void addRound(Round round) {
        rounds.add(round);
    }

    public Iterator<Enemy> getEnemies() {
        return enemies.iterator();
    }

    public RoadSpawn getRoadSpawn() {
        return roadSpawn;
    }

    public TowerNotifier getNotifier() {
        return notifier;
    }

    public Board getBoard() {
        return board;
    }

    public Player getPlayer() {
        return player;
    }

    public List<Round> getRounds() {
        return rounds;
    }

    public Repaintable getRepaintable() {
        return repaintable;
    }

    public int enemiesLeft() {
        return enemies.size();
    }

    public void update() {
        currentState.update(this);
    }

}
