package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Entity extends Sprite{
    private Sprite sprite;
    private Vector2 position;
    private Vector2 size;

    public Entity(Sprite sprite, Vector2 position) {
        this.sprite = sprite;
        this.position = position;
        this.set(sprite);
        this.setPosition(position.x, position.y);
    }

    /**
     * Move to wallentity where it has its only use
     * @return
     */
    /*public Entity(Sprite sprite, Vector2 position, Vector2 size) {
        this.sprite = sprite;
        this.position = position;
        this.size = size;
        this.set(sprite);
        this.setSize(size.x, size.y);
        this.setPosition(position.x, position.y);
    }*/

    public Sprite getSprite() {
        return sprite;
    }

    public Vector2 getPosition() {
        return position;
    }

    @Override
    public void draw(Batch batch) {
        super.draw(batch);
    }
}
