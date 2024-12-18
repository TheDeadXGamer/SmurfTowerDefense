package com.group34.Model.Tower;

import com.group34.Model.Enemy.Attackable;
import com.group34.Model.Positionable;

public interface Attack<enemies extends Positionable & Attackable> extends Tower<enemies> {
   /**
    * Retrieves the attack speed of the tower.
    * <p>
    * The attack speed determines how quickly the tower can attack enemies,
    * measured as the number of attacks per unit of time.
    * </p>
    *
    * @return the attack speed of the tower as an integer.
    */
   public int getAttackSpeed();

   /**
    * Retrieves the damage dealt by the tower's attacks.
    * <p>
    * The damage represents the amount of health reduction inflicted on an enemy
    * when the tower successfully attacks.
    * </p>
    *
    * @return the damage dealt by the tower as an integer.
    */
   public int getDamage();
}
