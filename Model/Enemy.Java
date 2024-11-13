import javafx.geometry.Point2D;

public class Enemy implements EnemyInterface {
    private int health;
    private int speed;
    private double xPosition;
    private double yPosition;

    public Enemies(int health, int speed, double xPosition, double yPosition){
        this.health = health;
        this.speed = speed;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }

    @Override
    public int getHealth(){
        return this.health
    }

    @Override
    public void setHealth(int health){
        if(health > 0){
            this.health = health;
        }
    }

    @Override
    public int getSpeed(){
        return this.speed
    }

    @Override
    public void setSpeed(int speed){
        if(speed > 0){
            this.speed = speed;
        }
    }

    // Using JavaFX Point2D class for position tracking
    @Override
    public Point2D getPosition(){
        return new Point2D(xPosition, yPosition);
    }

    @Override
    public void setPosition(Point2D position){
        this.xPosition = position.getX();
        this.yPosition = position.getY();
    }

}