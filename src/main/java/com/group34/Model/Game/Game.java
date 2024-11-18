package com.group34.Model.Game;
import javax.swing.JFrame;

public class Game extends JFrame {
    
    public Game(){
        setTitle("Smurf Tower Defence");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        // Initializing MVC components
        GameModel model = new GameModel();
        GameController controller = new GameController(model);
        GameView gameView = new GameView(controller);

        setContentPane(gameView); // Set GameView as Jpanel for JFrame
    }


    public static void main (String[] args){
        Game game = new Game();
        game.setVisible(true);
    }
}