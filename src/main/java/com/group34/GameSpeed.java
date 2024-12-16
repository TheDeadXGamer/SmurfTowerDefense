package com.group34;

public enum GameSpeed {
    SLOW(30), NORMAL(60), FAST(120);

    private final int speed;

    GameSpeed(int speed) {
        this.speed = speed;
    }

    public int getSpeed() {
        return speed;
    }
}
