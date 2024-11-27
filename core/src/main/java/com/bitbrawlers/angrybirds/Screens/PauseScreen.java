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
import com.bitbrawlers.angrybirds.exceptions.ButtonInteractionException;

import java.io.Serializable;

import static com.bitbrawlers.angrybirds.Screens.Level1.loadGameState1;
import static com.bitbrawlers.angrybirds.Screens.Level2.loadGameState2;
import static com.bitbrawlers.angrybirds.Screens.Level3.loadGameState3;

public class PauseScreen implements Screen, Serializable {
    Vector2 touchPos; // Position of touch input
    private  OrthographicCamera camera; // Camera for rendering
    private  Texture mainMenu; // Texture for the Main Menu button
    private  Texture background; // Background texture for the pause screen
    private  Texture saveGame; // Texture for the Save Game button
    private  Texture resumeGame; // Texture for the Resume Game button
    private  Texture exitGame; // Texture for the Exit Game button
    private SpriteBatch spriteBatch; // SpriteBatch for rendering
    private AngryBirds game;

    // Constants for button dimensions
    private static final int BUTTON_WIDTH = 3;
    private static final int BUTTON_HEIGHT = 1;

    // Button positions
    private float saveGameY, resumeGameY, exitGameY, mainMenuY;
    private float buttonX; // X position for buttons

    private Sound buttonSound; // Sound effect for button clicks

    public PauseScreen(AngryBirds game) {
        this.game = game; // Store the reference to the AngryBirds instance
        camera = new OrthographicCamera(); // Create a camera
        camera.setToOrtho(false, 1920, 1080); // Set camera dimensions

        // Load textures for the pause screen background and buttons
        this.background = new Texture("Backgrounds/pause_background.png");
        this.mainMenu = new Texture("Buttons/mainmenu_button.png");
        this.saveGame = new Texture("Buttons/savegame_button.png");
        this.resumeGame = new Texture("Buttons/resumegame_button.png");
        this.exitGame = new Texture("Buttons/exitgame_button.png");

        buttonSound = Gdx.audio.newSound(Gdx.files.internal("Sounds/button.mp3")); // Load button sound
        this.spriteBatch = new SpriteBatch(); // Initialize SpriteBatch for drawing
        touchPos = new Vector2(); // Initialize touch position vector

        // Set button positions
        buttonX = 6; // Common X position for buttons
        saveGameY = 7; // Y position for Save Game button
        resumeGameY = 5; // Y position for Resume Game button
        mainMenuY = 3; // Y position for Main Menu button
        exitGameY = 1; // Y position for Exit Game button
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
            spriteBatch.draw(saveGame, buttonX, saveGameY, BUTTON_WIDTH, BUTTON_HEIGHT);
            spriteBatch.draw(mainMenu, buttonX, mainMenuY, BUTTON_WIDTH, BUTTON_HEIGHT);
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

                // Check if Save Game button is clicked
                if (touchPos.x > buttonX && touchPos.x < buttonX + BUTTON_WIDTH &&
                    touchPos.y > saveGameY && touchPos.y < saveGameY + BUTTON_HEIGHT) {
                    buttonSound.play();
                    System.out.println("pause save button");
                    //saveGameState();
                    game.setScreen(new MenuScreen(game));
                    dispose(); // Dispose of current resources
                }

                // Check if Main Menu button is clicked
                if (touchPos.x > buttonX && touchPos.x < buttonX + BUTTON_WIDTH &&
                    touchPos.y > mainMenuY && touchPos.y < mainMenuY + BUTTON_HEIGHT) {
                    buttonSound.play(); // Play sound effect
                    game.setScreen(new MenuScreen(game)); // Transition to the menu screen
                    dispose(); // Dispose of current resources
                }

                // Check if Resume Game button is clicked
                if (touchPos.x > buttonX && touchPos.x < buttonX + BUTTON_WIDTH &&
                    touchPos.y > resumeGameY && touchPos.y < resumeGameY + BUTTON_HEIGHT) {
                    buttonSound.play();
                    System.out.println("pause-loading");
                    if(StartScreen.levelCount == 1) {
                        System.out.println("pause-level1-loading");
                        loadGameState1(game);
                        System.out.println("pause-level1-loaded");
                        game.setScreen(new Level1(game));
                    }
                    else if(StartScreen.levelCount == 2) {
                        System.out.println("pause-level2-loading");
                        loadGameState2(game);
                        System.out.println("pause-level2-loaded");
                        game.setScreen(new Level2(game));
                    }
                    else if(StartScreen.levelCount == 3){
                        System.out.println("pause-level3-loading");
                        loadGameState3(game);
                        System.out.println("pause-level3-loaded");
                        game.setScreen(new Level3(game));
                    }
//                    //loadGameState1(game);
//                    game.setScreen(loadGameState1(game));
                    System.out.println("pause-loaded");

//                    if(StartScreen.levelCount == 1){
//                        loadGameState1(game);
//                        game.setScreen(new Level1(game));
//                    }
//                    else if(StartScreen.levelCount == 2)game.setScreen(new Level2(game));
//                    else if(StartScreen.levelCount == 3)game.setScreen(new Level3(game));


//                    //game.setScreen(new Level1(game));
//                    System.out.println("resume");
//                    Level1 savedState = Level1.loadGameState1(game);
//                    System.out.println("after save");
//                    if (savedState != null) {
//                        System.out.println("Game state loaded successfully!");  // Debug print
//                        game.setScreen(savedState);  // Set the screen with the loaded state
//                    } else {
//                        game.setScreen(new Level1(game));
//                        System.out.println("Error: Game state is null!");
//                        // Optionally, fall back to a new game instance
//                        game.setScreen(new Level1(game));
//                    }
                    dispose();
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
        saveGame.dispose();
        resumeGame.dispose();
        exitGame.dispose();
        mainMenu.dispose(); // Dispose of Main Menu button texture
        spriteBatch.dispose();
    }
}
