package com.mygdx.game.entity.movableentity.coins;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

/**
 * A small moving coin that will have a velocity when created in factory, but with same
 * class-structur as SmallStaticCoin
 */
public class SmallMovingCoin extends SmallStaticCoin
{

    protected SmallMovingCoin(Sprite sprite, Vector2 position, Vector2 size, Vector2 velocity, Vector2 acceleration,
			      int damage, int hitPointsMax, boolean movingLeft)
    {
	super(sprite, position, size, velocity, acceleration, damage, hitPointsMax, movingLeft);
    }
}
