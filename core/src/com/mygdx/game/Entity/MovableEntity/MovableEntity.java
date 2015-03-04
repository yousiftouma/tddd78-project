package com.mygdx.game.Entity.MovableEntity;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Entity.Entity;

/**
 * Created by Yousif Touma on 2015-03-04.
 */
public class MovableEntity extends Entity {
    protected Vector2 velocity;
    protected Vector2 acceleration;

    public MovableEntity(final Sprite sprite, final Vector2 position, final Vector2 velocity, final Vector2 acceleration) {
	super(sprite, position);
	this.velocity = velocity;
	this.acceleration = acceleration;
    }

    public Vector2 getVelocity() {
	return velocity;
    }

    public Vector2 getAcceleration() {
	return acceleration;
    }

    public void setVelocity(final Vector2 velocity) {
	this.velocity = velocity;
    }

    public void setAcceleration(final Vector2 acceleration) {
	this.acceleration = acceleration;
    }

    @Override public void update() {
	super.update();
	this.position.add(velocity);
	this.velocity.add(acceleration);
    }
}
