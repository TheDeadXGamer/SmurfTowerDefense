package com.group34.GameState;

import com.group34.Model.Game.Game;
import com.group34.Model.Road.RoadToken;
import com.group34.Model.Round.Round;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import com.group34.TowerDefence;
import com.group34.Model.Enemy.Enemy;
import com.group34.Model.Enemy.EnemyFactory;

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

                    updateKilledEnemies(towerDefence);
                    towerDefence.getBoard().update();
                    towerDefence.repaint();
                }
            }
        }
        System.out.println("Game Over");
        
    }

    private void updateKilledEnemies(TowerDefence towerDefence) {
        List<Enemy> killedEnemies = new ArrayList<>();
        Game game = towerDefence.getGame();
        Iterator<Enemy> iterEnemy = game.getEnemies();
        for (; iterEnemy.hasNext();) {
            Enemy enemy = iterEnemy.next();
            if (!enemy.isAlive()) {
                killedEnemies.add(enemy);
                continue;
            }
            enemy.move();
            game.getNotifier().getInstance().notifyThatEnemyMoved(enemy);
        }
        for (Enemy enemy: killedEnemies) {
            game.removeEnemy(enemy);
        }
    }
}
