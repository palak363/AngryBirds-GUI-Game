package com.bitbrawlers.angrybirds;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.bitbrawlers.angrybirds.Screens.StartScreen;
//import com.bitbrawlers.angrybirds.Screens.x;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class AngryBirds extends Game {
    public FitViewport viewport;
    public SpriteBatch batch;

    @Override
    public void create () {
        Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
        viewport = new FitViewport(16, 9);
        batch = new SpriteBatch();
        setScreen(new StartScreen(this));
        //setScreen(new Level3(this));
    }

    @Override
    public void render () {
        super.render();
    }

    @Override
    public void dispose() {
        super.dispose();
        batch.dispose();
    }



    @Override
    public void resize(int width, int height){
        viewport.update(width, height, true);
    }
}
