package com.group34.Model.Round;

import java.util.PriorityQueue;

public class RoundBuilder {
    final PriorityQueue<RoundEvent> events;

    public RoundBuilder() {
        events = new PriorityQueue<>();
    }

    public RoundBuilder addEvent(RoundEvent event) {
        events.add(event);
        return this;
    }

    public Round build() {
        return new Round(this);
    }
}
