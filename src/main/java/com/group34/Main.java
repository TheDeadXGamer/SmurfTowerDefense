package com.group34;

import javax.swing.JFrame;

import com.group34.Model.Board.Board;
import com.group34.Model.Game.Game;
import com.group34.Model.Game.Player;
import com.group34.View.BoardView;



class TowerDefence implements Runnable {
    Game game;
    Board board;
    Player player;

    @Override
    public void run() {
        while (player.isAlive()) {
            //board.update();
            //game.update();
        }
    }

}

public class Main {
    public static void main (String[] args){

        JFrame window = new JFrame("Tower Defence");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setLocationRelativeTo(null);
        
        Board board = new Board(815, 635);
        window.add(new BoardView(board));

        window.pack();
        window.setVisible(true);
       // GameView game = new GameView();
       // game.setVisible(true);
    }
}
