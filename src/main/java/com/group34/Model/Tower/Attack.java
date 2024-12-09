package com.group34.Model.Tower;

import com.group34.Model.Enemy.Attackable;
import com.group34.Model.Positionable;

public interface Attack<enemies extends Positionable & Attackable> extends Tower<enemies> {
   public int getAttackSpeed();
   public int getDamage();
}

