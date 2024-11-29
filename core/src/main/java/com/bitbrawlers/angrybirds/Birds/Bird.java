package com.bitbrawlers.angrybirds.Birds;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import java.io.Serializable;

public abstract class Bird implements Serializable {
    static final float GROUND_LEVEL = 1.3F;
    static final float gravity = 9.8f;
    private final String colour;
    protected boolean isLaunched;
    private double damage;
    private Vector2 velocity;
    private Vector2 position;

    public Bird(String colour, double damage) {
        this.colour = colour;
        this.damage = damage;
        this.isLaunched = false;
        this.velocity = new Vector2(0, 0);
        this.position = new Vector2(0,0);
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }


    public String getColour() {
        return colour;
    }

    public double getDamage() {
        return damage;
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public void setVelocity(float x,float y) {
        this.velocity = new Vector2(x,y);
    }

    // Method to launch the bird with a specified speed
    void launch(double speed) {}

//    public void launch(float x, float y) {};

    // Method to define the bird's special ability (to be overridden by subclasses)
    void specialAbility() {}

    public boolean isMoving() {
        // Example logic to check if bird is still moving
        return Math.abs(velocity.x) > 0.1f || Math.abs(velocity.y) > 0.1f;
    }
//    public void update(float deltaTime) {
//    }

    public boolean launched()
    {
        return this.isLaunched;
    }

    public void dispose() {
    }

//    public Texture getTexture() {
//        return null;
//    }

    public boolean isStopped() {
        // Check if velocity is zero or near zero
        return Math.abs(velocity.x) < 0.1f && Math.abs(velocity.y) < 0.1f;
    }

    public void setPosition(float v, float v1) {
        position.set(v, v1);
    }

    public void update(float deltaTime) {
        this.position.x += velocity.x * deltaTime;
        this.position.y += velocity.y * deltaTime;
        this.velocity.y -= gravity * deltaTime;
        if (this.position.y <= GROUND_LEVEL) {
            this.position.y = GROUND_LEVEL;
            this.velocity.y = 0;
            //this.velocity.x = 0;
            this.isLaunched = false; // Stop movement when it hits the ground

        }
    }
    public void launch(float speed, float angle) {
        float radians = (float) Math.toRadians(angle);
        this.velocity.x = (float) (speed * Math.cos(radians));
        this.velocity.y = (float) (speed * Math.sin(radians));
        System.out.println("Launch Velocity: " + this.velocity);
        this.isLaunched = true;

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
    }


}
