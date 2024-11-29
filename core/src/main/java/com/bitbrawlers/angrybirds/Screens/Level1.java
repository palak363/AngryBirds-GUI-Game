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
import com.bitbrawlers.angrybirds.Blocks.WoodBlock;
import com.bitbrawlers.angrybirds.Catapult;
import com.bitbrawlers.angrybirds.Pigs.MediumPig;
import com.bitbrawlers.angrybirds.exceptions.UnableToRenderException;

import java.io.*;

public class Level1 implements Screen, Serializable {
    //private static final String LEVEL1 = "level1_state.ser";

    static float GROUND = 1.3F;
    Vector2 touchPos;
    private transient OrthographicCamera camera;
    private RedBird redBird1;
    private RedBird redBird2;
    private RedBird redBird3;

    private MediumPig mediumPig;
    private WoodBlock wood1;

    private static transient Texture background;
    private static transient Texture pauseButton;
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
    private int currentBirdIndex = 0; // Track the current bird to launch

    private static Sound catapultReleaseSound;
    private static Sound pigHitSound;


    public Level1(AngryBirds game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1920, 1080);
        this.background = new Texture("Backgrounds/game_background.png");

        this.wood1 = new WoodBlock("Rectangle", game.viewport.getWorldWidth() - 5.0F, 1.25F, new Texture("Elements/Blocks/Wood/img_4.png"));

        this.pauseButton = new Texture("Buttons/pause_button.png");
        this.catapult = new Catapult(2.25F, 1.2F);

        this.redBird1 = new RedBird(3.5F, 3.5F);
        this.redBird2 = new RedBird(1.7F, 1.25F);
        this.redBird3 = new RedBird(0.7F, 1.25F);

        this.mediumPig = new MediumPig(6, game.viewport.getWorldWidth() - 4.25F, 4F);

        buttonSound = Gdx.audio.newSound(Gdx.files.internal("Sounds/button.mp3"));
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
            spriteBatch.draw(redBird1.getTexture(), redBird1.getPosition().x, redBird1.getPosition().y, 0.7F, 0.7F);
            spriteBatch.draw(redBird2.getTexture(), redBird2.getPosition().x, redBird2.getPosition().y, 0.7F, 0.7F);
            spriteBatch.draw(redBird3.getTexture(), redBird3.getPosition().x, redBird3.getPosition().y, 0.7F, 0.7F);
            spriteBatch.draw(wood1.getTexture(), wood1.getPosition().x, wood1.getPosition().y, 2.5F, 3.0F);

            spriteBatch.draw(mediumPig.getTexture(), mediumPig.getX(), mediumPig.getY(), 1.0F, 1.0F);
            spriteBatch.end();

