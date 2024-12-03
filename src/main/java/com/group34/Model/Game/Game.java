package com.group34.Model.Game;

import java.util.List;

import com.group34.Model.Board.Board;
import com.group34.Model.Enemy.Enemy;

public class Game{
    private int currentRound;
    private Player player;
    private List<Enemy> enemies;
    private Board board;

    public Game() {
        this.currentRound = 1;
        this.player = new Player(200); 
    }

    public int getCurrentRound(){
        return currentRound;
    }

    public void nextRound(){
        currentRound ++;
    }

    public void subscribe(Enemy enemy){
        enemies.add(enemy);
    }

    public void unsubscribe(Enemy enemy){
        enemies.remove(enemy);
    }


    public void update() {
        for (Enemy enemy : enemies) {
            enemy.move();
        }
        board.update();
        player.isAlive();
    }
    
}
