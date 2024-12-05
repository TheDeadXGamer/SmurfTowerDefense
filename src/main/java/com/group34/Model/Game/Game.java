package com.group34.Model.Game;

import java.util.List;

import com.group34.Model.Enemy.Enemy;

public class Game {
    private List<Enemy> enemies;

    public void subscribe(Enemy enemy){
        enemies.add(enemy);
    }

    public void unsubscribe(Enemy enemy){
        enemies.remove(enemy);
    }


    public void update() {
        for (Enemy enemy : enemies) {
            enemy.move(); 
        }
    }

}
