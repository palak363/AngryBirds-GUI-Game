package com.bitbrawlers.angrybirds.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import com.badlogic.gdx.utils.ScreenUtils;
import com.bitbrawlers.angrybirds.AngryBirds;
import com.bitbrawlers.angrybirds.Birds.*;
import com.bitbrawlers.angrybirds.Blocks.GlassBlock;
import com.bitbrawlers.angrybirds.Blocks.RockBlock;
import com.bitbrawlers.angrybirds.Blocks.WoodBlock;
import com.bitbrawlers.angrybirds.Catapult;
import com.bitbrawlers.angrybirds.Pigs.KingPig;
import com.bitbrawlers.angrybirds.Pigs.LargePig;
import com.bitbrawlers.angrybirds.Pigs.MediumPig;
import com.bitbrawlers.angrybirds.Pigs.SmallPig;


public class GameScreen implements Screen, Serializable {
    int launchCount = 0;
    Vector2 touchPos; // Position of touch input
    private OrthographicCamera camera; // Camera for the game

    private Vector2 dragStartPos;
    private Vector2 dragEndPos;
    private boolean isDragging = false;


    // Declare bird objects
    private Bird temp;

    private BlackBird blackBird;
    //private BlueBird blueBird;
    private GreenBird greenBird;
    //private PinkBird pinkBird;
    private RedBird redBird;
    //private YellowBird yellowBird;

    // Declare pig objects
    private List<SmallPig> smallPigs; // List to hold small pigs
    private MediumPig mediumPig;
    private LargePig largePig;
    private KingPig kingPig;

    private WoodBlock wood1 ;
    private WoodBlock wood2 ;
    private WoodBlock wood3 ;
    private WoodBlock wood4 ;
    private WoodBlock wood5 ;
    private GlassBlock glass1;
    private GlassBlock glass2;
    private RockBlock rock1;
    private RockBlock rock2;

    // Declare textures for background and buttons
    private Texture background;
    private Texture pauseButton;
    private Catapult catapult; // Catapult object for launching birds
    private SpriteBatch spriteBatch; // SpriteBatch for drawing
    private AngryBirds game; // Main game object
    private float pause_buttonX, pause_buttonY; // Button positions
    private float catapult_buttonX, catapult_buttonY;
    private static final int BUTTON_WIDTH = 1; // Button dimensions
    private static final int BUTTON_HEIGHT = 1;
    private Sound buttonSound; // Sound for button presses

    private boolean isBirdLaunched = false; // Flag to track if the bird is launched
    private float launchSpeed = 5.0f; // Launch speed of the bird (adjust as needed)
    private float launchAngle = 30.0f; // Launch angle (adjust as needed)

    public GameScreen(AngryBirds game) {
        this.game = game; // Reference to main game object
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1920, 1080); // Set camera dimensions
        this.background = new Texture("Backgrounds/game_background.png"); // Load background texture

        this.wood1 = new WoodBlock("Rectangle",game.viewport.getWorldWidth() - 6F, 1.25F, new Texture("Elements/Blocks/Wood/img.png"));
        this.wood2 = new WoodBlock("Rectangle",game.viewport.getWorldWidth() - 5.25F, 1.25F, new Texture("Elements/Blocks/Wood/img.png"));
        this.wood3 = new WoodBlock("Rectangle",game.viewport.getWorldWidth() - 4.5F, 1.25F, new Texture("Elements/Blocks/Wood/img.png"));
        this.wood4 = new WoodBlock("Rectangle",game.viewport.getWorldWidth() - 3.75F, 1.25F, new Texture("Elements/Blocks/Wood/img.png"));
        this.wood5 = new WoodBlock("Rectangle",game.viewport.getWorldWidth() - 3F, 1.25F, new Texture("Elements/Blocks/Wood/img.png"));
        this.glass1 = new GlassBlock("Rectangle",game.viewport.getWorldWidth() - 5.75F, 2.0F, new Texture("Elements/Blocks/Glass/img.png"));
        this.glass2 = new GlassBlock("Rectangle",game.viewport.getWorldWidth() - 3F, 2.15F, new Texture("Elements/Blocks/Glass/img_2.png"));
        this.rock1 = new RockBlock("Rectangle",game.viewport.getWorldWidth() - 4.5F, 2.15F, new Texture("Elements/Blocks/Rock/img.png"));
        this.rock2 = new RockBlock("Rectangle",game.viewport.getWorldWidth() - 4.5F, 3.05F, new Texture("Elements/Blocks/Rock/img.png"));

