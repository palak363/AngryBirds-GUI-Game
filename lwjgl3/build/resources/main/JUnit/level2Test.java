package com.bitbrawlers.angrybirds.JUnit;

import com.badlogic.gdx.math.Vector2;
import com.bitbrawlers.angrybirds.AngryBirds;
import com.bitbrawlers.angrybirds.Birds.Bird;
import com.bitbrawlers.angrybirds.Blocks.Block;
import com.bitbrawlers.angrybirds.Pigs.Pig;
import com.bitbrawlers.angrybirds.Screens.Level2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class level2Test {
    private Level2 level2;

    @BeforeEach
    void setUp() {
        AngryBirds game = new AngryBirds();
        level2 = new Level2(game);
    }

    @Test
    void testCheckCollisionWithBlock() {
        Bird bird = level2.redBird;
        Block block = level2.wood1;
        bird.setPosition(new Vector2(block.getPosition().x, block.getPosition().y + 1));
        boolean result = level2.checkCollisionWithBlock(bird, block);
        assertTrue(result, "Collision with the block");
    }

    @Test
    void testHandleCollisionsWithPig() {
        Bird bird = level2.redBird;
        Pig pig = level2.smallPig1;
        bird.setPosition(new Vector2(pig.getX(), pig.getY() + 1));
        level2.handleCollisions(bird);
        assertTrue(pig.isDestroyed(), "Pig destroyed after collision");
    }

    @Test
    void testWinCondition() {
        level2.smallPig1.setDestroyed(true);
        level2.largePig1.setDestroyed(true);
        level2.largePig2.setDestroyed(true);
        level2.handlePostCollision();
        assertEquals(1, level2.win, "Winner");
    }
}
