package com.mygdx.game.entity.movableentity.coins;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.entity.GameObject;

/**
 * A small moving coin that will have a velocity when created in factory, but with same
 * class-structur as SmallStaticCoin
 */
public class SmallMovingCoin extends SmallStaticCoin
{

    protected SmallMovingCoin(Sprite sprite, Vector2 position, Vector2 size, Vector2 velocity, Vector2 acceleration,
			      int hitPointsMax, boolean movingLeft)
    {
	super(sprite, position, size, velocity, acceleration, hitPointsMax, movingLeft);
    }

    /**
     * Ignores superclass method on purpose as they should return different GameObject enums
     * @return actual GameObject type
     */
    @Override public GameObject getGameObjectType() {
	return GameObject.SMALL_MOVING_COIN;
    }
}
