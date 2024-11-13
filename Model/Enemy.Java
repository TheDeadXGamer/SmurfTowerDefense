import javafx.geometry.Point2D;

public class Enemy implements EnemyInterface {
    // Enum for enemy types with their properties
    public enum EnemyType {
        GARGAMEL("Gargamel", 200, 10, 50, 100),
       
        final String name;
        final int health;
        final int speed;
        final int reward;
        final int scoreValue;
       
        EnemyType(String name, int health, int speed, int reward, int scoreValue) {
            this.name = name;
            this.health = health;
            this.speed = speed;
            this.reward = reward;
            this.scoreValue = scoreValue;
        }
       
        public String getName() {
            return name;
        }
    }
   
    private EnemyType type;
    private int health;
    private int speed;
    private Point2D position;  // Single position object instead of x and y variables
    private int reward;
    private int scoreValue;
   
    // Constructor for Enemy using EnemyType
    public Enemy(EnemyType type, Point2D startPosition) {
        this.type = type;
        this.health = type.health;
        this.speed = type.speed;
        this.position = startPosition;
        this.reward = type.reward;
        this.scoreValue = type.scoreValue;
    }
   
    // Getter for name
    public String getName() {
        return type.getName();
    }
   
    // Getter for type
    public EnemyType getType() {
        return type;
    }

    // Returns health 
    @Override
    public int getHealth() {
        return this.health;
    }

    // Sets health
    @Override
    public void setHealth(int health) {
        if(health > 0) {
            this.health = health;
        }
    }

    // Returns speed
    @Override
    public int getSpeed() {
        return this.speed;
    }

    // Set speeds
    @Override
    public void setSpeed(int speed) {
        if(speed > 0) {
            this.speed = speed;
        }
    }

    // Returns position
    @Override
    public Point2D getPosition() {
        return position;  // Return the Point2D object directly
    }

    // Sets JavaFX Point2D position
    @Override
    public void setPosition(Point2D position) {
        this.position = position;  // Store the Point2D object directly
    }

    // Returns (monetary) reward
    @Override
    public int getReward() {
        return this.reward;
    }

    // Returns scoreValue
    @Override
    public int getScoreValue() {
        return this.scoreValue;
    }

    // Causes damage to enemy, or sets to 0 representing death
    @Override
    public void takeDamage(int damage) {
        if (damage > 0) {
            this.health = Math.max(0, this.health - damage);
        }
    }

    // Is True if characters health is above 0
    @Override
    public boolean isAlive() {
        return this.health > 0;
    }
    
    @Override
    public void move() {
        //TODO Implement in accordance with game's movement logic
    }
}