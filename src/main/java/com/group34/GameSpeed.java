package com.group34;

public enum GameSpeed {
    SLOW(30), NORMAL(60), FAST(120);

    private final int speed;

    // Static field to store the current speed
    private static GameSpeed currentSpeed = NORMAL; // Default value

    GameSpeed(int speed) {
        this.speed = speed;
    }

    public int getSpeed() {
        return speed;
    }

    // Setter for current speed
    public static void setCurrentSpeed(GameSpeed speed) {
        currentSpeed = speed;
    }

    // Getter for current speed
    public static int getCurrentSpeed() {
        return currentSpeed.speed;
    }
}
