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
     * damage set to 0 hitpoints set to 1 (should die instantly and not deal damage)
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

    /**
     * any powerup should separate from walls
     * @param type what type of gameobject we collide with
     * @param object actual object to interact with
     */
    @Override public void doAction(final GameObject type, final CollisionEntity object) {
	Side side = getCollisionSide(object);
	if (type == GameObject.WALL) {
	    separateSide(side, object);
	}
    }

}
