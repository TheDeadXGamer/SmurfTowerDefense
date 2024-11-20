package com.group34.Model.Tower;

import java.util.Base64;
import java.util.Iterator;
import java.util.stream.Stream;

import com.group34.Model.Enemy.BaseEnemy;

public interface AreaAttack extends Attack {
    default public void attack(Stream<BaseEnemy> enemies) {
        enemies.forEach(target -> {
            int currHealth = target.getHealth();
            target.setHealth(currHealth - this.damage()); 
        });

    };
    
}
