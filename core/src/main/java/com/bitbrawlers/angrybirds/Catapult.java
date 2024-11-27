package com.bitbrawlers.angrybirds;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import java.io.Serializable;

public class Catapult implements Serializable {
    private transient Texture catapultTexture; // Texture for the catapult image
    private Vector2 position; // Position of the catapult in the game world

    // Constructor to initialize the catapult with a position
    public Catapult(float x, float y) {
        this.catapultTexture = new Texture("Elements/catapult.png"); // Load the catapult texture
        this.position = new Vector2(x, y); // Set the initial position
    }

    // Getter for the catapult's texture
    public Texture getTexture() {
        return catapultTexture;
    }

    // Getter for the catapult's position
    public Vector2 getPosition() {
        return position;
    }

    // Setter to change the catapult's position
    public void setPosition(Vector2 position) {
        this.position = new Vector2(position.x, position.y);
    }

    // Method to dispose of the texture to free up resources
    public void dispose() {
        catapultTexture.dispose();
    }
}
