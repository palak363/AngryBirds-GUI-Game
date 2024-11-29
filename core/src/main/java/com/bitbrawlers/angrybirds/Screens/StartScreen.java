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
    private Texture background;
    private Texture playButton;
    private SpriteBatch spriteBatch;
    private AngryBirds game;
    private float buttonX, buttonY;
    private static final int BUTTON_WIDTH = 3;
    private static final int BUTTON_HEIGHT = 1;
    private Sound buttonSound;
    private Sound background_sound;
    public static int levelCount = 1;

    public StartScreen(AngryBirds game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1920, 1080);

        this.background = new Texture("Backgrounds/start_background.png");
        this.playButton = new Texture("Buttons/start_button.png");
        this.spriteBatch = new SpriteBatch();
        this.touchPos = new Vector2();

        buttonX = 6.25F;
        buttonY = 0.5F;

        buttonSound = Gdx.audio.newSound(Gdx.files.internal("Sounds/button.mp3"));
        background_sound = Gdx.audio.newSound(Gdx.files.internal("Sounds/angrybirds.mp3"));
    }

    @Override
    public void render(float delta) {

        try {
            ScreenUtils.clear(0, 0, 0, 1);
            game.viewport.apply(); // Apply the viewport settings
            spriteBatch.setProjectionMatrix(game.viewport.getCamera().combined); // Set the projection matrix

            spriteBatch.begin();
            spriteBatch.draw(background, 0, 0, game.viewport.getWorldWidth(), game.viewport.getWorldHeight());
            spriteBatch.draw(playButton, buttonX, buttonY, BUTTON_WIDTH, BUTTON_HEIGHT);
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

                if (touchPos.x > buttonX && touchPos.x < buttonX + BUTTON_WIDTH &&
                    touchPos.y > buttonY && touchPos.y < buttonY + BUTTON_HEIGHT) {
                    buttonSound.play();
                    //SINGLETON
                    game.setScreen(MenuScreen.getInstance(game));
                    //game.setScreen(new MenuScreen(game)); // Transition to the menu screen
                    dispose();

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
        game.viewport.update(width, height);
    }

    @Override
    public void show() {
        background_sound.loop();
    }

    @Override
    public void hide() {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void dispose() {
        background.dispose();
        playButton.dispose();
        spriteBatch.dispose();
    }
}
