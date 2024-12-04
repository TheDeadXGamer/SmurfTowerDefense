package com.group34.Model.Game;



public class GameController{
    private GameModel gameModel;
    private GameLoop gameLoop;


    public GameController(GameModel gameModel, GameLoop gameLoop) {
        this.gameModel = gameModel;
        this.gameLoop = gameLoop;
    }

    public void setDifficulty(Difficulty difficulty) {
        gameModel.setDifficulty(difficulty);
    }

    public Difficulty getDifficulty() {
        return gameModel.getDifficulty();
    }

    public int getPlayerHealth(){
        return gameModel.getPlayerHealth();
    }

    public int getPlayerMoney(){
        return gameModel.getPlayerMoney();
    }

    public void startGame() {
        gameLoop.start();
    }

    public void stopGame() {
        gameLoop.stop();
    }
    
        
}
