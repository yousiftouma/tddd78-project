package com.mygdx.game.entity.movableentity.coins;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

/**
 * Coin that has a static position.
 */
public class SmallStaticCoin extends AbstractCoin
{

    private final static int VALUE = 10;
    private final static int COIN_WIDTH = 16;
    private final static int COIN_HEIGHT = 16;

    protected SmallStaticCoin(Sprite sprite, Vector2 position, Vector2 size, Vector2 velocity, Vector2 acceleration,
			      int damage, int hitPointsMax, boolean movingLeft)
    {
 	super(sprite, position, size, velocity, acceleration, damage, hitPointsMax, movingLeft);
 	setValue(VALUE);
    }

    public static Vector2 getCoinSize() {
	return new Vector2(COIN_WIDTH, COIN_HEIGHT);
    }

}
