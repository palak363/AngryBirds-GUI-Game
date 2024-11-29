package com.bitbrawlers.angrybirds;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import java.io.Serializable;

public class Catapult implements Serializable {
    private transient Texture catapultTexture;
    private Vector2 position;

    public Catapult(float x, float y) {
        this.catapultTexture = new Texture("Elements/catapult.png");
        this.position = new Vector2(x, y);
    }

    public Texture getTexture() {
        return catapultTexture;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = new Vector2(position.x, position.y);
    }

    public void dispose() {
        catapultTexture.dispose();
    }
}
