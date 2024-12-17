package com.group34.GameState;

import com.group34.TowerDefence;

public interface GameState {
    void enterState(TowerDefence towerDefence);
    void exitState(TowerDefence towerDefence);
    void update(TowerDefence towerDefence);
}
