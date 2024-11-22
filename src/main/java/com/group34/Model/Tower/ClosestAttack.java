package com.group34.Model.Tower;

import java.util.Base64;
import java.util.Comparator;
import java.util.Iterator;
import java.util.stream.Stream;

import com.group34.Model.Enemy.BaseEnemy;

public interface ClosestAttack extends Attack {
    default public void attack(Stream<BaseEnemy> enemies) {

        BaseEnemy closest = enemies
            .filter(enemy -> this.getPosition().distance(enemy.getPosition()).abs() < this.range)
            .min(Integer::compare)
            .map(enemy -> enemy.damage(this.damage));
    };
    
}
