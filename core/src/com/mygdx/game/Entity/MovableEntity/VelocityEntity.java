package com.mygdx.game.Entity.MovableEntity;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Entity.Entity;

/**
 * Entity that handles movement with constant speed
 */
public class VelocityEntity extends Entity
{

    private Vector2 velocity;

    /**
     *
     * @param sprite Sprite that represents the entity
     * @param position Vector2(x,y) bottomleft position of entity, 0,0 bottom left in frame
     * @param velocity Vector2(x velocity, y velocity) positive rightward and up
     */
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
        this.position.add(velocity);
    }
}
