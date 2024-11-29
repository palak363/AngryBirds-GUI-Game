package com.bitbrawlers.angrybirds.Blocks;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import java.awt.Point;
import java.io.Serializable;

public abstract class Block implements Serializable
{
    private final String material;
    private Vector2 position;
    private int durability;
    private final String shape;
    private boolean isDestroyed;

    public Block(String shape, String material, int durability, float x, float y)
    {
        this.shape=shape;
        this.material=material;
        this.durability=durability;
        this.position = new Vector2(x, y);
        this.isDestroyed=false;
    }
    public Block(String shape, String material, int durability)
    {
        this.shape=shape;
        this.material=material;
        this.durability=durability;
    }

    public boolean isDestroyed() {
        return isDestroyed;
    }

    public void setDestroyed(boolean destroyed) {
        isDestroyed = destroyed;
    }


    public String getMaterial()
    {
        return material;
    }
    public Vector2 getPosition()
    {
        return position;
    }
    public int getDurability()
    {
        return durability;
    }
    public void setDurability(int durability)
    {
        this.durability=durability;
    }

    public String getShape()
    {
        return shape;
    }

    // Method to update the block's position
    public void setPosition(float x,float y)
    {
        this.position=new Vector2(x, y);
    }

    public void dispose() {
    }

//    public Texture getTexture() {
//        return null;
//    }


}
