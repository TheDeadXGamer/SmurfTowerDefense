package com.group34;

import com.group34.Model.Board.Board;
import com.group34.Model.Game.Game;
import com.group34.Model.Game.Player;

class TowerDefenceBuilder {
}
class TowerDefence implements Runnable {
    Game game;
    Board board;
    Player player;

    @Override
    public void run() {
        while (player.isAlive()) {
            board.update();
            game.update();
        }
    }

}

public class Main {
    public static void main (String[] args){
       // GameView game = new GameView();
       // game.setVisible(true);
    }
}
