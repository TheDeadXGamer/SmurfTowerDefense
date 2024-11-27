package com.group34.Model.Tower;


import java.util.stream.Stream;

import com.group34.Model.Enemy.Enemy;

public interface AreaAttack extends Attack {
    @Override
    default public void attack(Stream<Enemy> enemies) {
        /*
        enemies.forEach(target -> {
            int currHealth = target.getHealth();
            target.setHealth(currHealth - this.damage()); 
        });
        */

    };
    
}
