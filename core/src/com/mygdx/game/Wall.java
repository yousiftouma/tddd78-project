package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

/**
 * Wall, floor and ceiling class used for platformfunctionality. Detects collision.
 */
public class Wall extends Entity {
    private Vector2 size;

    /**
     * @param sprite Sprite to represent wall
     * @param position Vector2 (x,y) bottomleft position of sprite, 0,0 bottom left in frame
     * @param size Vector2 (width, height)
     */
    public Wall(final Sprite sprite, final Vector2 position, final Vector2 size) {
	super(sprite, position);
	this.size = size;
	this.setSize(size.x, size.y);
    }

    public Vector2 getSize() {
        return size;
    }

    public void setSize(final Vector2 size) {
        this.size = size;
    }
}
