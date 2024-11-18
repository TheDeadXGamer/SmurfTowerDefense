package com.group34.Model.Enemy;

public class EnemyGargamel extends BaseEnemy {
    public EnemyGargamel() {
        super("Enemy.Gargamel",  // name
                200,         // health
                10,          // speed
                50,          // reward
                100          // scoreValue
        );
    }

    @Override
    public void move() {
        // Enemy.Gargamel-specific movement implementation
    }
}