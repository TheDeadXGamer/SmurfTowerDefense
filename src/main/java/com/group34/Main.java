package com.group34;

import java.awt.Dimension;

import javax.swing.JFrame;

import com.group34.Model.Board.Board;
import com.group34.View.BoardView;
import com.group34.View.LightningSmurfView;



class TowerDefence extends JFrame implements Runnable {
    static final int FPS = 60;

    //Game game;
    //Board board;
    //Player player;

    public TowerDefence() {
        //this.board = board;
        //this.player = player;


        init();

    }


    void init() {
        setTitle("Tower Defence");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        
        Board board = new Board(new Dimension(815, 635));
        BoardView boardView = new BoardView(board);
        boardView.add(new LightningSmurfView(board));
        add(boardView);
        
        pack();
        setVisible(true);
    }

    @Override
    public void run() {
        while (true) {
            System.out.println("Game loop");
            repaint();
        }
    }

    


}

public class Main {
    public static void main (String[] args){

       Thread thread = new Thread(new TowerDefence());
       thread.start();

    }
}
