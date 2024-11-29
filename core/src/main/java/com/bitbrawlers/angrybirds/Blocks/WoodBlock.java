package com.bitbrawlers.angrybirds.Blocks;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import java.awt.Point;
import java.io.Serializable;

public class WoodBlock extends Block implements Serializable
{
    private transient Texture image;
    private Vector2 position;

    public WoodBlock(String shape, float x, float y, Texture image)
    {
        super(shape,"Wood",1,x,y);
        this.image = image;
        this.position = new Vector2(x, y);

    }
    public Texture getTexture() {
        return image;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }


}
