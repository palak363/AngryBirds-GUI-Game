package com.bitbrawlers.angrybirds.Birds;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import java.io.Serializable;

public class BlueBird extends Bird implements Serializable {
    private static final float GROUND_LEVEL = 1.3F;
    private transient Texture birdTexture;
    private Vector2 position;
    private Vector2 velocity; // Bird velocity
    private final float gravity = 9.8f; // Gravity (negative value to pull downward)

    public BlueBird(float x, float y) {
        super("Blue", 2);
        this.birdTexture = new Texture("Elements/Birds/blueBird.png");  // Load bird image
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

    public void calculateLaunch(Vector2 dragStartPos, Vector2 dragEndPos,boolean isBirdLaunched) {
        if (dragStartPos != null && dragEndPos != null) {
            System.out.println("----"+dragStartPos.x);
            System.out.println("----"+dragStartPos.y);
            // Use actual drag start and end positions
            float x1 = 839.0F;
            float y1 = 919.0F;
            float x2 = dragEndPos.x;
            float y2 = dragEndPos.y;

            System.out.println("-----");
            System.out.println(x1);
            System.out.println(y1);
            System.out.println(x2);
            System.out.println(y2);
            System.out.println("------");

            // Calculate differences in X and Y
            float deltaX = x1 - x2;
            float deltaY = y1 - y2;

            // Calculate the launch angle (in radians)
            float angle = (float) Math.atan2(deltaY, deltaX); // Angle in radians

            // Convert angle to degrees for easier interpretation
            float launchAngle = angle * (180.0f / (float) Math.PI);

            // Calculate the launch speed (length of the drag vector)
            float launchSpeed = (float) Math.sqrt(deltaX * deltaX + deltaY * deltaY);

            // Scale the speed to match game physics (optional)
            launchSpeed *= 0.1f; // Example scaling factor; adjust based on your physics needs

            // Launch the bird
            launch(launchSpeed, launchAngle); // Pass speed and angle to bird object
            isBirdLaunched = true;

            // Reset drag positions
            dragStartPos = null;
            dragEndPos = null;
        }
    }public void setBirdTexture(Texture birdTexture) {
        this.birdTexture = birdTexture;
    }
}
