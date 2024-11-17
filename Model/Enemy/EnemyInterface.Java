package Enemy;

import javafx.geometry.Point2D;

interface EnemyInterface {
    // Arvid: should be split into smaller interfaces (interface segregation principle), see similar methods in PlayerInterface

    // Basic properties
    int getHealth();
    void setHealth(int health);
    int getSpeed();
    void setSpeed(int speed);
    String getName();
    void setName(String name);      // Might have to change these later to some other method of obtaining predetermined names

    // Postition tracking
    double getX();
    double getY();
    void setPosition(Point2D position);

    // Reward/Scoring
    int getReward();        // Gain rewards for killing enemies such as money
    int getScoreValue();    // Top-up a scoreboard for killing enemies

    // Combat related
    void takeDamage(int damage);
    boolean isAlive();

    // Movement related
    void move();

}