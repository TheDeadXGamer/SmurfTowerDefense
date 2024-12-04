package com.group34.Model.Road;

import com.group34.Model.Game.Player;

public class RoadEnd implements Road {
    Player player;

    public RoadEnd(Player player) {
        this.player = player;
    }

    @Override
    public RoadToken advance(RoadToken token, int distance) {
        player.reduceHealth(1);
        return token;
    }



}
