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
    int getReward();
    int getScoreValue();

    // Combat related
    void takeDamage(int damage);
    boolean isAlive();

    // Movement related
    void move();

}