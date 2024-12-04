package com.group34.Model.Game.GameState;

import com.group34.Model.Game.GameStatus;
import com.group34.Model.Player.Player;

public class MidRoundState implements GameState {
    
    public void enterState(GameStatus gameStatus) {
        // Display round number
    }

    public void exitState(GameStatus gameStatus) {
        // Go to next round
    }

    public void update(GameStatus gameStatus) {
        Player player = gameStatus.getPlayer();

        if(player.getHealth() <= 0){
            gameStatus.setState(new GameOverState());
        }
    }
}
