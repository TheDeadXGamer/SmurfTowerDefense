package com.group34.Model.Game.GameState;

import com.group34.Model.Game.Game;
import com.group34.Model.Game.Repaintable;
import com.group34.Model.Road.RoadToken;
import com.group34.Model.Round.Round;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import com.group34.Model.Enemy.Enemy;
import com.group34.Model.Enemy.EnemyFactory;

public class MidRoundState implements GameState {
    
    public void enterState(Game game) {
        System.out.println("Starting new round...");
    }

    public void exitState(Game game) {
        System.out.println("Round completed!");
    }

    public void update(Game game) {
        for (Round round : game.getRounds()) {
            while (round.eventsLeft() > 0 || game.enemiesLeft() > 0) {
                if (game.getPlayer().isAlive()) {
                    Optional<EnemyFactory> spawn = round.spawn();

                    if (spawn.isPresent()) {
                        RoadToken token = new RoadToken(game.getRoadSpawn());
                        game.addEnemy(spawn.get().createEnemy(token));
                    }

                    updateKilledEnemies(game);
                    game.getBoard().update();
                    game.getRepaintable().repaint();
                }
            }
        }
        System.out.println("Game Over");
        
    }

    private void updateKilledEnemies(Game game) {
        List<Enemy> killedEnemies = new ArrayList<>();
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
