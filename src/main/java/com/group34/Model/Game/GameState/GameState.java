package com.group34.Model.Game.GameState;

import com.group34.Model.Game.GameStatus;

public interface GameState {
    void enterState(GameStatus gameStatus);
    void exitState(GameStatus gameStatus);
    void update(GameStatus gameStatus);
}
