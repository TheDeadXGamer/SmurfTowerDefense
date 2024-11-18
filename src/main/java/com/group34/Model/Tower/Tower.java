package com.group34.Model.Tower;
import java.awt.geom.Point2D;
import com.group34.Model.Enemy.BaseEnemy;
import java.lang.Math;
public abstract class Tower  {
    protected int attackSpeed;
    protected int damage;
    protected String towerType;
    protected int cost;
    protected Point2D position;
    protected int range;

    protected int size; //Im thinking that towers should have hitboxes of different sizes.

    public Tower(int attackSpeed, int damage, int cost, Point2D position, int range) {
        this.attackSpeed = attackSpeed;
        this.damage = damage;
        this.cost = cost;
        this.position = position;
        this.range = range;
    }

    //maybe also a nr of projectiles variable?
    //Dont know how to nicely store upgrades for towers yet

    public int getAttackSpeed() {return this.attackSpeed;}

    public int getDamage() {return this.damage;}

    public String getTowerType() {return this.towerType;}
    public int getCost() {return this.cost;}

    private void setAttackSpeed(int attackSpeed) {
        this.attackSpeed = attackSpeed; 
    }

    private void setDamage(int damage) {
        this.damage = damage;
    }

    private void setTowerType(String towerType) {
        this.towerType = towerType;
    }

    private void setCost(int price) {
        this.cost = price;
        
    }
    //should shooting be instant or will the projectiles be able to miss?
    public void attack(BaseEnemy target) {
        shootProjectile(target);
        int currHealth = target.getHealth();
        target.setHealth(currHealth - this.damage);

    }

    public void shootProjectile(BaseEnemy target) {
        Point2D EnemyPosition = new Point2D.Double(target.getX(), target.getY());
        double Coefficient = (EnemyPosition.getY() - position.getY()) / (EnemyPosition.getX() - position.getX());
        //venne riktigt hur jag ska göra detta, tänker att det kan vänta tills annat implementeras
    }
}
