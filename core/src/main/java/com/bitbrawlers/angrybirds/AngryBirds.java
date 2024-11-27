package com.bitbrawlers.angrybirds;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.bitbrawlers.angrybirds.Screens.GameScreen;
import com.bitbrawlers.angrybirds.Screens.Level1;
import com.bitbrawlers.angrybirds.Screens.Level3;
import com.bitbrawlers.angrybirds.Screens.StartScreen;
//import com.bitbrawlers.angrybirds.Screens.x;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class AngryBirds extends Game {
    public FitViewport viewport; // Viewport for handling the game’s rendering dimensions
    public SpriteBatch batch; // SpriteBatch for drawing 2D graphics

    @Override
    public void create () {
        // Set the game to fullscreen mode using the current display's settings
        Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());

        // Initialize the viewport with a 16:9 aspect ratio
        viewport = new FitViewport(16, 9);

        // Initialize the SpriteBatch for rendering textures
        batch = new SpriteBatch();

        // Set the initial screen to the StartScreen
        setScreen(new StartScreen(this));
        //setScreen(new Level3(this));
    }

    @Override
    public void render () {
        // Call the render method of the Game superclass, which handles screen updates
        super.render();
    }

    @Override
    public void dispose() {
        // Dispose of resources when the game is closed
        super.dispose();
        batch.dispose(); // Dispose of the SpriteBatch
    }



    @Override
    public void resize(int width, int height){
        // Update the viewport when the game window is resized
        viewport.update(width, height, true);
    }
}
