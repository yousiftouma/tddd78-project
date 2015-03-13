package com.mygdx.game.entity.movableentity.coins;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.entity.CollisionEntity;
import com.mygdx.game.entity.Entity;
import com.mygdx.game.entity.GameObject;
import com.mygdx.game.entity.Side;
import com.mygdx.game.entity.movableentity.MovableEntity;

import java.util.Collection;


/**
 * Abstract class that is never instanciated and has no abstract methods but is necessary since we
 * inherit methods and create subclasses
 */
public abstract class AbstractCoin extends MovableEntity
{
    protected boolean movingLeft;
    protected int value;

    protected AbstractCoin(Sprite sprite, Vector2 position, Vector2 size, Vector2 velocity, Vector2 acceleration,
			   int damage, int hitPointsMax, boolean movingLeft)
    {
	super(sprite, position, size, velocity, acceleration, damage, hitPointsMax);
	this.movingLeft = movingLeft;
    }

    public void setValue(final int value) {
	this.value = value;
    }

    public int getValue() {
	return value;
    }
    @Override public void moveLeft(final float dt) {
        setPositionX(getPosition().x - velocity.x * dt);
    }

    @Override public void moveRight(final float dt) {
	setPositionX(getPosition().x + velocity.x * dt);
    }

    @Override public void onDeath(final Collection<Entity> objects) {
	remove(objects);
    }

    @Override public void doAction(GameObject type, CollisionEntity object) {
        if (type == GameObject.WALL) {
            Side side = getCollisionSide(object);
            separateSide(side, object);
	    if (side == Side.LEFT || side == Side.RIGHT) {
		movingLeft = !movingLeft;
	    }
        }
    }
}
