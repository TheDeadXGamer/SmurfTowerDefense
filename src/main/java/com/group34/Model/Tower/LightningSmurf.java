package com.group34.Model.Tower;

import com.group34.Model.Enemy.Enemy;
import com.group34.Model.Projectile.LightningBoltFactory;
import com.group34.Model.Tower.Targeting.ClosestAttack;
import com.group34.Model.Tower.Targeting.Targetings;

import java.awt.geom.Point2D;
import java.util.List;

public class LightningSmurf implements Upgrade,Attack {

    private int attackSpeed;
    private int damage;
    private Point2D position;
    private int range;
    private List<Enemy> targets;
    private int cost;
    private Targetings targeting = new ClosestAttack(new LightningBoltFactory(this), position);

    public LightningSmurf(Point2D position) {
        this.attackSpeed = 1;
        this.damage = 3;
        this.range = 300;
        this.position = position;
        this.cost = 500;
    }

    /**
     * Upgrades the tower and returns the new tower
     * @return the new tower
     */
    @Override
    public Attack upgrade() {
        return new ThunderSmurf(this.position);
    }

    /**
     * Returns the position of the tower
     * @return the position of the tower
     */
    @Override
    public Point2D getPosition() {
        return (Point2D) position.clone();
    }

    /**
     * Sets the position of the tower
     * @param position the new position of the tower
     */
    @Override
    public void setPosition(Point2D position) {
        this.position = position;
    }

    /**
     * Returns the range of the tower
     * @return the range of the tower
     */
    @Override
    public int getRange() {
        return range;
    }

    /**
     * Attacks the enemies in range
     */
    @Override
    public void action() {
        targeting.attack(targets);
    }

    /**
     * Returns the type of the tower
     * @return the type of the tower
     */
    @Override
    public String getTowerType() {
        return "LightningSmurf";
    }

    /**
     * Returns the path to the image of the tower
     * @return the path to the image of the tower
     */
    @Override
    public String getTowerImagePath() {
        return "/assets/Towers/LightningSmurf.png";
    }

    /**
     * Returns the cost of the tower
     * @return the cost of the tower
     */
    @Override
    public int getCost() {
        return cost;
    }

    /**
     * Returns the attack speed of the tower
     * @return the attack speed of the tower
     */
    @Override
    public int getAttackSpeed() {
        return attackSpeed;
    }

    /**
     * Returns the damage of the tower
     * @return the damage of the tower
     */
    @Override
    public int getDamage() {
        return damage;
    }

}
