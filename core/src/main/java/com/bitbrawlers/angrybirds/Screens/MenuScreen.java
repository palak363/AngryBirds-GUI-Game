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

import static com.bitbrawlers.angrybirds.Screens.Level1.loadGameState1;
import static com.bitbrawlers.angrybirds.Screens.Level2.loadGameState2;
import static com.bitbrawlers.angrybirds.Screens.Level3.loadGameState3;


public class MenuScreen implements Screen, Serializable {
    Vector2 touchPos; // Position of touch input
    private OrthographicCamera camera; // Camera for rendering
    private Texture background; // Background texture
    private Texture newGame; // Texture for the New Game button
    private Texture resumeGame; // Texture for the Resume Game button
    private Texture exitGame; // Texture for the Exit Game button
    private SpriteBatch spriteBatch; // SpriteBatch for rendering
    private AngryBirds game; // Main game object reference

    // Constants for button dimensions
    private static final int BUTTON_WIDTH = 3;
    private static final int BUTTON_HEIGHT = 1;

    // Positions for buttons
    private float newGameY, resumeGameY, exitGameY;
    private float buttonX; // X position for buttons

    private Sound buttonSound; // Sound effect for button clicks

    public MenuScreen(AngryBirds game) {
        this.game = game; // Store the reference to the AngryBirds instance
        camera = new OrthographicCamera(); // Create a camera
        camera.setToOrtho(false, 1920, 1080); // Set camera dimensions

        // Load textures for the menu background and buttons
        this.background = new Texture("Backgrounds/menu_background.png");
        this.newGame = new Texture("Buttons/newgame_button.png");
        this.resumeGame = new Texture("Buttons/resumegame_button.png");
        this.exitGame = new Texture("Buttons/exitgame_button.png");

        buttonSound = Gdx.audio.newSound(Gdx.files.internal("Sounds/button.mp3")); // Load button sound
        this.spriteBatch = new SpriteBatch(); // Initialize SpriteBatch for drawing
        touchPos = new Vector2(); // Initialize touch position vector

        // Set button positions
        buttonX = 6; // Common X position for buttons
        newGameY = 6; // Y position for New Game button
        resumeGameY = 4; // Y position for Resume Game button
        exitGameY = 2; // Y position for Exit Game button
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
            spriteBatch.draw(newGame, buttonX, newGameY, BUTTON_WIDTH, BUTTON_HEIGHT);
            spriteBatch.draw(resumeGame, buttonX, resumeGameY, BUTTON_WIDTH, BUTTON_HEIGHT);
            spriteBatch.draw(exitGame, buttonX, exitGameY, BUTTON_WIDTH, BUTTON_HEIGHT);
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

                // Check if New Game button is clicked
                if (touchPos.x > buttonX && touchPos.x < buttonX + BUTTON_WIDTH &&
                    touchPos.y > newGameY && touchPos.y < newGameY + BUTTON_HEIGHT) {
                    buttonSound.play(); // Play sound effect
                    game.setScreen(new SelectLevelScreen(game)); // Transition to the level selection screen
                    dispose(); // Dispose of current resources
                }

                // Check if Resume Game button is clicked
                if (touchPos.x > buttonX && touchPos.x < buttonX + BUTTON_WIDTH &&
                    touchPos.y > resumeGameY && touchPos.y < resumeGameY + BUTTON_HEIGHT) {
                    buttonSound.play(); // Play sound effect
                    System.out.println("main menu resume");

                    if(StartScreen.levelCount == 1) {
                        loadGameState1(game);
                        game.setScreen(new Level1(game));
                    }
                    else if(StartScreen.levelCount == 2) {
                        loadGameState2(game);
                        game.setScreen(new Level2(game));
                    }
                    else if(StartScreen.levelCount == 3){
                        loadGameState3(game);
                        game.setScreen(new Level3(game));
                    }

//                    loadGameState1(game);
//                    game.setScreen(new Level1(game));
                    System.out.println("menu-load-done");
                    //game.setScreen(new SelectLevelScreen(game));
//                    System.out.println("resume");
//                    Level1 savedState = Level1.loadGameState1(game);
//                    System.out.println("after save");
//                    if (savedState != null) {
//                        System.out.println("Game state loaded successfully!");  // Debug print
//                        game.setScreen(savedState);  // Set the screen with the loaded state
//                    } else {
//                        System.out.println("Error: Game state is null!");
//                        // Optionally, fall back to a new game instance
//                        game.setScreen(new Level1(game));
//                    }
    //                else if(StartScreen.levelCount == 2) game.setScreen(new SavedLevel2(game));
    //                else if(StartScreen.levelCount == 3) game.setScreen(new SavedLevel3(game));

                    dispose(); // Dispose of current resources
                }

                // Check if Exit Game button is clicked
                if (touchPos.x > buttonX && touchPos.x < buttonX + BUTTON_WIDTH &&
                    touchPos.y > exitGameY && touchPos.y < exitGameY + BUTTON_HEIGHT) {
                    buttonSound.play(); // Play sound effect
                    dispose(); // Dispose of current resources
                    Gdx.app.exit(); // Exit the application
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
        newGame.dispose();
        resumeGame.dispose();
        exitGame.dispose();
        spriteBatch.dispose();
    }
}
