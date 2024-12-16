package com.group34.Model.Round;

import java.util.Optional;
import java.util.PriorityQueue;

import com.group34.Model.Enemy.EnemyFactory;



public class Round {
    
    PriorityQueue<RoundEvent> events;
    int counter = 0;


    public Round(RoundBuilder builder) {
        this.events = builder.events;
    }

    public Optional<EnemyFactory> spawn() {
        if (!events.isEmpty() && events.peek().counter == counter) {
            return Optional.of(events.poll().enemy); 
        }
        counter++; 
        return Optional.empty();
    }

    public boolean isRoundOver() {
        return events.isEmpty();
    }

}



