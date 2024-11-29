package com.bitbrawlers.angrybirds.Birds;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import java.io.Serializable;

public class PinkBird extends Bird implements Serializable {
    private static final float GROUND_LEVEL = 1.3F;
    private transient Texture birdTexture;
    private Vector2 position;
    private Vector2 velocity; // Bird velocity
    private final float gravity = 9.8f; // Gravity (negative value to pull downward)

    public PinkBird(float x, float y) {
        super("Pink", 2);
        this.birdTexture = new Texture("Elements/Birds/pinkBird.png");  // Load bird image
        this.position = new Vector2(x, y);

        this.velocity = new Vector2(0, 0);
    }

    // Update method for moving the bird based on physics
    public void update(float deltaTime) {
        this.position.x += velocity.x * deltaTime;
        this.position.y += velocity.y * deltaTime;
        this.velocity.y -= gravity * deltaTime;
        if (this.position.y < GROUND_LEVEL) {
            this.position.y = GROUND_LEVEL;
            this.velocity.y = 0;
            this.isLaunched = false; // Stop movement when it hits the ground
        }
    }

    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }

    public void launch(float speed, float angle) {
        // Convert angle from degrees to radians
        float radians = (float) Math.toRadians(angle);

        // Calculate velocity components
        this.velocity.x = (float) (speed * Math.cos(radians));
        this.velocity.y = (float) (speed * Math.sin(radians));
        System.out.println("Launch Speed: " + speed + ", Angle: " + angle + "°");
        System.out.println("Initial Velocity: (" + velocity.x + ", " + velocity.y + ")");

        // Set bird as "in motion"
        this.isLaunched = true;
    }


    public Texture getTexture() {
        return birdTexture;
    }


    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(float x, float y) {
        this.position.set(x, y);
    }

    // Special ability (explosion) of the black bird
    void specialAbility() {}

    //Cleanup
    public void dispose() {
        birdTexture.dispose();
    }

    public void setBirdTexture(Texture birdTexture) {
        this.birdTexture = birdTexture;
    }
}
