package com.bitbrawlers.angrybirds.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.bitbrawlers.angrybirds.AngryBirds;

import com.bitbrawlers.angrybirds.exceptions.UnableToRenderException;
import com.bitbrawlers.angrybirds.exceptions.UnableToStartGameException;

import java.io.Serializable;

public class SelectLevelScreen implements Screen, Serializable {
    private Texture background; // Background texture for the level selection screen
    private Texture back; // Texture for the Back button
    private Texture level1; // Texture for Level 1 button
    private Texture level2; // Texture for Level 2 button
    private Texture level3; // Texture for Level 3 button
    private SpriteBatch spriteBatch; // SpriteBatch for rendering
    private AngryBirds game; // Reference to the main game object

    private static final int BUTTON_WIDTH = 3; // Button width
    private static final int BUTTON_HEIGHT = 3; // Button height

    // Button positions
    private float level1X, level2X, level3X;
    private float buttonY; // Common Y position for buttons

    private Sound buttonSound; // Sound effect for button clicks

    private OrthographicCamera camera; // Camera for rendering
    private Vector2 touchPos; // Position of touch input

    public SelectLevelScreen(AngryBirds game) {
        this.game = game; // Store the reference to the AngryBirds instance
        camera = new OrthographicCamera(); // Create a camera
        camera.setToOrtho(false, 1920, 1080); // Set camera dimensions

        // Load textures for the level selection background and buttons
        this.background = new Texture("Backgrounds/level_background.png");
        this.back = new Texture("Buttons/back_button.png");
        this.level1 = new Texture("Buttons/level1_button.png");
        this.level2 = new Texture("Buttons/level2_button.png");
        this.level3 = new Texture("Buttons/level3_button.png");

        buttonSound = Gdx.audio.newSound(Gdx.files.internal("Sounds/button.mp3")); // Load button sound
        this.spriteBatch = new SpriteBatch(); // Initialize SpriteBatch for drawing

        touchPos = new Vector2(); // Initialize touch position vector

        // Set button positions
        buttonY = 3; // Common Y position for buttons
        level1X = 2.5F; // X position for Level 1 button
        level2X = 6.5F; // X position for Level 2 button
        level3X = 10.5F; // X position for Level 3 button
    }

    @Override
    public void render(float delta) {

        try {
            ScreenUtils.clear(0, 0, 0, 1); // Clear the screen with a black color
            game.viewport.apply(); // Apply the viewport settings
            spriteBatch.setProjectionMatrix(game.viewport.getCamera().combined); // Set the projection matrix

            // Begin drawing
            spriteBatch.begin();
            // Draw the background and buttons
            spriteBatch.draw(background, 0, 0, game.viewport.getWorldWidth(), game.viewport.getWorldHeight());
            spriteBatch.draw(back, 0, game.viewport.getWorldHeight() - BUTTON_HEIGHT + 2, BUTTON_WIDTH - 2, BUTTON_HEIGHT - 2);
            spriteBatch.draw(level1, level1X - 0.4F, buttonY, BUTTON_WIDTH + 0.2F, BUTTON_HEIGHT - 0.2F);
            spriteBatch.draw(level2, level2X, buttonY, BUTTON_WIDTH, BUTTON_HEIGHT);
            spriteBatch.draw(level3, level3X, buttonY, BUTTON_WIDTH, BUTTON_HEIGHT);
            spriteBatch.end(); // End the drawing process

        }
        catch (Exception e)
        {
            throw new UnableToRenderException("Failed to render the screen!", e);
        }

        try
        {

            // Handle touch input for button interactions
            if (Gdx.input.justTouched()) {
                touchPos.set(Gdx.input.getX(), Gdx.input.getY()); // Get the touch position
                game.viewport.unproject(touchPos); // Convert from screen to world coordinates

                // Back button click
                if (touchPos.x > 0 && touchPos.x < BUTTON_WIDTH &&
                    touchPos.y > game.viewport.getWorldHeight() - BUTTON_HEIGHT && touchPos.y < game.viewport.getWorldHeight()) {
                    buttonSound.play(); // Play sound effect
                    game.setScreen(new MenuScreen(game)); // Transition to the menu screen
                    dispose(); // Dispose of current resources
                }

                // Level 1 button click
                if (touchPos.x > level1X && touchPos.x < level1X + BUTTON_WIDTH &&
                    touchPos.y > buttonY && touchPos.y < buttonY + BUTTON_HEIGHT) {
                    buttonSound.play(); // Play sound effect
                    game.setScreen(new Level1(game)); // Transition to Level 1 game screen
                    dispose(); // Dispose of current resources
                }

                // Level 2 button click
                if (touchPos.x > level2X && touchPos.x < level2X + BUTTON_WIDTH &&
                    touchPos.y > buttonY && touchPos.y < buttonY + BUTTON_HEIGHT) {
                    buttonSound.play(); // Play sound effect
                    game.setScreen(new Level2(game)); // Transition to Level 2 game screen
                    dispose(); // Dispose of current resources
                }

                // Level 3 button click
                if (touchPos.x > level3X && touchPos.x < level3X + BUTTON_WIDTH &&
                    touchPos.y > buttonY && touchPos.y < buttonY + BUTTON_HEIGHT) {
                    buttonSound.play(); // Play sound effect
                    game.setScreen(new Level3(game)); // Transition to Level 3 game screen
                    dispose(); // Dispose of current resources
                }
            }
        }
        catch (Exception e)
        {
            throw new UnableToStartGameException("Error starting game!",e);
        }
    }

    @Override
    public void resize(int width, int height) {
        game.viewport.update(width, height); // Update the viewport dimensions
    }

    @Override
    public void show() {} // Method required by Screen interface

    @Override
    public void hide() {} // Method required by Screen interface

    @Override
    public void pause() {} // Method required by Screen interface

    @Override
    public void resume() {} // Method required by Screen interface

    @Override
    public void dispose() {
        // Dispose of textures and resources
        background.dispose();
        level1.dispose();
        level2.dispose();
        level3.dispose();
        back.dispose(); // Dispose of Back button texture
        spriteBatch.dispose();
    }
}