        this.pauseButton = new Texture("Buttons/pause_button.png"); // Load pause button texture
        this.catapult = new Catapult(2.25F, 1.2F); // Initialize catapult

        // Initialize bird objects
        //this.temp = new Bird(3.5F, 3.5F); //Bird position on catapult

        this.blackBird = new BlackBird(0, 1.25F);
        //this.blueBird = new BlueBird(0.5F, 1.25F);
        this.greenBird = new GreenBird(1.0F, 1.25F);
        //this.pinkBird = new PinkBird(1.5F, 1.25F);
        //this.redBird = new RedBird(2.0F, 1.25F);
        this.redBird = new RedBird(3.5F, 3.5F);
        //this.yellowBird = new YellowBird(2.5F, 1.25F);

        // Initialize small pigs list
        smallPigs = new ArrayList<>();
        smallPigs.add(new SmallPig(2, game.viewport.getWorldWidth() - 2.75F, 1.5F));
        smallPigs.add(new SmallPig(2, game.viewport.getWorldWidth() - 4.25F, 1.5F));
        smallPigs.add(new SmallPig(2, game.viewport.getWorldWidth() - 5.75F, 1.5F));

        // Initialize other pig objects
        this.mediumPig = new MediumPig(6, game.viewport.getWorldWidth() - 5.75F, 2.9F);
        this.largePig = new LargePig(8, game.viewport.getWorldWidth() - 3.1F, 3.05F);
        this.kingPig = new KingPig(10, game.viewport.getWorldWidth() - 5.0F, 4F);


