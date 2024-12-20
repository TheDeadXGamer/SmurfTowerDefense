package com.group34.Model.Round;

import com.group34.Model.Enemy.EnemyFactory;

/**
 * Represents an event that occurs in a round
 */
public class RoundEvent implements Comparable<RoundEvent> {
    final EnemyFactory enemy;
    final int counter;

    public RoundEvent(EnemyFactory enemy, int counter) {
        this.enemy = enemy;
        this.counter = counter;
    }

    /**
     * Compares the counter of the current RoundEvent with the counter of another RoundEvent
     * @param o the other RoundEvent
     * @return the difference between the counters
     */
    @Override
    public int compareTo(RoundEvent o) {
        return Integer.compare(counter, o.counter);
    }
}
