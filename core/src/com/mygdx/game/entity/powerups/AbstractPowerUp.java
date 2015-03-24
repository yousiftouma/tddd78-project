package com.mygdx.game.entity.powerups;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.entity.CollisionEntity;

/**
 * Abstract powerup that is never instantiated but with shared code for subclasses
 */
public abstract class AbstractPowerUp extends CollisionEntity
{
    /**
     * variable is protected since it will have different values for different
     * powerups and need to be accessed with abstract getter in each subclass
     */
    protected float powerUpTime;
    public abstract float getPowerUpTime();

    /**
     * default damage to 0
     * @param sprite
     * @param position
     * @param size
     * @param powerUpTime
     */
    protected AbstractPowerUp(Sprite sprite, Vector2 position, Vector2 size, float powerUpTime) {
	super(sprite, position, size, 0);
	this.powerUpTime = powerUpTime;
    }
}
