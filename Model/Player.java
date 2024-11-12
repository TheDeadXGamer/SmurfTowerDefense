import java.util.ArrayList;
import java.util.List;

public class Player implements PlayerInterface {
    private int health;
    private int money;
    private int roundNumber;
    private List<Tower> towers;
    private GameStatus gameStatus;
    private Difficulty difficulty;

    public Player(int startingHealth, int startingMoney, Difficulty difficulty) {
        this.health = startingHealth;
        this.money = startingMoney;
        this.roundNumber = 1;
        this.towers = new ArrayList<>();
        this.gameStatus = GameStatus.ACTIVE;    // Using ACTIVE, PAUSED and GAME_OVER as an enum for playerstatus
        this.difficulty = difficulty;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public void setHealth(int health) {
        this.health = health;
        if (health <= 0) {
            this.gameStatus = GameStatus.GAME_OVER;
        }
    }

    @Override
    public int getMoney() {
        return money;
    }

    @Override
    public void setMoney(int money) {
        this.money = money;
    }

    @Override
    public int getRoundNumber() {
        return roundNumber;
    }

    @Override
    public void setRoundNumber(int roundNumber) {
        this.roundNumber = roundNumber;
    }

    @Override
    public List<Tower> getTowers() {
        return towers;
    }

    @Override
    public void addTower(Tower tower) {
        if (money >= tower.getCost()) {
            towers.add(tower);
            money -= tower.getCost();
        } else {
            System.out.println("Insufficient funds to place this tower.");
        }
    }

    @Override
    public void removeTower(Tower tower) {
        towers.remove(tower);
        money += tower.getCost() / 2; // If we should use this logic for selling?
    }

    @Override
    public GameStatus getStatus() {
        return gameStatus;
    }

    @Override
    public void setStatus(GameStatus status) {
        this.gameStatus = status;
    }

    @Override
    public boolean isAlive() {
        return health > 0;
    }

    @Override
    public Difficulty getDifficulty() {
        return difficulty;
    }

    @Override
    public void applyDifficultyModifiers() {
        // logic for difficulty multipliers
    }

    @Override
    public void modifyMoney(int amount) {
        this.money += amount;
    }
}
