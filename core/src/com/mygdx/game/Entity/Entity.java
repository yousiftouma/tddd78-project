package com.mygdx.game.entity;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

/**
 * Class that represents all objects in the game
 */
public class Entity extends Sprite {
    private Sprite sprite;
    protected Vector2 position;

    /**
     *
     * @param sprite Sprite that represents the entity
     * @param position Vector2(x,y) bottomleft position of sprite in frame, 0,0 bottom left in frame
     */
    public Entity(Sprite sprite, Vector2 position) {
        this.sprite = sprite;
        this.position = position;
        this.set(sprite);
        this.setPosition(position.x, position.y);
    }

    public Sprite getSprite() {
        return sprite;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(final Vector2 position) {
        this.position = position;
    }

    @Override
    public void draw(Batch batch) {
        super.draw(batch);
    }

    public void update(){
    }
}
