package com.bitbrawlers.angrybirds.Pigs;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import java.io.Serializable;
import java.util.Vector;

public class MediumPig extends Pig implements Serializable {
    private transient Texture pigTexture;
    private Vector2 position;  // Use Vector2 for position

    public MediumPig(int health, float x, float y) {
        super(health, 2, x, y);  // Pass x and y to the superclass
        this.pigTexture = new Texture("Elements/Pigs/mediumPig.png");
        this.position = new Vector2(x, y);  // Store position as Vector2
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

    // Method to set the position of the MediumPig
    public void setPosition(Vector2 position) {
        this.position = new Vector2(position.x,position.y);  // Update position using Vector2
    }

    @Override
    public void hit(double damage) {
        // Implement logic for when the pig is hit by a bird
    }

    public void setPigTexture(Texture pigTexture) {
        this.pigTexture = pigTexture;
    }

    // Clean up resources by disposing of the pig's texture
    public void dispose() {
        pigTexture.dispose();
    }
}
