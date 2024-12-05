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
        Optional<EnemyFactory> res;
        if (events.peek().counter == counter) {
            res = Optional.of(events.poll().enemy);
        } else {
            res = Optional.empty();
        }
        counter++;
        return res;
        
    }

    public int eventsLeft() {
        return events.size();
    }

}



