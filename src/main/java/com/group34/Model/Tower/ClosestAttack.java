package com.group34.Model.Tower;


import java.util.Comparator;
import java.util.stream.Stream;

import com.group34.Model.Enemy.Enemy;

public interface ClosestAttack extends Attack {
    @Override
    default public void attack(Stream<Enemy> enemies) {
        
        Enemy closest = enemies
            .min(Comparator.comparing(target -> this.getPosition().distance(target.getPosition()))).get();
        closest.damage(this.getDamage());
    
    };
    
}
