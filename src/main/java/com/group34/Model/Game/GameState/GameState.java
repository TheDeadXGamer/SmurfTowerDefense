package com.group34.Model.Game.GameState;

import com.group34.Model.Game.Game;

public interface GameState {
    void enterState(Game game);
    void exitState(Game game);
    void update(Game game);
}
