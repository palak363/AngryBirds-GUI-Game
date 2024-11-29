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
    // Static instance for Singleton - DESIGN PATTERN
    private static MenuScreen instance;

    Vector2 touchPos;
    private OrthographicCamera camera;
    private Texture background;
    private Texture newGame;
    private Texture resumeGame;
    private Texture exitGame;
    private SpriteBatch spriteBatch;
    private AngryBirds game;
    private static final int BUTTON_WIDTH = 3;
    private static final int BUTTON_HEIGHT = 1;
    private float newGameY, resumeGameY, exitGameY;
    private float buttonX;
    private Sound buttonSound;

    public MenuScreen(AngryBirds game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1920, 1080);
        this.background = new Texture("Backgrounds/menu_background.png");
        this.newGame = new Texture("Buttons/newgame_button.png");
        this.resumeGame = new Texture("Buttons/resumegame_button.png");
        this.exitGame = new Texture("Buttons/exitgame_button.png");

        buttonSound = Gdx.audio.newSound(Gdx.files.internal("Sounds/button.mp3"));
        this.spriteBatch = new SpriteBatch();
        touchPos = new Vector2();

        buttonX = 6;
        newGameY = 6;
        resumeGameY = 4;
        exitGameY = 2;
    }

    public static MenuScreen getInstance(AngryBirds game) {
        if (instance == null) {
            instance = new MenuScreen(game);
        }
        return instance;
    }

    @Override
    public void render(float delta) {

        try {
            ScreenUtils.clear(0, 0, 0, 1);
            game.viewport.apply();
            spriteBatch.setProjectionMatrix(game.viewport.getCamera().combined);
            spriteBatch.begin();
            spriteBatch.draw(background, 0, 0, game.viewport.getWorldWidth(), game.viewport.getWorldHeight());
            spriteBatch.draw(newGame, buttonX, newGameY, BUTTON_WIDTH, BUTTON_HEIGHT);
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

                //select level
                if (touchPos.x > buttonX && touchPos.x < buttonX + BUTTON_WIDTH &&
                    touchPos.y > newGameY && touchPos.y < newGameY + BUTTON_HEIGHT) {
                    buttonSound.play();
                    game.setScreen(new SelectLevelScreen(game));
                    dispose();
                }

                // Check if Resume Game button is clicked
                if (touchPos.x > buttonX && touchPos.x < buttonX + BUTTON_WIDTH &&
                    touchPos.y > resumeGameY && touchPos.y < resumeGameY + BUTTON_HEIGHT) {
                    buttonSound.play();
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
