package com.group34.Model.Round;

import java.util.Optional;
import java.util.PriorityQueue;

import com.group34.Model.Enemy.Enemy;

public class Round {
    
    PriorityQueue<RoundEvent> events;
    int counter = 0;


    public Round(PriorityQueue<RoundEvent> events) {
        this.events = events;
    }

    public Optional<Enemy> spawn() {
        Optional<Enemy> res;
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

class RoundBuilder {
    private final PriorityQueue<RoundEvent> events;

    public RoundBuilder() {
        events = new PriorityQueue<>();
    }

    public RoundBuilder addEvent(RoundEvent event) {
        events.add(event);
        return this;
    }

    public Round build() {
        return new Round(events);
    }

}



