package com.mygdx.game.entity.movableentity.powerups;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.entity.CollisionEntity;
import com.mygdx.game.entity.GameObject;
import com.mygdx.game.entity.Side;
import com.mygdx.game.entity.movableentity.MovableEntity;


/**
 * Abstract states that is never instantiated but with shared code for subclasses
 */
public abstract class AbstractPowerUp extends MovableEntity
{

    private float powerUpTime;

    private final static int DAMAGE = 0;
    private final static int HIT_POINTS = 1;

    /**
     * damage set to 0 hitpoints set to 1 (should die instantly)
     */
    protected AbstractPowerUp(Sprite sprite, Vector2 position, Vector2 size, Vector2 velocity, Vector2 acceleration)
    {
	super(sprite, position, size, velocity, acceleration, DAMAGE, HIT_POINTS);
    }

    public void setPowerUpTime(final float powerUpTime) {
	this.powerUpTime = powerUpTime;
    }

    public float getPowerUpTime() {
	return powerUpTime;
    }

    @Override public void moveLeft(final float dt) {
	setPositionX(getPosition().x - velocity.x * dt);
    }

    @Override public void moveRight(final float dt) {
	setPositionX(getPosition().x + velocity.x * dt);
    }

    @Override public void doAction(final GameObject type, final CollisionEntity object) {
	Side side = getCollisionSide(object);
	if (type == GameObject.WALL) {
	    separateSide(side, object);
	}
    }
}