            if (Gdx.input.justTouched()) {
                //loadGameState1();
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
                    StartScreen.levelCount = 1;
                    System.out.println("saving");
                    saveGameState1();
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
                if (currentBirdIndex == 0) {
                    redBird1.update(delta);
                    //redBird1.setPosition(new Vector2(redBird1.getPosition().x, GROUND));
                    if (checkCollision(redBird1, mediumPig)) {
                        StartScreen.levelCount = 1;
                        pigHitSound.play();
                        game.setScreen(new WinScreen(game));
                        dispose();
                    }
                    if (redBird1.getPosition().x > game.viewport.getWorldWidth() || redBird1.getPosition().y > game.viewport.getWorldHeight() || redBird1.getPosition().y < 0) {
                        currentBirdIndex = 1; // Move to the next bird
                        redBird2.setPosition(3.5F,3.5F); // Move redBird2 to catapult position
                        isBirdLaunched = false; // Reset launch flag for next bird
                    }
                } else if (currentBirdIndex == 1) {
                    redBird2.update(delta);
                    //redBird2.setPosition(new Vector2(redBird2.getPosition().x, GROUND));
                    if (checkCollision(redBird2, mediumPig)) {
                        StartScreen.levelCount = 1;
                        pigHitSound.play();
                        game.setScreen(new WinScreen(game));
                        dispose();
                    }
                    if (redBird2.getPosition().x > game.viewport.getWorldWidth() || redBird2.getPosition().y > game.viewport.getWorldHeight() || redBird2.getPosition().y < 0) {
                        currentBirdIndex = 2; // Move to the next bird
                        redBird3.setPosition(3.5F,3.5F); // Move redBird3 to catapult position
                        isBirdLaunched = false; // Reset launch flag for next bird
                    }
                } else if (currentBirdIndex == 2) {
                    redBird3.update(delta);
                    //redBird3.setPosition(new Vector2(redBird3.getPosition().x, GROUND));
                    if (checkCollision(redBird3, mediumPig)) {
                        StartScreen.levelCount = 1;
                        pigHitSound.play();
                        game.setScreen(new WinScreen(game));
                        dispose();
                    }
                    if (redBird3.getPosition().x > game.viewport.getWorldWidth() || redBird3.getPosition().y > game.viewport.getWorldHeight() || redBird3.getPosition().y < 0) {
                        StartScreen.levelCount = 1;
                        game.setScreen(new GameOverScreen(game));
                        dispose();
                    }
                }
            }
        }


        catch (Exception e)
        {
            throw new UnableToRenderException("An error occurred during rendering", e);
        }

    }

    public static boolean checkCollision(RedBird bird, MediumPig pig) {
        float birdX = bird.getPosition().x;
        float birdY = bird.getPosition().y;
        float birdWidth = 0.7F;
        float birdHeight = 0.7F;
        float pigX = pig.getX();
        float pigY = pig.getY();
        float pigWidth = 1.0F;
        float pigHeight = 1.0F;
        return birdX < pigX + pigWidth && birdX + birdWidth > pigX && birdY < pigY + pigHeight && birdY + birdHeight > pigY;
    }

    public void calculateLaunch() {
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
                redBird1.launch(launchSpeed, launchAngle);
                catapultReleaseSound.play();
            } else if (currentBirdIndex == 1) {
                redBird2.launch(launchSpeed, launchAngle);
                catapultReleaseSound.play();
            } else if (currentBirdIndex == 2) {
                redBird3.launch(launchSpeed, launchAngle);
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
    public void show() {
        //loadGameState1();
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
        pauseButton.dispose();
        redBird1.dispose();
        redBird2.dispose();
        redBird3.dispose();
        mediumPig.dispose();
        wood1.dispose();
        buttonSound.dispose();
        catapultReleaseSound.dispose();
        pigHitSound.dispose();
        spriteBatch.dispose();
    }

    public void saveGameState1() {
        System.out.println("Saving game state...");
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("level1_state.ser"))) {
            out.writeObject(this);
            out.writeObject(currentBirdIndex);
            out.writeObject(isBirdLaunched);
            System.out.println("Game state saved successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Level1 loadGameState1(AngryBirds game) {
        System.out.println("Loading game state...");
        File file = new File("level1_state.ser");
        if (!file.exists()) {
            System.out.println("Game state file does not exist.");
            return null;
        }

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            Level1 savedState = (Level1) in.readObject();
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
        redBird1.setBirdTexture(new Texture("Elements/Birds/redbird.png"));
        redBird2.setBirdTexture(new Texture("Elements/Birds/redbird.png"));
        redBird3.setBirdTexture(new Texture("Elements/Birds/redbird.png"));
        mediumPig.setPigTexture(new Texture("Elements/Pigs/mediumpig.png"));
        buttonSound = Gdx.audio.newSound(Gdx.files.internal("Sounds/button.mp3"));
        catapultReleaseSound = Gdx.audio.newSound(Gdx.files.internal("Sounds/catapult.mp3"));
        pigHitSound = Gdx.audio.newSound(Gdx.files.internal("Sounds/bird_hit.mp3"));
        wood1 = new WoodBlock("Rectangle", game.viewport.getWorldWidth() - 5.0F, 1.25F, new Texture("Elements/Blocks/Wood/img_4.png"));
        mediumPig = new MediumPig(6, game.viewport.getWorldWidth() - 4.25F, 4F);
        touchPos = new Vector2();
        pause_buttonX = 7;
        pause_buttonY = 4;
        catapult_buttonX = 2.25F;
        catapult_buttonY = 1.25F;
        System.out.println("Transient fields reinitialized");
    }

    public void restoreDynamicState() {
        mediumPig.setPosition(new Vector2(6, game.viewport.getWorldWidth() - 4.25F));
        if (currentBirdIndex == 1) {
            redBird2.setPosition(3.5F, 3.5F);
            //redBird1.setPosition(new Vector2(3.5F, 3.5F));
            redBird3.setPosition(new Vector2(1.7F, 1.25F));
        } else if (currentBirdIndex == 2) {
            redBird3.setPosition(3.5F, 3.5F);
        }
    }
}
