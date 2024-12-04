package com.group34.Model.Game;

import com.group34.Model.Game.GameState.*;

public class GameStatus {
    private GameState currentState;

    public GameStatus() {
        menuState = new MenuState();
        preparationState = new PreparationState();
        roundState = new RoundState();
        endRoundState = new EndRoundState();
        gameOverState = new GameOverState();
        victoryState = new VictoryState();
        pausedState = new PausedState();
        
        currentState = menuState;  // Default start state
    }

    public void setState(GameState state) {
        currentState.exitState(this);
        currentState = state;
        currentState.enterState(this);
    }

    public void update() {
        currentState.update(this);
    }
    
}
