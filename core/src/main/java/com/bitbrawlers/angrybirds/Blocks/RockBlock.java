package com.bitbrawlers.angrybirds.Blocks;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import java.io.Serializable;

public class RockBlock extends Block implements Serializable
{
    private transient Texture image;
    private Vector2 position;

    public RockBlock(String shape, float x, float y, Texture image)
    {
        super(shape,"Rock",3,x,y);
        this.image = image;
        this.position = new Vector2(x, y);

    }
    public Texture getTexture() {
        return image;
    }

    public Vector2 getPosition() {
        return position;
    }
}

