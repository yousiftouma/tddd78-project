package com.mygdx.game.entity.movableentity.coins;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.entity.CollisionEntity;
import com.mygdx.game.entity.Entity;
import com.mygdx.game.entity.GameObject;

import java.util.Collection;

/**
 * A small moving coin that will have a velocity
 */
public class SmallMovingCoin extends AbstractCoin
{

    private final static int VALUE = 20;
    private final static int COIN_WIDTH = 16;
    private final static int COIN_HEIGHT = 16;

    protected SmallMovingCoin(final Sprite sprite, final Vector2 position, final Vector2 size, final Vector2 velocity,
			      final Vector2 acceleration, final int damage, final int hitPointsMax)
    {
	super(sprite, position, size, velocity, acceleration, damage, hitPointsMax);
	setValue(VALUE);
    }


    @Override public void moveLeft(final float dt) {

     }

     @Override public void moveRight(final float dt) {

     }

     @Override public void doAction(final GameObject type, final CollisionEntity object) {

     }

    @Override public void onDeath(final Collection<Entity> objects) {

    }

    public static Vector2 getCoinSize() {
	return new Vector2(COIN_WIDTH, COIN_HEIGHT);
    }
}
