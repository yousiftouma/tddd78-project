package com.mygdx.game.entity.movableentity.coins;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.entity.CollisionEntity;
import com.mygdx.game.entity.GameObject;
import com.mygdx.game.entity.Side;
import com.mygdx.game.entity.movableentity.MovableEntity;



/**
 * Abstract class that is never instantiated and has no abstract methods but is necessary since we
 * inherit methods and create subclasses
 */
public abstract class AbstractCoin extends MovableEntity
{

    private int value;
    private final static int DAMAGE = 0;
    private final static int HIT_POINTS = 1;

    /**
     * damage set to 0
     * hitpoints set to 1 (should die instantly)
     */
    protected AbstractCoin(Sprite sprite, Vector2 position, Vector2 size, Vector2 velocity, Vector2 acceleration)
    {
	super(sprite, position, size, velocity, acceleration, DAMAGE, HIT_POINTS);
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

    @Override public void doAction(GameObject type, CollisionEntity object) {
        if (type == GameObject.WALL) {
            Side side = getCollisionSide(object);
            separateSide(side, object);
        }
    }
}
