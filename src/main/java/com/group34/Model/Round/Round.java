package com.group34.Model.Round;

import java.util.Optional;
import java.util.PriorityQueue;

import com.group34.Model.Enemy.EnemyFactory;

/**
 * Round is a class that represents a round in the game.
 */
public class Round {
    PriorityQueue<RoundEvent> events;
    int counter = 0;

    public Round(RoundBuilder builder) {
        this.events = builder.events;
    }

    /**
     * Spawns an enemy if the counter matches the event counter.
     * @return Optional of the enemy that was spawned
     */
    public Optional<EnemyFactory> spawn() {
        if (!events.isEmpty() && events.peek().counter == counter) {
            return Optional.of(events.poll().enemy); 
        }
        counter++; 
        return Optional.empty();
    }

    /**
     * Checks if the round is over.
     * @return True if the round is over, false otherwise
     */
    public boolean isRoundOver() {
        return events.isEmpty();
    }
}



