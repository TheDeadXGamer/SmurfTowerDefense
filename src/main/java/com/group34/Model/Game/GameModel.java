package com.group34.Model.Game;

import com.group34.Model.Player.Player;

public class GameModel {
    private Difficulty difficulty;
    private GameStatus gameStatus;
    private int currentRound;
    private Player player;

    public GameModel() {
        this.difficulty = Difficulty.EASY; // Setting EASY as default 
        this.gameStatus = GameStatus.ACTIVE; 
        this.currentRound = 1;
        this.player = new Player(200, 1000); // EASY values as default

    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
        difficultyModifiers();
    }

    public void difficultyModifiers(){
        switch (difficulty) {
            case EASY:
                player.setHealth(200);
                player.setMoney(1000);
                System.out.println("Player chose " + difficulty);
                break;
            
            case MEDIUM:
                player.setHealth(150);
                player.setMoney(800);
                System.out.println("Player chose " + difficulty);
                break;

            case HARD:
                player.setHealth(100);
                player.setMoney(600);
                System.out.println("Player chose " + difficulty);
                break;
        }
    }

    public int getCurrentRound(){
        return currentRound;
    }

    public void nextRound(){
        currentRound ++;
    }

    public GameStatus getGameStatus(){
        return gameStatus;
    }

    public void setGameStatus(GameStatus status) {
        this.gameStatus = status;
    }
}