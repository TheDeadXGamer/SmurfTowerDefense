package com.group34;

import com.group34.Model.Board.Board;
import com.group34.Model.Game.Player;
import com.group34.View.Game.Game;




class TowerDefenceBuilder {
}
class TowerDefence implements Runnable {
    Game game;
    Board board;
    Player player;

    public void run() {
        while (player.isAlive()) {
            board.update();
            game.repaint();
        }
    }

}

public class Main {
    public static void main (String[] args){
        Game game = new Game();
        game.setVisible(true);
    }
}