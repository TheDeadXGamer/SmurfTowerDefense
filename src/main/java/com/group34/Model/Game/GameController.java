package com.group34.Model.Game;

public class GameController{
    private GameModel gameModel;

    public GameController(GameModel gameModel) {
        this.gameModel = gameModel;
    }

    public void setDifficulty(Difficulty difficulty) {
        gameModel.setDifficulty(difficulty);
    }

    public Difficulty getDifficulty() {
        return gameModel.getDifficulty();
    }
}
