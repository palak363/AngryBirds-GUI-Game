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
import com.bitbrawlers.angrybirds.Birds.*;
import com.bitbrawlers.angrybirds.Blocks.Block;
import com.bitbrawlers.angrybirds.Blocks.GlassBlock;
import com.bitbrawlers.angrybirds.Blocks.RockBlock;
import com.bitbrawlers.angrybirds.Blocks.WoodBlock;
import com.bitbrawlers.angrybirds.Catapult;
import com.bitbrawlers.angrybirds.Pigs.KingPig;
import com.bitbrawlers.angrybirds.Pigs.Pig;
import com.bitbrawlers.angrybirds.Pigs.SmallPig;

import com.bitbrawlers.angrybirds.exceptions.UnableToRenderException;

import java.io.*;

public class Level3 implements Screen, Serializable {
    public static int tntBlast =0;
    float GROUND = 1.3F;
    Vector2 touchPos;
    private transient OrthographicCamera camera;
    private PinkBird pinkBird;
    private BlueBird blueBird;
    private YellowBird yellowBird;
    private int win =0;
    //private SmallPig smallPig1;
    private SmallPig largePig1;
    private SmallPig largePig2;
    private KingPig kingPig1;
    private int kingKillCount = 0 ;
    private WoodBlock wood1;
    private WoodBlock wood2;
    private WoodBlock wood3;
    private RockBlock rock1;
    private WoodBlock tnt;
    //private GlassBlock glass1;
    //private GlassBlock glass1Broken;
    private transient Texture background;
    private transient Texture pauseButton;
    private Catapult catapult;
    private transient SpriteBatch spriteBatch;
    private transient AngryBirds game;
    private float pause_buttonX, pause_buttonY;
    private float catapult_buttonX, catapult_buttonY;
    private static final int BUTTON_WIDTH = 1; // Button dimensions
    private static final int BUTTON_HEIGHT = 1;
    private static transient Sound buttonSound; // Sound for button presses
    private boolean isDragging = false;
    private Vector2 dragStartPos = new Vector2(839.0F, 919.0F);
    private Vector2 dragEndPos;
    private boolean isBirdLaunched = false;
    private int currentBirdIndex = 0;// Track the current bird to launch
    private static transient Sound catapultReleaseSound;
    private static transient Sound pigHitSound;

    public Level3(AngryBirds game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1920, 1080); // Set camera dimensions
        this.background = new Texture("Backgrounds/game_background.png"); // Load background texture

        this.wood1 = new WoodBlock("Rectangle",game.viewport.getWorldWidth() - 6.4F, 1.2F, new Texture("Elements/Blocks/Wood/img_8.png"));
        this.wood2 = new WoodBlock("Rectangle",game.viewport.getWorldWidth() - 4.55F, 1.2F, new Texture("Elements/Blocks/Wood/img_8.png"));
        this.wood3 = new WoodBlock("Table",game.viewport.getWorldWidth() - 4.55F, 2.9F, new Texture("Elements/Blocks/Wood/img_1.png"));
        this.rock1 = new RockBlock("Triangle",game.viewport.getWorldWidth() - 2.65F, 1.1F, new Texture("Elements/Blocks/Rock/img_4.png"));
        this.tnt = new WoodBlock("tnt",game.viewport.getWorldWidth() - 4.55F, 4.65F, new Texture("Elements/Blocks/tnt.png"));
        //this.glass1 = new GlassBlock("Circle",game.viewport.getWorldWidth() - 4.5F, 1.2F, new Texture("Elements/Blocks/Glass/img_2.png"));
        //this.glass1Broken = new GlassBlock("Circle",game.viewport.getWorldWidth() - 4.65F, 2.9F, new Texture("Elements/Blocks/Glass/img_2_broken.png"));

        //this.smallPig1 = new SmallPig(1,game.viewport.getWorldWidth()-4.2F,4.3F);

        this.largePig1 = new SmallPig(1, game.viewport.getWorldWidth() - 5.7F, 1.6F);
        this.largePig2 = new SmallPig(1, game.viewport.getWorldWidth()-5.7F, 3F);
        this.kingPig1 = new KingPig(3, game.viewport.getWorldWidth()-4.55F, 6.45F);

        this.pauseButton = new Texture("Buttons/pause_button.png"); // Load pause button texture
        this.catapult = new Catapult(2.25F, 1.2F); // Initialize catapult

