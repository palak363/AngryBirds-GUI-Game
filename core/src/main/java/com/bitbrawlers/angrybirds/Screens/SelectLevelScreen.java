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
    //Strategy design pattern
    private LevelTransitionStrategy levelTransitionStrategy;

    private Texture background;
    private Texture back;
    private Texture level1;
    private Texture level2;
    private Texture level3;
    private SpriteBatch spriteBatch;
    private AngryBirds game;

    private static final int BUTTON_WIDTH = 3;
    private static final int BUTTON_HEIGHT = 3;

    private float level1X, level2X, level3X;
    private float buttonY;

    private Sound buttonSound;

    private OrthographicCamera camera;
    private Vector2 touchPos;

    public SelectLevelScreen(AngryBirds game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1920, 1080);

        this.background = new Texture("Backgrounds/level_background.png");
        this.back = new Texture("Buttons/back_button.png");
        this.level1 = new Texture("Buttons/level1_button.png");
        this.level2 = new Texture("Buttons/level2_button.png");
        this.level3 = new Texture("Buttons/level3_button.png");

        buttonSound = Gdx.audio.newSound(Gdx.files.internal("Sounds/button.mp3"));
        this.spriteBatch = new SpriteBatch();

        touchPos = new Vector2();

        buttonY = 3;
        level1X = 2.5F;
        level2X = 6.5F;
        level3X = 10.5F;
    }

    @Override
    public void render(float delta) {

        try {
            ScreenUtils.clear(0, 0, 0, 1);
            game.viewport.apply();
            spriteBatch.setProjectionMatrix(game.viewport.getCamera().combined);

            // Begin drawing
            spriteBatch.begin();
            spriteBatch.draw(background, 0, 0, game.viewport.getWorldWidth(), game.viewport.getWorldHeight());
            spriteBatch.draw(back, 0, game.viewport.getWorldHeight() - BUTTON_HEIGHT + 2, BUTTON_WIDTH - 2, BUTTON_HEIGHT - 2);
            spriteBatch.draw(level1, level1X - 0.4F, buttonY, BUTTON_WIDTH + 0.2F, BUTTON_HEIGHT - 0.2F);
            spriteBatch.draw(level2, level2X, buttonY, BUTTON_WIDTH, BUTTON_HEIGHT);
            spriteBatch.draw(level3, level3X, buttonY, BUTTON_WIDTH, BUTTON_HEIGHT);
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

                if (touchPos.x > 0 && touchPos.x < BUTTON_WIDTH &&
                    touchPos.y > game.viewport.getWorldHeight() - BUTTON_HEIGHT && touchPos.y < game.viewport.getWorldHeight()) {
                    buttonSound.play();
                    game.setScreen(new MenuScreen(game));
                    dispose();
                }

                // Level 1 button click
                if (touchPos.x > level1X && touchPos.x < level1X + BUTTON_WIDTH &&
                    touchPos.y > buttonY && touchPos.y < buttonY + BUTTON_HEIGHT) {
                    buttonSound.play();

                    //Strategy design pattern
                    levelTransitionStrategy = new Level1TransitionStrategy(); // Set the strategy for Level 1
                    levelTransitionStrategy.transitionToLevel(game);

                    //game.setScreen(new Level1(game));
                    dispose();
                }

                // Level 2 button click
                if (touchPos.x > level2X && touchPos.x < level2X + BUTTON_WIDTH &&
                    touchPos.y > buttonY && touchPos.y < buttonY + BUTTON_HEIGHT) {
                    buttonSound.play();

                    //Strategy design pattern
                    levelTransitionStrategy = new Level2TransitionStrategy(); // Set the strategy for Level 1
                    levelTransitionStrategy.transitionToLevel(game);

                    //game.setScreen(new Level2(game));
                    dispose();
                }

                // Level 3 button click
                if (touchPos.x > level3X && touchPos.x < level3X + BUTTON_WIDTH &&
                    touchPos.y > buttonY && touchPos.y < buttonY + BUTTON_HEIGHT) {
                    buttonSound.play();

                    //Strategy design pattern
                    levelTransitionStrategy = new Level3TransitionStrategy();
                    levelTransitionStrategy.transitionToLevel(game);

                    //game.setScreen(new Level3(game));
                    dispose();
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
        level1.dispose();
        level2.dispose();
        level3.dispose();
        back.dispose();
        spriteBatch.dispose();
    }
}
