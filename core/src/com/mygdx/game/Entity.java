package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Entity extends Sprite{
    private Sprite sprite;
    private Vector2 pos;
    private Vector2 size;

    public Entity(Sprite sprite, Vector2 pos) {
        this.sprite = sprite;
        this.pos = pos;
        this.set(sprite);
        this.setPosition(pos.x, pos.y);
    }

    public Entity(Sprite sprite, Vector2 pos, Vector2 size) {
        this.sprite = sprite;
        this.pos = pos;
        this.size = size;
        this.set(sprite);
        this.setSize(size.x, size.y);
        this.setPosition(pos.x, pos.y);
    }

    public Sprite getSprite() {
        return sprite;
    }

    public Vector2 getPos() {
        return pos;
    }

    @Override
    public void draw(Batch batch) {
        super.draw(batch);
    }
}
