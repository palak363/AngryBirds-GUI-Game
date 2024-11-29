package com.bitbrawlers.angrybirds.Pigs;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import java.io.Serializable;

public class KingPig extends Pig implements Serializable {
    private transient Texture pigTexture;
    private Vector2 position;  // Use Vector2 for position

    public KingPig(int health, float x, float y) {
        super(health, 4, x, y);  // Pass position as float x, y to the superclass
        this.pigTexture = new Texture("Elements/Pigs/kingPig.png");
        this.position = new Vector2(x, y);  // Store the position as a Vector2
    }

    public Texture getTexture() {
        return pigTexture;
    }

    public float getX() {
        return position.x;  // Get x from Vector2
    }

    public float getY() {
        return position.y;  // Get y from Vector2
    }

    // Method to set the position of the KingPig
    public void setPosition(float x, float y) {
        this.position.set(x, y);  // Update position using Vector2
    }

    @Override
    public void hit(double damage) {
        // Implement logic when the pig is hit by a bird
    }

    // Clean up resources by disposing of the pig's texture
    public void dispose() {
        pigTexture.dispose();
    }
}