        this.pinkBird = new PinkBird(3.5F, 3.5F);
        this.blueBird = new BlueBird(1.7F, 1.25F);
        this.yellowBird = new YellowBird(0.7F, 1.25F);

        buttonSound = Gdx.audio.newSound(Gdx.files.internal("Sounds/button.mp3")); // Load button sound
        this.spriteBatch = new SpriteBatch();
        this.touchPos = new Vector2();
        pause_buttonX = 7;
        pause_buttonY = 4;
        catapult_buttonX = 2.25F;
        catapult_buttonY = 1.25F;

        catapultReleaseSound = Gdx.audio.newSound(Gdx.files.internal("Sounds/catapult.mp3"));
        pigHitSound = Gdx.audio.newSound(Gdx.files.internal("Sounds/bird_hit.mp3"));
    }

    @Override
    public void render(float delta) {

        try
        {
            ScreenUtils.clear(0, 0, 0, 1);
            game.viewport.apply(); // Apply viewport settings
            spriteBatch.setProjectionMatrix(game.viewport.getCamera().combined); // Set projection matrix

            spriteBatch.begin();
            spriteBatch.draw(background, 0, 0, game.viewport.getWorldWidth(), game.viewport.getWorldHeight());
            spriteBatch.draw(pauseButton, 0, game.viewport.getWorldHeight() - BUTTON_HEIGHT, BUTTON_WIDTH, BUTTON_HEIGHT);
            spriteBatch.draw(catapult.getTexture(), catapult.getPosition().x, catapult.getPosition().y, 3, 3);
            spriteBatch.draw(pinkBird.getTexture(), pinkBird.getPosition().x, pinkBird.getPosition().y, 0.7F, 0.7F);
            spriteBatch.draw(blueBird.getTexture(), blueBird.getPosition().x, blueBird.getPosition().y, 0.7F, 0.7F);
            spriteBatch.draw(yellowBird.getTexture(), yellowBird.getPosition().x, yellowBird.getPosition().y, 0.7F, 0.7F);

            System.out.println("render");
            if(win ==1){
                StartScreen.levelCount = 3;
                game.setScreen(new WinScreen(game));
            }
    //        if (!smallPig1.isDestroyed()) {
    //            spriteBatch.draw(smallPig1.getTexture(), smallPig1.getX(), smallPig1.getY(), 0.75F, 0.75F);
    //        }

            if (!largePig1.isDestroyed()) {
                spriteBatch.draw(largePig1.getTexture(), largePig1.getX(), largePig1.getY(), 0.75F, 0.75F);
            }

            if (!kingPig1.isDestroyed()) {
                spriteBatch.draw(kingPig1.getTexture(), kingPig1.getX(), kingPig1.getY(), 2F, 2F);
            }

            if (!largePig2.isDestroyed()) {
                spriteBatch.draw(largePig2.getTexture(), largePig2.getX(), largePig2.getY(), 0.75F, 0.75F);
            }

            if (!wood1.isDestroyed()) {
                spriteBatch.draw(wood1.getTexture(), wood1.getPosition().x, wood1.getPosition().y, 2F, 2F);
            }
            if (!rock1.isDestroyed()) {
                spriteBatch.draw(rock1.getTexture(), rock1.getPosition().x, rock1.getPosition().y, 2F, 2F);
            }

            if (!wood3.isDestroyed()) {
                spriteBatch.draw(wood3.getTexture(), wood3.getPosition().x, wood3.getPosition().y, 2F, 2F);
            }

            if (!wood2.isDestroyed()) {
                spriteBatch.draw(wood2.getTexture(), wood2.getPosition().x, wood2.getPosition().y, 2F, 2F);
            }
            if (!tnt.isDestroyed()) {
                spriteBatch.draw(tnt.getTexture(), tnt.getPosition().x, tnt.getPosition().y, 2F, 2F);
            }
    //        if (!glass1.isDestroyed() ) {
    //            spriteBatch.draw(glass1.getTexture(), glass1.getPosition().x, glass1.getPosition().y, 1.5F, 1.5F);
    //        }

            spriteBatch.end();
            handlePostCollision();

            if (Gdx.input.justTouched()) {
                System.out.println("touch");
                touchPos.set(Gdx.input.getX(), Gdx.input.getY()); // Get touch position
                game.viewport.unproject(touchPos); // Convert to world coordinates

                if (!isDragging) {
                    dragStartPos = new Vector2(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY());
                    isDragging = true;
                } else if (Gdx.input.isTouched()) {
                    dragEndPos = new Vector2(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY());
                } else {
                    dragEndPos = new Vector2(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY());
                    isDragging = false;
                    calculateLaunch();
                }

                if (touchPos.x > 0 && touchPos.x < BUTTON_WIDTH &&
                    touchPos.y > game.viewport.getWorldHeight() - BUTTON_HEIGHT && touchPos.y < game.viewport.getWorldHeight()) {
                    buttonSound.play();
                    StartScreen.levelCount = 3;
                    System.out.println("saving");
                    saveGameState3();
                    System.out.println("saved");
                    game.setScreen(new PauseScreen(game));
                    dispose();
                }

            } else if (!Gdx.input.isTouched() && isDragging) {
                dragEndPos = new Vector2(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY());
                isDragging = false;
                calculateLaunch();
            }

            // Handle launch and collision detection for all birds
            if (isBirdLaunched) {
                System.out.println("islaunch");
                if (currentBirdIndex == 0) {
                    pinkBird.update(delta);
                    handleCollisions(pinkBird);
                    checkBirdOutOfBounds(pinkBird);
                } else if (currentBirdIndex == 1) {
                    blueBird.update(delta);
                    handleCollisions(blueBird);
                    checkBirdOutOfBounds(blueBird);
                } else if (currentBirdIndex == 2) {
                    yellowBird.update(delta);
                    handleCollisions(yellowBird);
                    checkBirdOutOfBounds(yellowBird);
                }
            }
        }
        catch (Exception e)
        {
            throw new UnableToRenderException("An error occurred during rendering", e);
        }
    }

    private void handleCollisions(Bird bird) {
        System.out.println("handleCollisions");
//        if (checkCollision(bird, smallPig1)) {
//            smallPig1.setDestroyed(true);
//            bird.setVelocity(0, 0);
//            bird.setPosition(new Vector2(bird.getPosition().x, GROUND));
//            handlePostCollision();
//        }
        if (checkCollision(bird, largePig1)) {
            pigHitSound.play();
            largePig1.setDestroyed(true);
            bird.setVelocity(0, 0);
            bird.setPosition(new Vector2(bird.getPosition().x, GROUND));
            handlePostCollision();
        }
        if (checkCollision(bird, kingPig1)) {
            pigHitSound.play();
            if(kingKillCount == 0) kingKillCount=1;
            else {
                kingPig1.setDestroyed(true);
                bird.setVelocity(0, 0);
                bird.setPosition(new Vector2(bird.getPosition().x, GROUND));
                handlePostCollision();
            }
        }
        if (checkCollision(bird, largePig2)) {
            pigHitSound.play();
            largePig2.setDestroyed(true);
            handlePostCollision();
        }
        if (checkCollisionWithBlock(bird, wood1)) {
            pigHitSound.play();
            wood1.setDestroyed(true);
            bird.setVelocity(0, 0);
            bird.setPosition(new Vector2(bird.getPosition().x, GROUND));
            handlePostCollision();
        }
        if (checkCollisionWithBlock(bird, rock1)) {
            pigHitSound.play();
            rock1.setDestroyed(true);
            bird.setVelocity(0, 0);
            bird.setPosition(new Vector2(bird.getPosition().x, GROUND));
            handlePostCollision();
        }

        if (checkCollisionWithBlock(bird, tnt)) {
            pigHitSound.play();
            win=1;
            tntBlast = 1;
            tnt.setDestroyed(true);
            bird.setVelocity(0, 0);
            bird.setPosition(new Vector2(bird.getPosition().x, GROUND));
            handlePostCollision();
        }
        if (checkCollisionWithBlock(bird, wood3)) {
            pigHitSound.play();
            wood3.setDestroyed(true);
            bird.setVelocity(0, 0);
            bird.setPosition(new Vector2(bird.getPosition().x, GROUND));
            handlePostCollision();
        }
        if (checkCollisionWithBlock(bird, wood2)) {
            pigHitSound.play();
            wood2.setDestroyed(true);
            bird.setVelocity(0, 0);
            bird.setPosition(new Vector2(bird.getPosition().x, GROUND));
            handlePostCollision();
        }
//        if (checkCollisionWithBlock(bird, glass1)) {
//            glass1.setDestroyed(true);
//            bird.setVelocity(0, 0);
//            bird.setPosition(new Vector2(bird.getPosition().x, GROUND));
//            handlePostCollision();
//        }
    }

    private void checkBirdOutOfBounds(Bird bird) {
        System.out.println("checkBirdOutOfBounds");
        if (bird.getPosition().y <= GROUND) {
            currentBirdIndex++;
            if (currentBirdIndex < 3) {
                if (currentBirdIndex == 1) blueBird.setPosition(3.5F, 3.5F);
                else if (currentBirdIndex == 2) yellowBird.setPosition(3.5F, 3.5F);
                isBirdLaunched = false; // Reset launch flag for next bird
                handlePostCollision();
            } else {
                StartScreen.levelCount = 3;
                game.setScreen(new GameOverScreen(game));
                dispose();
            }
        }
    }

    private void handlePostCollision() {
        System.out.println("handlePostCollision");
//        if(glass1.isDestroyed()){
//            moveToGround(wood2);
//            moveToGround(largePig1);
//        }
        if (largePig1.isDestroyed() && largePig2.isDestroyed() && kingPig1.isDestroyed()) {
            System.out.println("win");
            win=1;
        }
    }


    private void moveToGround(Block block) {
        System.out.println("moveToGround");
        block.setPosition(block.getPosition().x, GROUND);
    }

    private void moveToGround(Pig pig) {
        System.out.println("moveToGround");
        pig.setPosition(pig.getX(), GROUND+1.7F);
    }


    private boolean checkCollisionWithBlock(Bird bird, Block block) {
        System.out.println("checkCollisionWithBlock");
        float birdX = bird.getPosition().x;
        float birdY = bird.getPosition().y;
        float birdWidth = 0.7F;
        float birdHeight = 0.7F;

        float blockX = block.getPosition().x;
        float blockY = block.getPosition().y;
        float blockWidth = 3F;
        float blockHeight = 2F;
        return birdX < blockX + blockWidth && birdX + birdWidth > blockX &&
            birdY < blockY + blockHeight && birdY + birdHeight > blockY;
    }


    private boolean checkCollision(Bird bird, Pig pig) {
        System.out.println("checkcollision");
        float birdX = bird.getPosition().x;
        float birdY = bird.getPosition().y;
        float birdWidth = 0.7F;
        float birdHeight = 0.7F;

        float pigX = pig.getX();
        float pigY = pig.getY();
        float pigWidth = 0.75F;
        float pigHeight = 0.75F;
        return birdX < pigX + pigWidth && birdX + birdWidth > pigX &&
            birdY < pigY + pigHeight && birdY + birdHeight > pigY;
    }


    private void calculateLaunch() {
        System.out.println("calculatelaunch");
        if (dragStartPos != null && dragEndPos != null) {
            float x1 = 839.0F;
            float y1 = 919.0F;
            float x2 = dragEndPos.x;
            float y2 = dragEndPos.y;
            float deltaX = x1 - x2;
            float deltaY = y1 - y2;

            float angle = (float) Math.atan2(deltaY, deltaX);
            float launchAngle = angle * (180.0f / (float) Math.PI);
            float launchSpeed = (float) Math.sqrt(deltaX * deltaX + deltaY * deltaY);
            launchSpeed *= 0.1f;

            // Launch current bird
            if (currentBirdIndex == 0) {
                pinkBird.launch(launchSpeed, launchAngle);
                catapultReleaseSound.play();
            } else if (currentBirdIndex == 1) {
                blueBird.launch(launchSpeed, launchAngle);
                catapultReleaseSound.play();
            } else if (currentBirdIndex == 2) {
                yellowBird.launch(launchSpeed, launchAngle);
                catapultReleaseSound.play();
            }
            isBirdLaunched = true;
            dragStartPos = null;
            dragEndPos = null;
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
        pauseButton.dispose();
        pinkBird.dispose();
        blueBird.dispose();
        yellowBird.dispose();
        spriteBatch.dispose();
    }

    public void saveGameState3() {
        System.out.println("Saving game state...");
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("level3_state.ser"))) {
            out.writeObject(this);
            out.writeObject(currentBirdIndex);
            out.writeObject(isBirdLaunched);
            System.out.println("Game state saved successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Level3 loadGameState3(AngryBirds game) {
        System.out.println("Loading game state...");
        File file = new File("level3_state.ser");
        if (!file.exists()) {
            System.out.println("Game state file does not exist.");
            return null;
        }
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            Level3 savedState = (Level3) in.readObject();
            savedState.currentBirdIndex = (Integer) in.readObject();
            savedState.isBirdLaunched = (Boolean) in.readObject();
            savedState.reinitialize(game);
            savedState.restoreDynamicState();
            savedState.setGame(game);
            System.out.println("Game state loaded successfully.");
            return savedState;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void setGame(AngryBirds game) {
        this.game = game;
    }

    public void reinitialize(AngryBirds game) {
        this.game = game;
        System.out.println("Reinitializing transient fields...");
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1920, 1080);
        background = new Texture("Backgrounds/game_background.png");
        pauseButton = new Texture("Buttons/pause_button.png");
        spriteBatch = new SpriteBatch();
        pinkBird.setBirdTexture(new Texture("Elements/Birds/pinkbird.png"));
        blueBird.setBirdTexture(new Texture("Elements/Birds/bluebird.png"));
        yellowBird.setBirdTexture(new Texture("Elements/Birds/yellowbird.png"));
        buttonSound = Gdx.audio.newSound(Gdx.files.internal("Sounds/button.mp3"));
        catapultReleaseSound = Gdx.audio.newSound(Gdx.files.internal("Sounds/catapult.mp3"));
        pigHitSound = Gdx.audio.newSound(Gdx.files.internal("Sounds/bird_hit.mp3"));
        wood1 = new WoodBlock("Rectangle", game.viewport.getWorldWidth() - 6.4F, 1.2F, new Texture("Elements/Blocks/Wood/img_8.png"));
        wood2 = new WoodBlock("Rectangle", game.viewport.getWorldWidth() - 4.55F, 1.2F, new Texture("Elements/Blocks/Wood/img_8.png"));
        wood3 = new WoodBlock("Table", game.viewport.getWorldWidth() - 4.55F, 2.9F, new Texture("Elements/Blocks/Wood/img_1.png"));
        rock1 = new RockBlock("Triangle", game.viewport.getWorldWidth() - 2.65F, 1.1F, new Texture("Elements/Blocks/Rock/img_4.png"));
        tnt = new WoodBlock("tnt", game.viewport.getWorldWidth() - 4.55F, 4.65F, new Texture("Elements/Blocks/tnt.png"));
        largePig1 = new SmallPig(1, game.viewport.getWorldWidth() - 5.7F, 1.6F);
        largePig2 = new SmallPig(1, game.viewport.getWorldWidth() - 5.7F, 3F);
        kingPig1 = new KingPig(3, game.viewport.getWorldWidth() - 4.55F, 6.45F);
        pauseButton = new Texture("Buttons/pause_button.png");
        catapult = new Catapult(2.25F, 1.2F);
        pinkBird = new PinkBird(3.5F, 3.5F);
        blueBird = new BlueBird(1.7F, 1.25F);
        yellowBird = new YellowBird(0.7F, 1.25F);
        spriteBatch = new SpriteBatch();
        touchPos = new Vector2();
        pause_buttonX = 7;
        pause_buttonY = 4;
        catapult_buttonX = 2.25F;
        catapult_buttonY = 1.25F;
        System.out.println("Transient fields reinitialized.");
    }

    public void restoreDynamicState() {
        pinkBird.setPosition(new Vector2(3.5F, 3.5F));
        blueBird.setPosition(new Vector2(1.7F, 1.25F));
        yellowBird.setPosition(new Vector2(0.7F, 1.25F));
        largePig1.setPosition(game.viewport.getWorldWidth() - 5.7F, 1.6F);
        largePig2.setPosition(game.viewport.getWorldWidth() - 5.7F, 3F);
        kingPig1.setPosition(game.viewport.getWorldWidth() - 4.55F, 6.45F);
        wood1.setPosition(new Vector2(game.viewport.getWorldWidth() - 6.4F, 1.2F));
        wood2.setPosition(new Vector2(game.viewport.getWorldWidth() - 4.55F, 1.2F));
        wood3.setPosition(new Vector2(game.viewport.getWorldWidth() - 4.55F, 2.9F));
        rock1.setPosition(game.viewport.getWorldWidth() - 2.65F, 1.1F);
        tnt.setPosition(new Vector2(game.viewport.getWorldWidth() - 4.55F, 4.65F));
        System.out.println("Dynamic state restored.");
    }

}
