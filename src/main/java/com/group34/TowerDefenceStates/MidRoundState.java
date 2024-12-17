package com.group34.TowerDefenceStates;

import java.util.List;
import java.util.Optional;

import com.group34.Model.Enemy.Enemy;
import com.group34.Model.Enemy.EnemyFactory;
import com.group34.Model.Road.RoadToken;
import com.group34.Model.Round.Round;
import com.group34.TowerDefence;

public class MidRoundState implements GameState {
    
    public void enterState(TowerDefence towerDefence) {
        System.out.println("Starting new round...");
    }

    public void exitState(TowerDefence towerDefence) {
        System.out.println("Round completed!");
    }

    public void update(TowerDefence towerDefence) {
        for (Round round : towerDefence.getRounds()) {
            while (!round.isRoundOver() || towerDefence.getGame().enemiesLeft() > 0) {
                if (towerDefence.getPlayer().isAlive()) {
                    Optional<EnemyFactory> spawn = round.spawn();

                    if (spawn.isPresent()) {
                        RoadToken token = new RoadToken(towerDefence.getRoadSpawn());
                        towerDefence.getGame().addEnemy(spawn.get().createEnemy(token));
                    }

                    List<Enemy> killed = towerDefence.getGame().update();
                    for (Enemy enemy : killed) {
                        towerDefence.getCashVault().deposit(enemy.getReward());
                    }

                    towerDefence.getBoard().update();
                    towerDefence.getFrame().repaint();

                    try {
                        Thread.sleep(1000 / 60);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
  
                }
            }
        }
    }

}
