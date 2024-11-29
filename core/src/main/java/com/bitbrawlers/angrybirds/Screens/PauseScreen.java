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
    Vector2 touchPos;
    private  OrthographicCamera camera;
    private  Texture mainMenu;
    private  Texture background;
    private  Texture saveGame;
    private  Texture resumeGame;
    private  Texture exitGame;
    private SpriteBatch spriteBatch;
    private AngryBirds game;
    public int resumeLevel1 = 0;
    private static final int BUTTON_WIDTH = 3;
    private static final int BUTTON_HEIGHT = 1;
    private float saveGameY, resumeGameY, exitGameY, mainMenuY;
    private float buttonX;
    private Sound buttonSound;

    public PauseScreen(AngryBirds game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1920, 1080);
        this.background = new Texture("Backgrounds/pause_background.png");
        this.mainMenu = new Texture("Buttons/mainmenu_button.png");
        this.saveGame = new Texture("Buttons/savegame_button.png");
        this.resumeGame = new Texture("Buttons/resumegame_button.png");
        this.exitGame = new Texture("Buttons/exitgame_button.png");

        buttonSound = Gdx.audio.newSound(Gdx.files.internal("Sounds/button.mp3"));
        this.spriteBatch = new SpriteBatch();
        touchPos = new Vector2();
        buttonX = 6;
        saveGameY = 7;
        resumeGameY = 5;
        mainMenuY = 3;
        exitGameY = 1;
    }

    @Override
    public void render(float delta) {

        try {
            ScreenUtils.clear(0, 0, 0, 1);
            game.viewport.apply();
            spriteBatch.setProjectionMatrix(game.viewport.getCamera().combined);
            spriteBatch.begin();
            spriteBatch.draw(background, 0, 0, game.viewport.getWorldWidth(), game.viewport.getWorldHeight());
            spriteBatch.draw(saveGame, buttonX, saveGameY, BUTTON_WIDTH, BUTTON_HEIGHT);
            spriteBatch.draw(mainMenu, buttonX, mainMenuY, BUTTON_WIDTH, BUTTON_HEIGHT);
            spriteBatch.draw(resumeGame, buttonX, resumeGameY, BUTTON_WIDTH, BUTTON_HEIGHT);
            spriteBatch.draw(exitGame, buttonX, exitGameY, BUTTON_WIDTH, BUTTON_HEIGHT);
            spriteBatch.end();
        }
        catch (Exception e)
        {
            throw new UnableToRenderException("Failed to render the screen!", e);
        }
        try
        {
            if (Gdx.input.justTouched()) {
                touchPos.set(Gdx.input.getX(), Gdx.input.getY());
                game.viewport.unproject(touchPos);

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
                        resumeLevel1 = 1;
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
        game.viewport.update(width, height);
    }

    @Override
    public void show() {}

    @Override
    public void hide() {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void dispose() {
        background.dispose();
        saveGame.dispose();
        resumeGame.dispose();
        exitGame.dispose();
        mainMenu.dispose();
        spriteBatch.dispose();
    }
}
