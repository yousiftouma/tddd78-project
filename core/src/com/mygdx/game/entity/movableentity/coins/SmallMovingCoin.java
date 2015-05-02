package com.mygdx.game.entity.movableentity.coins;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.entity.CollisionEntity;
import com.mygdx.game.entity.GameObject;
import com.mygdx.game.entity.Side;
import com.mygdx.game.maps.AbstractMap;

/**
 * A small moving coin that will have a velocity when created in factory, but with same class-structur as SmallStaticCoin
 */
public class SmallMovingCoin extends SmallStaticCoin
{
    private boolean movingLeft;

    protected SmallMovingCoin(Sprite sprite, Vector2 position, Vector2 size, Vector2 velocity, Vector2 acceleration,
			      boolean movingLeft)
    {
	super(sprite, position, size, velocity, acceleration);
	this.movingLeft = movingLeft;
    }

    @Override public void doAction(GameObject type, CollisionEntity object) {
	super.doAction(type, object);
	if (type == GameObject.WALL) {
	    Side side = getCollisionSide(object);
	    // check both that collisionside returns right or left but also if it seems to be a wall and not platform
	    // to avoid sometimes changing direction when about to fall off platform (and getting side = left or right)
	    if ((side == Side.LEFT || side == Side.RIGHT) && object.getWidth() <= AbstractMap.NORMAL_WALL_THICKNESS) {
		movingLeft = !movingLeft;
	    }
	}
    }

    @Override
    public void update(float dt) {
        super.update(dt);
        if (movingLeft){
            moveLeft(dt);
        }
        else{
            moveRight(dt);
        }
    }

    /**
     * Ignores superclass method on purpose as they should return different GameObject enum.
     * @return actual GameObject type
     */
    @Override public GameObject getGameObjectType() {
	return GameObject.SMALL_MOVING_COIN;
    }

}
