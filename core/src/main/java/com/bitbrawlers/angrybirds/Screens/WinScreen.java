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
    private Texture background;
    private Texture tntBackground;
    private Texture mainMenu;
    private Texture exit;
    private SpriteBatch spriteBatch;
    private AngryBirds game;

    private static final int BUTTON_WIDTH = 2;
    private static final int BUTTON_HEIGHT = 1;

    private float mainMenuX, exitX;
    private float buttonY;

    private Sound buttonSound;

    private OrthographicCamera camera;
    private Vector2 touchPos;

    public WinScreen(AngryBirds game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1920, 1080);

        this.background = new Texture("Backgrounds/win_background.png");
        this.tntBackground = new Texture("Backgrounds/blast.png");
        this.mainMenu = new Texture("Buttons/mainmenu_button.png");
        this.exit = new Texture("Buttons/exitgame_button.png");
        buttonSound = Gdx.audio.newSound(Gdx.files.internal("Sounds/button.mp3"));
        this.spriteBatch = new SpriteBatch();

        touchPos = new Vector2();

        buttonY = 5;
        mainMenuX = 5;
        exitX = 9;
    }

    @Override
    public void render(float delta) {
        try {
            ScreenUtils.clear(0, 0, 0, 1);
            game.viewport.apply();
            spriteBatch.setProjectionMatrix(game.viewport.getCamera().combined);

            spriteBatch.begin();
            if (Level3.tntBlast == 1) {
                spriteBatch.draw(tntBackground, 0, 0, game.viewport.getWorldWidth(), game.viewport.getWorldHeight());
            } else {
                spriteBatch.draw(background, 0, 0, game.viewport.getWorldWidth(), game.viewport.getWorldHeight());
            }
            spriteBatch.draw(mainMenu, mainMenuX, buttonY, BUTTON_WIDTH, BUTTON_HEIGHT);
            spriteBatch.draw(exit, exitX, buttonY, BUTTON_WIDTH, BUTTON_HEIGHT);
            spriteBatch.end();
        }
        catch (RuntimeException e)
        {
            throw new UnableToRenderException("Failed to render the screen!", e);
        }

        try
        {
            if (Gdx.input.justTouched()) {
                touchPos.set(Gdx.input.getX(), Gdx.input.getY());
                game.viewport.unproject(touchPos);

                if (touchPos.x > mainMenuX && touchPos.x < mainMenuX + BUTTON_WIDTH &&
                    touchPos.y > buttonY && touchPos.y < buttonY + BUTTON_HEIGHT) {
                    buttonSound.play();
                    game.setScreen(new SelectLevelScreen(game));
                    dispose();
                }

                if (touchPos.x > exitX && touchPos.x < exitX + BUTTON_WIDTH &&
                    touchPos.y > buttonY && touchPos.y < buttonY + BUTTON_HEIGHT) {
                    buttonSound.play();
                    dispose();
                    Gdx.app.exit();
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
        mainMenu.dispose();
        exit.dispose();
        spriteBatch.dispose();
    }
}
