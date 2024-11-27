//package com.bitbrawlers.angrybirds.JUnitTest;
//
//import com.bitbrawlers.angrybirds.Birds.RedBird;
//import com.bitbrawlers.angrybirds.Pigs.MediumPig;
//import org.junit.*;
//import com.badlogic.gdx.math.Vector2;
//
//import com.bitbrawlers.angrybirds.Screens.Level1;
//
//import com.bitbrawlers.angrybirds.Birds.RedBird;
//
//public class Test
//{
//    // Test the bird's position is set correctly
//    @Test
//    public void testSetPosition()
//    {
//        RedBird redBird = new RedBird(100,200);
//        Vector2 position = redBird.getPosition();
//
//        Assert.assertEquals(100, position.x);
//        Assert.assertEquals(200, position.y);
//    }
//
//    // Test the bird is launched
//    @Test
//    public void testLaunch()
//    {
//        RedBird redBird = new RedBird(100,200);
//
//        redBird.launch(10,45);
//        assertTrue(redBird.launched(), "Bird should be launched after calling launch");
//    }
//
//    // Test the position changes after launch
//    @Test
//    public void testLaunchPositionChange()
//    {
//        RedBird redBird = new RedBird(100,200);
//
//        redBird.launch(10,45);
//        assertNotEquals(100, redBird.getPosition().x);
//        assertNotEquals(200, redBird.getPosition().y);
//    }
//
//    // Test the bird collides with pig
//    @Test
//    public void testBirdCollidesWithPig()
//    {
//        RedBird redBird = new RedBird(100,200);
//        MediumPig pig = new MediumPig(1,100,200);
//
//        assertTrue(Level1.checkCollision(redBird,pig));
//    }
//}
