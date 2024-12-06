package com.group34.Model.Tower;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;


import com.group34.Model.Enemy.Enemy;
import com.group34.Model.Projectile.LightningBoltFactory;
import com.group34.Model.Tower.Targeting.ClosestAttack;
import com.group34.Model.Tower.Targeting.Targetings;

public class LightningSmurf implements Upgrade, Attack {

    private Boolean canAttack = true;

    private float lastAttackTime = 0;

    protected int attackSpeed;
    protected int damage;

    protected Point2D position;
    protected int range;
    List<Enemy> targets = new ArrayList<>();
    Targetings targeting;
    
    public LightningSmurf(Point2D position, int attackSpeed, int damage, int range) {
        this.attackSpeed = attackSpeed;
        this.damage = damage;
        this.range = range;
        this.position = position;
        this.targeting = new ClosestAttack(new LightningBoltFactory(this), this.position);
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
        if (System.nanoTime() - lastAttackTime >=  (Math.pow(10,9)) / attackSpeed) {
            canAttack = true;
        }
        if (canAttack) {

            targeting.attack(targets);
            canAttack = false;
            lastAttackTime = System.nanoTime();

        }
    }

    @Override
    public void checkInRange(Enemy enemy) {

        if (position.distance(enemy.getPosition()) <= range && !targets.contains(enemy)) {
            targets.add(enemy);

        }
        else if (position.distance(enemy.getPosition()) > range && targets.contains(enemy)) {
            targets.remove(enemy);
        }
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
        return 10;
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


    @Override
    public void notifyAllTowers(Enemy enemy) {
        checkInRange(enemy);
    }
}
