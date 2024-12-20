package com.group34.Model.Tower;

import com.group34.Model.Enemy.Attackable;
import com.group34.Model.Positionable;

/**
 * Interface for the tower listener.
 * @param <enemies> the type of enemy that the tower listens to.
 */
public interface TowerListener<enemies extends Positionable & Attackable> {
    /**
     * Notifies a tower when a certain action happens and passes the enemy related to that action.
     * @param enemy the enemy related to the action.
     */
    void notifyTower(enemies enemy);

    /**
     * Notifies a tower when an enemy dies.
     * @param enemy the enemy that died.
     */
    void notifyOfDeath(enemies enemy);
}
