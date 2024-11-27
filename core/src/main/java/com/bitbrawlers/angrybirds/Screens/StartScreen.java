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

public class StartScreen implements Screen, Serializable {
    Vector2 touchPos; // Position of touch input
    private OrthographicCamera camera; // Camera for rendering
    private Texture background; // Background texture for the start screen
    private Texture playButton; // Texture for the Play button
    private SpriteBatch spriteBatch; // SpriteBatch for rendering
    private AngryBirds game; // Reference to the main game object
    private float buttonX, buttonY; // Positions for the button
    private static final int BUTTON_WIDTH = 3; // Width of the button
    private static final int BUTTON_HEIGHT = 1; // Height of the button
    private Sound buttonSound; // Sound effect for button clicks
    private Sound background_sound; // Sound effect for background music
    public static int levelCount = 1;

    public StartScreen(AngryBirds game) {
        this.game = game; // Store reference to the AngryBirds instance
        camera = new OrthographicCamera(); // Create a camera
        camera.setToOrtho(false, 1920, 1080); // Set camera dimensions

        // Load textures for the background and button
        this.background = new Texture("Backgrounds/start_background.png");
        this.playButton = new Texture("Buttons/start_button.png");
        this.spriteBatch = new SpriteBatch(); // Initialize SpriteBatch for drawing
        this.touchPos = new Vector2(); // Initialize touch position vector

        // Set button position
        buttonX = 6.25F;
        buttonY = 0.5F;

        // Load sound effects
        buttonSound = Gdx.audio.newSound(Gdx.files.internal("Sounds/button.mp3"));
        background_sound = Gdx.audio.newSound(Gdx.files.internal("Sounds/angrybirds.mp3"));
    }

    @Override
    public void render(float delta) {

        try {
            // Clear the screen with a black color
            ScreenUtils.clear(0, 0, 0, 1);
            game.viewport.apply(); // Apply the viewport settings
            spriteBatch.setProjectionMatrix(game.viewport.getCamera().combined); // Set the projection matrix

            // Begin drawing
            spriteBatch.begin();
            // Draw the background and play button
            spriteBatch.draw(background, 0, 0, game.viewport.getWorldWidth(), game.viewport.getWorldHeight());
            spriteBatch.draw(playButton, buttonX, buttonY, BUTTON_WIDTH, BUTTON_HEIGHT);
            spriteBatch.end(); // End the drawing process
        }
        catch (Exception e)
        {
            throw new UnableToRenderException("Failed to render the screen!", e);
        }

        try
        {
            // Handle touch input
            if (Gdx.input.justTouched()) {
                touchPos.set(Gdx.input.getX(), Gdx.input.getY()); // Get the touch position
                game.viewport.unproject(touchPos); // Convert from screen to world coordinates

                // Check if the Play button is clicked
                if (touchPos.x > buttonX && touchPos.x < buttonX + BUTTON_WIDTH &&
                    touchPos.y > buttonY && touchPos.y < buttonY + BUTTON_HEIGHT) {
                    buttonSound.play(); // Play button sound
                    game.setScreen(new MenuScreen(game)); // Transition to the menu screen
                    dispose(); // Dispose of current resources
                }
            }
        }
        catch (Exception e)
        {
            throw new UnableToStartGameException("Error processing button input!",e);
        }

    }

    @Override
    public void resize(int width, int height) {
        game.viewport.update(width, height); // Update the viewport dimensions
    }

    @Override
    public void show() {
        background_sound.loop(); // Start looping the background music
    }

    @Override
    public void hide() {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void dispose() {
        // Dispose of textures and resources
        background.dispose();
        playButton.dispose();
        spriteBatch.dispose();
    }
}
