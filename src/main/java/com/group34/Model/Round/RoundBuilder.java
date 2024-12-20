package com.group34.Model.Round;

import java.util.PriorityQueue;

/**
 * RoundBuilder is a builder class for Round
 */
public class RoundBuilder {
    final PriorityQueue<RoundEvent> events;

    public RoundBuilder() {
        events = new PriorityQueue<>();
    }

    /**
     * Adds an event to the round
     * @param event The event to add
     * @return The RoundBuilder object
     */
    public RoundBuilder addEvent(RoundEvent event) {
        events.add(event);
        return this;
    }

    /**
     * Builds the round
     * @return The round object
     */
    public Round build() {
        return new Round(this);
    }
}
