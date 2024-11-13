interface EnemiesInterface {

    // Basic properties
    int getHealth();
    void setHealth(int health);
    int getSpeed();
    void setSpeed();

    // Postition tracking
    double getX();
    double getY();
    void setPosition(double X, double Y);

    // Reward/Scoring
    int getReward();        // Gain rewards for killing enemies such as money
    int getScoreValue();    // Top-up a scoreboard for killing enemies

    // Combat related
    void takeDamage(int damage);
    boolean isAlive();

    // Movement related
    void move();

}