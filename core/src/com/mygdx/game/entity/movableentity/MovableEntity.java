package com.mygdx.game.entity.movableentity;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.entity.CollisionEntity;
import com.mygdx.game.entity.Entity;

/**
 * Created by Yousif Touma on 2015-03-04.
 */
public abstract class MovableEntity extends CollisionEntity
{
    protected Vector2 velocity;
    protected Vector2 acceleration;
    public abstract void moveLeft(float dt);
    public abstract void moveRight(float dt);
    public abstract void doAction(String type, float x, float y);


    protected MovableEntity(Sprite sprite, Vector2 position, Vector2 size, Vector2 velocity, Vector2 acceleration) {
	super(sprite, position, size);
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

    public void update(float dt) {
	setPosition(new Vector2(getPosition().x, getPosition().y + velocity.y*dt));
	velocity.y -= acceleration.y * dt;
    }
}
