package com.group34.Model.Road;

import com.group34.Model.Game.Player;

public class RoadEnd implements Road {
    Player player;

    public RoadEnd(Player player) {
        this.player = player;
    }

    @Override
    public void advance(RoadToken token, int distance) {
        player.reduceHealth(1);
    }



}
