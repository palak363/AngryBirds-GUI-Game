package com.bitbrawlers.angrybirds.JUnit;

import com.badlogic.gdx.math.Vector2;
import com.bitbrawlers.angrybirds.Birds.RedBird;
import com.bitbrawlers.angrybirds.Pigs.MediumPig;
import com.bitbrawlers.angrybirds.Screens.Level1;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Bird
{
    @Test
    public void testSetPosition() {
        RedBird redBird = new RedBird(100,200);
        Vector2 position = redBird.getPosition();
        Assert.assertEquals(100, position.x);
        Assert.assertEquals(200, position.y);
    }

    @Test
    public void testLaunch() {
        RedBird redBird = new RedBird(100,200);
        redBird.launch(10,45);
        assertTrue(redBird.launched(), "Bird should be launched");
    }

    @Test
    public void testLaunchPositionChange() {
        RedBird redBird = new RedBird(100,200);
        redBird.launch(10,45);
        assertNotEquals(100, redBird.getPosition().x);
        assertNotEquals(200, redBird.getPosition().y);
    }

    @Test
    public void testBirdCollidesWithPig() {
        RedBird redBird = new RedBird(100,200);
        MediumPig pig = new MediumPig(1,100,200);
        assertTrue(Level1.checkCollision(redBird,pig));
    }
}


