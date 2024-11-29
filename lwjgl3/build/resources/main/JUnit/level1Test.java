package com.bitbrawlers.angrybirds.JUnit;

import com.badlogic.gdx.graphics.Texture;
import com.bitbrawlers.angrybirds.AngryBirds;
import com.bitbrawlers.angrybirds.Birds.RedBird;
import com.bitbrawlers.angrybirds.Blocks.WoodBlock;
import com.bitbrawlers.angrybirds.Pigs.MediumPig;
import com.bitbrawlers.angrybirds.Screens.Level1;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class level1Test {
    private Level1 level1;
    private RedBird redBird1;
    private RedBird redBird2;
    private RedBird redBird3;
    private MediumPig mediumPig;
    private WoodBlock woodBlock;

    @Before
    public void setUp() {
        level1 = new Level1(new AngryBirds());
        redBird1 = new RedBird(3.5F, 3.5F);
        redBird2 = new RedBird(1.7F, 1.25F);
        redBird3 = new RedBird(0.7F, 1.25F);
        mediumPig = new MediumPig(6, 6, 4F);
        woodBlock = new WoodBlock("Rectangle", 5.0F, 1.25F, new Texture("Elements/Blocks/Wood/img_4.png"));
    }

    @Test
    public void testCollisionDetection() {
        redBird1.setPosition(5.0F, 4.0F);
        mediumPig.setPosition(5.0F, 4.0F);

        boolean collision = Level1.checkCollision(redBird1, mediumPig);
        assertTrue("Bird and pig should collide", collision);

        redBird1.setPosition(10.0F, 10.0F);
        collision = Level1.checkCollision(redBird1, mediumPig);
        assertFalse("Bird and pig should not collide", collision);
    }

    @Test
    public void testWoodBlockPosition() {
        float expectedX = 5.0F;
        float expectedY = 1.25F;

        assertEquals("Wood block should be at expected X position", expectedX, woodBlock.getPosition().x, 0.001);
        assertEquals("Wood block should be at expected Y position", expectedY, woodBlock.getPosition().y, 0.001);
    }
}
