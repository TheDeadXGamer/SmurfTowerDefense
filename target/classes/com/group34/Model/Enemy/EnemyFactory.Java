package com.group34.Model.Enemy;
import java.awt.Point;

// Factory class that creates enemies based on class type
class EnemyFactory {
    // Create enemy of type T at a given position
    public static <T extends EnemyInterface> T createEnemy(Class<T> enemyClass, Point startPosition) {
        try {
            // Create a new instance of the enemy class
            T enemy = enemyClass.getDeclaredConstructor().newInstance();
            // Set the enemy's position
            enemy.setPosition(startPosition);
            return enemy;
            // Catch any exceptions that occur during instantiation
        } catch (Exception e) {
            throw new RuntimeException("Could not create enemy of type " + enemyClass.getName(), e);
        }
    }
}