package com.mygdx.game.entity.movableentity.coins;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.entity.movableentity.MovableEntity;



/**
 * Abstract class that is never created and has no abstract methods but is necessary since we
 * inherit methods and create subclasses
 */
public abstract class AbstractCoin extends MovableEntity
{
    protected int value;

    protected AbstractCoin(final Sprite sprite, final Vector2 position, final Vector2 size, final Vector2 velocity,
			   final Vector2 acceleration, final int damage, final int hitPointsMax)
    {
	super(sprite, position, size, velocity, acceleration, damage, hitPointsMax);
    }

    public void setValue(final int value) {
	this.value = value;
    }

    public int getValue() {
	return value;
    }
}
