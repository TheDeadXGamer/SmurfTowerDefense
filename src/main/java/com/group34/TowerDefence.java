package com.group34;

import java.util.List;

import javax.swing.JFrame;

import com.group34.GameState.GameState;
import com.group34.Model.Board.Board;
import com.group34.Model.Cash.CashVault;
import com.group34.Model.Game.Game;
import com.group34.Model.Game.Player;
import com.group34.Model.Road.RoadSpawn;
import com.group34.Model.Round.Round;
import com.group34.View.BoardView;
import com.group34.View.Shop.ShopController;
import com.group34.View.Shop.ShopModel;

public class TowerDefence extends JFrame implements Runnable{
    static final int FPS = 60;
    private CashVault cashVault;
    private Game game;
    private Board board;
    private Player player;
    private GameState currentState;
    private RoadSpawn roadSpawn;
    private List<Round> rounds;


    public TowerDefence(TowerDefenceBuilder builder) {

        this.cashVault = builder.cashVault;
        this.game = builder.game;
        this.board = builder.board;
        this.player = builder.player;
        this.currentState = builder.startState;
        this.roadSpawn = builder.roadSpawn;
        this.rounds = builder.rounds;
        
        // Set up the JFrame
        setTitle("Tower Defence");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
        setLocationRelativeTo(null);

        // TODO: maybe not the best usage of shop stuff like this, change later
        ShopModel shopModel = new ShopModel(player, cashVault, board);
        ShopController shopController = new ShopController(shopModel);
        BoardView boardView = new BoardView(this.board, this.game, shopController);

        add(boardView);
        
        pack();
        setVisible(true);

    }


    @Override
    public void run() {
        this.update();
    }


    /**
     * Repaints the game
     */
    @Override
    public void repaint() {
        super.repaint();
        try {
            Thread.sleep(1000 / FPS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates the game state
     * @return void
     */
    public void update() {
        currentState.update(this);
    }

    /**
     * Sets the game state
     * @param state
     */
    public void setState(GameState state) {
        this.currentState = state;
        this.currentState.enterState(this);
    }

    /**
     * Gets the player
     * @return Player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Gets the cash vault
     * @return CashVault
     */
    public CashVault getCashVault() {
        return cashVault;
    }

    /**
     * Gets the game
     * @return Game
     */
    public Game getGame() {
        return game;
    }

    /**
     * Gets the board
     * @return Board
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Gets the road spawn
     * @return RoadSpawn
     */
    public RoadSpawn getRoadSpawn() {
        return roadSpawn;
    }

    /**
     * Gets the rounds
     * @return List<Round>
     */
    public List<Round> getRounds() {
        return rounds;
    }
   
}
