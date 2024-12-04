package com.group34.Model.Round;

import com.group34.Model.Enemy.Enemy;

public class RoundEvent {
    final Enemy enemy;
    final int counter;

    public RoundEvent(Enemy enemy, int counter) {
        this.enemy = enemy;
        this.counter = counter;
    }
}
