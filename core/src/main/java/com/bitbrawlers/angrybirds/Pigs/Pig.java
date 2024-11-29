package com.bitbrawlers.angrybirds.Pigs;

import com.badlogic.gdx.math.Vector2;

import java.io.Serializable;

public abstract class Pig implements Serializable {
    private int health;
    private final int size;
    private Vector2 position;
    private boolean isDestroyed;

    public Pig(int health, int size, float x, float y) {
        this.health = health;
        this.size = size;
        this.position = new Vector2(x, y);  // Using Vector2 for float positions
        this.isDestroyed = false;
    }

    public boolean isDestroyed() {
        return isDestroyed;
    }

    public void setDestroyed(boolean destroyed) {
        isDestroyed = destroyed;
    }

    public int getHealth() {
        return health;
    }

    public int getSize() {
        return size;
    }

    public Vector2 getPosition() {
        return position;
    }

    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }

    // Method to set the pig's position using float values
    public void setPosition(float x, float y) {
        this.position.set(x, y);  // Update position using Vector2
    }

    // Check if the pig is still alive based on its health
    public boolean isAlive() {
        return (health > 0);
    }

    // Method to reduce pig's health when hit, to be implemented in subclasses
    public void hit(double damage) {
        // Implement damage logic here
    }
}
