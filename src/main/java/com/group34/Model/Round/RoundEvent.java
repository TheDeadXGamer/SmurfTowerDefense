package com.group34.Model.Round;

import com.group34.Model.Enemy.EnemyFactory;

public class RoundEvent implements Comparable<RoundEvent> {
    final EnemyFactory enemy;
    final int counter;

    public RoundEvent(EnemyFactory enemy, int counter) {
        this.enemy = enemy;
        this.counter = counter;
    }

    @Override
    public int compareTo(RoundEvent o) {
        return Integer.compare(counter, o.counter);
    }


}
