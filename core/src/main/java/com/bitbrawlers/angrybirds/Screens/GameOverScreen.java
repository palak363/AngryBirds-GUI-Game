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

public class GameOverScreen implements Screen, Serializable {
    private Texture background;
    private Texture playAgain;
    private Texture mainMenu;
    private Texture exit;
    private SpriteBatch spriteBatch;
    private AngryBirds game;

    private static final int BUTTON_WIDTH = 2;
    private static final int BUTTON_HEIGHT = 1;

    private float playAgainX, mainMenuX, exitX;
    private float buttonY;

    private Sound buttonSound;

    private OrthographicCamera camera;
    private Vector2 touchPos;

    public GameOverScreen(AngryBirds game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1920, 1080); // Set camera dimensions

        this.background = new Texture("Backgrounds/gameover_background.png");
        this.playAgain = new Texture("Buttons/playagain_button.png");
        this.mainMenu = new Texture("Buttons/mainMenu_button.png");
        this.exit = new Texture("Buttons/exitgame_button.png");

        buttonSound = Gdx.audio.newSound(Gdx.files.internal("Sounds/button.mp3")); // Load button sound
        this.spriteBatch = new SpriteBatch();

        touchPos = new Vector2();

        buttonY = 5;
        playAgainX = 4;
        mainMenuX = 7;
        exitX = 10;
    }

    @Override
    public void render(float delta) {
        try
        {
            ScreenUtils.clear(0, 0, 0, 1);
            game.viewport.apply();
            spriteBatch.setProjectionMatrix(game.viewport.getCamera().combined);

            spriteBatch.begin();
            spriteBatch.draw(background, 0, 0, game.viewport.getWorldWidth(), game.viewport.getWorldHeight());
            spriteBatch.draw(playAgain, playAgainX, buttonY, BUTTON_WIDTH, BUTTON_HEIGHT);
            spriteBatch.draw(mainMenu, mainMenuX, buttonY, BUTTON_WIDTH, BUTTON_HEIGHT);
            spriteBatch.draw(exit, exitX, buttonY, BUTTON_WIDTH, BUTTON_HEIGHT);

            spriteBatch.end();
        }
        catch (Exception e)
        {
            throw new UnableToRenderException("Failed to render the screen!", e);
        }

        // Handle touch input
        if (Gdx.input.justTouched()) {
            touchPos.set(Gdx.input.getX(), Gdx.input.getY());
            game.viewport.unproject(touchPos);

            try
            {
                // Play Again button click
                if (touchPos.x > playAgainX && touchPos.x < playAgainX + BUTTON_WIDTH &&
                    touchPos.y > buttonY && touchPos.y < buttonY + BUTTON_HEIGHT) {
                    buttonSound.play();
                    if(StartScreen.levelCount == 1) game.setScreen(new Level1(game));
                    if(StartScreen.levelCount == 2) game.setScreen(new Level2(game));
                    if(StartScreen.levelCount == 3) game.setScreen(new Level3(game));

                    dispose();
                }

                // Main Menu button click
                if (touchPos.x > mainMenuX && touchPos.x < mainMenuX + BUTTON_WIDTH &&
                    touchPos.y > buttonY && touchPos.y < buttonY + BUTTON_HEIGHT) {
                    buttonSound.play();
                    game.setScreen(new MenuScreen(game));
                    dispose();
                }

                // Exit button click
                if (touchPos.x > exitX && touchPos.x < exitX + BUTTON_WIDTH &&
                    touchPos.y > buttonY && touchPos.y < buttonY + BUTTON_HEIGHT) {
                    buttonSound.play();
                    dispose();
                    Gdx.app.exit();
                }
            }
            catch (Exception e)
            {
                throw new ButtonInteractionException("Error processing button input!", e);
            }
        }
    }

    @Override
    public void resize(int width, int height) {
        game.viewport.update(width, height); // Update viewport on resize
    }

    @Override
    public void show() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
        background.dispose();
        playAgain.dispose();
        mainMenu.dispose();
        exit.dispose();
        spriteBatch.dispose();
    }
}
