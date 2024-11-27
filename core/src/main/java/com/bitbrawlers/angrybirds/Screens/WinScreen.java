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

import com.bitbrawlers.angrybirds.exceptions.ButtonInteractionException;
import com.bitbrawlers.angrybirds.exceptions.UnableToRenderException;

import java.io.Serializable;

public class WinScreen implements Screen, Serializable {
    private Texture background; // Background texture for the win screen
    private Texture tntBackground;
    private Texture mainMenu; // Texture for the Main Menu button
    private Texture exit; // Texture for the Exit button
    private SpriteBatch spriteBatch; // SpriteBatch for rendering textures
    private AngryBirds game; // Reference to the main AngryBirds game object

    private static final int BUTTON_WIDTH = 2; // Width of the buttons
    private static final int BUTTON_HEIGHT = 1; // Height of the buttons

    private float mainMenuX, exitX; // X coordinates for buttons
    private float buttonY; // Y coordinate for buttons

    private Sound buttonSound; // Sound effect for button clicks

    private OrthographicCamera camera; // Camera for rendering
    private Vector2 touchPos; // Position of touch input

    public WinScreen(AngryBirds game) {
        this.game = game; // Store reference to AngryBirds instance
        camera = new OrthographicCamera(); // Create a new camera
        camera.setToOrtho(false, 1920, 1080); // Set camera dimensions

        // Load textures for the background and buttons
        this.background = new Texture("Backgrounds/win_background.png");
        this.tntBackground = new Texture("Backgrounds/blast.png");
        this.mainMenu = new Texture("Buttons/mainmenu_button.png");
        this.exit = new Texture("Buttons/exitgame_button.png");
        buttonSound = Gdx.audio.newSound(Gdx.files.internal("Sounds/button.mp3")); // Load button sound effect
        this.spriteBatch = new SpriteBatch(); // Initialize SpriteBatch for drawing

        touchPos = new Vector2(); // Initialize touch position vector

        buttonY = 5; // Set button Y position
        mainMenuX = 5; // Set Main Menu button X position
        exitX = 9; // Set Exit button X position
    }

    @Override
    public void render(float delta) {
        try {
            ScreenUtils.clear(0, 0, 0, 1); // Clear the screen with a black color
            game.viewport.apply(); // Apply the viewport settings
            spriteBatch.setProjectionMatrix(game.viewport.getCamera().combined); // Set projection matrix

            spriteBatch.begin(); // Begin the drawing process
            // Draw the background and buttons
            if (Level3.tntBlast == 1) {
                spriteBatch.draw(tntBackground, 0, 0, game.viewport.getWorldWidth(), game.viewport.getWorldHeight());
            } else {
                spriteBatch.draw(background, 0, 0, game.viewport.getWorldWidth(), game.viewport.getWorldHeight());
            }
            spriteBatch.draw(mainMenu, mainMenuX, buttonY, BUTTON_WIDTH, BUTTON_HEIGHT);
            spriteBatch.draw(exit, exitX, buttonY, BUTTON_WIDTH, BUTTON_HEIGHT);
            spriteBatch.end(); // End the drawing process
        }
        catch (RuntimeException e)
        {
            throw new UnableToRenderException("Failed to render the screen!", e);
        }

        try
        {
            // Handle touch input
            if (Gdx.input.justTouched()) {
                touchPos.set(Gdx.input.getX(), Gdx.input.getY()); // Get touch position
                game.viewport.unproject(touchPos); // Convert screen to world coordinates

                // Main Menu button click
                if (touchPos.x > mainMenuX && touchPos.x < mainMenuX + BUTTON_WIDTH &&
                    touchPos.y > buttonY && touchPos.y < buttonY + BUTTON_HEIGHT) {
                    buttonSound.play(); // Play button click sound
                    game.setScreen(new SelectLevelScreen(game)); // Transition to the Select Level screen
                    dispose(); // Dispose of current resources
                }

                // Exit button click
                if (touchPos.x > exitX && touchPos.x < exitX + BUTTON_WIDTH &&
                    touchPos.y > buttonY && touchPos.y < buttonY + BUTTON_HEIGHT) {
                    buttonSound.play(); // Play button click sound
                    dispose(); // Dispose of current resources
                    Gdx.app.exit(); // Exit the application
                }
            }
        }

        catch (Exception e)
        {
            throw new ButtonInteractionException("Error processing button input!",e);
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
        mainMenu.dispose();
        exit.dispose();
        spriteBatch.dispose();
    }
}
