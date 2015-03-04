package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Yousif Touma on 2015-03-04.
 */
public class VelocityEntity extends Entity {
    private Vector2 velocity;


    public VelocityEntity(Sprite sprite, Vector2 position, Vector2 velocity) {
	super(sprite, position);
	this.velocity = velocity;
    }

    public Vector2 getVelocity() {
	return velocity;
    }

    public void setVelocity(final Vector2 velocity) {
	this.velocity = velocity;
    }

    @Override public void update() {
        super.update();
        this.getPosition().add(velocity);
    }
}
