package com.group34.Model;

import com.group34.Model.Game.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

    private Player player;

    @BeforeEach
    public void setup() {
        player = new Player(100);
    }

    @Test
    public void testKillablePlayer() {
        assertTrue(player.isAlive(), "Player should be alive");
        player.reduceHealth(player.getHealth());
        assertFalse(player.isAlive(), "Player should be dead");
    }
}
