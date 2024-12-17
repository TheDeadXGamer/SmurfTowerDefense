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


                    //If there is an enemy to spawn, create it and add it to the game
                    if (spawn.isPresent()) {
                        RoadToken token = new RoadToken(towerDefence.getRoadSpawn());
                        towerDefence.getGame().addEnemy(spawn.get().createEnemy(token));
                    }
                    
                    //Update enemies and repaint the view
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

        //Iterate over enemies and check if they are alive
        //If not, add them to the killedEnemies list
        for (; iterEnemy.hasNext();) {
            Enemy enemy = iterEnemy.next();
            if (!enemy.isAlive()) {
                killedEnemies.add(enemy);
                continue;
            }

            //Move enemy and notify observers
            enemy.move();
            game.getNotifier().getInstance().notifyThatEnemyMoved(enemy);
        }
        for (Enemy enemy: killedEnemies) {
            game.getNotifier().getInstance().notifyThatEnemyDied(enemy);
            game.removeEnemy(enemy);
        }
    }
}