        buttonSound = Gdx.audio.newSound(Gdx.files.internal("Sounds/button.mp3")); // Load button sound
        this.spriteBatch = new SpriteBatch(); // Initialize sprite batch
        this.touchPos = new Vector2(); // Initialize touch position
        pause_buttonX = 7; // Set pause button position
        pause_buttonY = 4; // Set pause button height
        catapult_buttonX = 2.25F; // Set catapult button position
        catapult_buttonY = 1.25F; // Set catapult button height
    }

    @Override
    public void render(float delta) {
        //while(launchCount<3) {
        ScreenUtils.clear(0, 0, 0, 1); // Clear the screen with black
        game.viewport.apply(); // Apply viewport settings
        spriteBatch.setProjectionMatrix(game.viewport.getCamera().combined); // Set projection matrix

        spriteBatch.begin(); // Start drawing

        // Draw background and UI elements
        spriteBatch.draw(background, 0, 0, game.viewport.getWorldWidth(), game.viewport.getWorldHeight());
        spriteBatch.draw(pauseButton, 0, game.viewport.getWorldHeight() - BUTTON_HEIGHT, BUTTON_WIDTH, BUTTON_HEIGHT);
        spriteBatch.draw(catapult.getTexture(), catapult.getPosition().x, catapult.getPosition().y, 3, 3);

        // Draw birds
        //spriteBatch.draw(temp.getTexture(), temp.getPosition().x, temp.getPosition().y, 0.7F, 0.7F);


        spriteBatch.draw(blackBird.getTexture(), blackBird.getPosition().x, blackBird.getPosition().y, 0.7F, 0.7F);
        //spriteBatch.draw(blueBird.getTexture(), blueBird.getPosition().x, blueBird.getPosition().y, 0.5F, 0.5F);
        spriteBatch.draw(greenBird.getTexture(), greenBird.getPosition().x, greenBird.getPosition().y, 0.7F, 0.7F);
        //spriteBatch.draw(pinkBird.getTexture(), pinkBird.getPosition().x, pinkBird.getPosition().y, 0.5F, 0.5F);
        spriteBatch.draw(redBird.getTexture(), redBird.getPosition().x, redBird.getPosition().y, 0.7F, 0.7F);
        //spriteBatch.draw(yellowBird.getTexture(), yellowBird.getPosition().x, yellowBird.getPosition().y, 0.5F, 0.5F);

        // Draw structure and pigs
        spriteBatch.draw(wood1.getTexture(), wood1.getPosition().x, wood1.getPosition().y, 1, 1);
        spriteBatch.draw(wood2.getTexture(), wood2.getPosition().x, wood2.getPosition().y, 1, 1);
        spriteBatch.draw(wood3.getTexture(), wood3.getPosition().x, wood3.getPosition().y, 1, 1);
        spriteBatch.draw(wood4.getTexture(), wood4.getPosition().x, wood4.getPosition().y, 1, 1);
        spriteBatch.draw(wood5.getTexture(), wood5.getPosition().x, wood5.getPosition().y, 1, 1);
        //spriteBatch.draw(glass.getTexture(), game.viewport.getWorldWidth() - 7.5F, 0.5F, 7, 7);
        spriteBatch.draw(rock1.getTexture(), rock1.getPosition().x, rock1.getPosition().y, 1, 1);
        spriteBatch.draw(rock2.getTexture(), rock2.getPosition().x, rock2.getPosition().y, 1, 1);
        spriteBatch.draw(glass1.getTexture(), glass1.getPosition().x, glass1.getPosition().y, 1, 1);
        spriteBatch.draw(glass2.getTexture(), glass2.getPosition().x, glass2.getPosition().y, 1, 1);


        for (SmallPig smallPig : smallPigs) {
            spriteBatch.draw(smallPig.getTexture(), smallPig.getX(), smallPig.getY(), 0.5F, 0.5F);
        }
        spriteBatch.draw(mediumPig.getTexture(), mediumPig.getX(), mediumPig.getY(), 0.75F, 0.75F);
        spriteBatch.draw(largePig.getTexture(), largePig.getX(), largePig.getY(), 1.25F, 1.25F);
        spriteBatch.draw(kingPig.getTexture(), kingPig.getX(), kingPig.getY(), 2.0F, 2.0F);

        // Draw win/lose buttons
        //        spriteBatch.draw(win, 4, game.viewport.getWorldHeight() - 2, 1, 1);
        //        spriteBatch.draw(lose, 6, game.viewport.getWorldHeight() - 2, 1, 1);

        spriteBatch.end(); // End drawing
//------------------------------------------------------------------------------------

        if (Gdx.input.justTouched()) {
            //resetBirdPosition();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY()); // Get touch position
            game.viewport.unproject(touchPos); // Convert to world coordinates

            // Pause button
            if (touchPos.x > 0 && touchPos.x < BUTTON_WIDTH &&
                touchPos.y > game.viewport.getWorldHeight() - BUTTON_HEIGHT && touchPos.y < game.viewport.getWorldHeight()) {
                buttonSound.play(); // Play sound
                game.setScreen(new PauseScreen(game)); // Switch to PauseScreen
                dispose(); // Dispose current resources
            }

            if(launchCount == 1) greenBird.setPosition(3.5F,3.5F);
            if(launchCount == 2) blackBird.setPosition(3.5F,3.5F);

            System.out.println("position setted----------------------");

            if (!isDragging) {
                // Start the drag
                dragStartPos = new Vector2(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY());
                isDragging = true;
            } else if (Gdx.input.isTouched()) {
                // Update drag end position as the user drags
                dragEndPos = new Vector2(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY());
            } else {
                // Finalize drag when touch is released
                dragEndPos = new Vector2(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY());
                isDragging = false;
                if(launchCount==0)redBird.calculateLaunch(dragStartPos, dragEndPos, isBirdLaunched);
                else if(launchCount==1)greenBird.calculateLaunch(dragStartPos, dragEndPos, isBirdLaunched);
                else if(launchCount==2)blackBird.calculateLaunch(dragStartPos, dragEndPos, isBirdLaunched);
                //temp.calculateLaunch(dragStartPos, dragEndPos, isBirdLaunched); // Calculate and perform the launch
            }

//            // Win button
//            if (touchPos.x > 4 && touchPos.x < 5 &&
//                touchPos.y > game.viewport.getWorldHeight() - 2 && touchPos.y < game.viewport.getWorldHeight() - 1) {
//                buttonSound.play(); // Play sound
//                game.setScreen(new WinScreen(game)); // Switch to WinScreen
//                dispose(); // Dispose current resources
//            }

//            // Lose button
//            if (touchPos.x > 6 && touchPos.x < 7 &&
//                touchPos.y > game.viewport.getWorldHeight() - 2 && touchPos.y < game.viewport.getWorldHeight() - 1) {
//                buttonSound.play(); // Play sound
//                game.setScreen(new GameOverScreen(game)); // Switch to GameOverScreen
//                dispose(); // Dispose current resources
//            }

        } else if (!Gdx.input.isTouched() && isDragging) {
            // When the user releases the touch, finalize the end position
            dragEndPos = new Vector2(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY());
            isDragging = false;

            // Now you can calculate the launch properties
            if(launchCount==0)redBird.calculateLaunch(dragStartPos, dragEndPos, isBirdLaunched);
            if(launchCount==1)greenBird.calculateLaunch(dragStartPos, dragEndPos, isBirdLaunched);
            if(launchCount==2)blackBird.calculateLaunch(dragStartPos, dragEndPos, isBirdLaunched);
        }

        if (isBirdLaunched) {
            if(launchCount==0)redBird.update(delta);
            else if(launchCount==1)greenBird.update(delta);
            else if(launchCount==2)blackBird.update(delta);
            launchCount++;
            dragEndPos = new Vector2(0,0);
            isBirdLaunched=false;
            //temp.update(delta); // Update bird's position with current velocity and game physics
        }

        System.out.println("launched----------------------");
        //-----------------------------------------------------------------------------
    }

    private void calculateLaunch() {
        if (dragStartPos != null && dragEndPos != null) {
            System.out.println("----"+dragStartPos.x);
            System.out.println("----"+dragStartPos.y);
            // Use actual drag start and end positions
            float x1 = 839.0F;
            float y1 = 919.0F;
            float x2 = dragEndPos.x;
            float y2 = dragEndPos.y;

            System.out.println("-----");
            System.out.println(x1);
            System.out.println(y1);
            System.out.println(x2);
            System.out.println(y2);
            System.out.println("------");

            // Calculate differences in X and Y
            float deltaX = x1 - x2;
            float deltaY = y1 - y2;

            // Calculate the launch angle (in radians)
            float angle = (float) Math.atan2(deltaY, deltaX); // Angle in radians

            // Convert angle to degrees for easier interpretation
            float launchAngle = angle * (180.0f / (float) Math.PI);

            // Calculate the launch speed (length of the drag vector)
            float launchSpeed = (float) Math.sqrt(deltaX * deltaX + deltaY * deltaY);

            // Scale the speed to match game physics (optional)
            launchSpeed *= 0.1f; // Example scaling factor; adjust based on your physics needs

            // Launch the bird
            temp.launch(launchSpeed, launchAngle); // Pass speed and angle to bird object
            isBirdLaunched = true;

            // Reset drag positions
            dragStartPos = null;
            dragEndPos = null;
        }
    }

    @Override
    public void resize(int width, int height) {
        game.viewport.update(width, height); // Update viewport dimensions
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
        // Dispose of all textures and resources
        background.dispose();
        pauseButton.dispose();
        redBird.dispose();
        //blueBird.dispose();
        greenBird.dispose();
        //pinkBird.dispose();
        //yellowBird.dispose();
        spriteBatch.dispose();
    }
}
