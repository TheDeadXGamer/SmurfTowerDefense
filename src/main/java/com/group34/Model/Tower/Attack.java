package com.group34.Model.Tower;

import java.util.Iterator;
import java.util.stream.Stream;

import com.group34.Model.Enemy.BaseEnemy;
import com.group34.Model.Projectile.ProjectileInterface;

public interface Attack extends Tower {
   public int getAttackSpeed();
   public  int getDamage();
   public void attack(Stream<BaseEnemy> enemy);


}
