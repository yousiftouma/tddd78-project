package com.mygdx.game.entity.movableentity.powerups;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.entity.CollisionEntity;
import com.mygdx.game.entity.GameObject;
import com.mygdx.game.entity.Side;
import com.mygdx.game.maps.AbstractMap;


/**
 * Normal states, can return its durationtime, type and size
 */
public class NormalMovingPowerDown extends AbstractPowerUp {

    private boolean movingLeft;

    private final static int POWER_UP_WIDTH = 32;
    private final static int POWER_UP_HEIGHT = 32;
    private final static float POWER_DOWN_TIME = 10;

    public NormalMovingPowerDown(Sprite sprite, Vector2 position, Vector2 size, Vector2 velocity, Vector2 acceleration,
				 boolean movingLeft) {
        super(sprite, position, size, velocity, acceleration);
	setPowerUpTime(POWER_DOWN_TIME);
	this.movingLeft = movingLeft;
    }

    @Override public GameObject getGameObjectType() {
	return GameObject.NORMAL_MOVING_POWER_DOWN;
    }

    public static Vector2 getPowerUpSize(){
	return new Vector2(POWER_UP_WIDTH, POWER_UP_HEIGHT);
    }

    @Override public void doAction(final GameObject type, final CollisionEntity object) {
	super.doAction(type, object);
	Side side = getCollisionSide(object);
	if (type == GameObject.WALL) {
	    separateSide(side, object);
	    // check both that collisionside returns right or left but also if it seems to be a wall and not platform
	    // to avoid sometimes changing direction when about to fall off platform (and getting side = left or right)
	    if ((side == Side.LEFT || side == Side.RIGHT) && object.getWidth() <= AbstractMap.NORMAL_WALL_THICKNESS) {
		movingLeft = !movingLeft;
	    }
	}
    }
    @Override public void update(float dt) {
	super.update(dt);
	if (movingLeft) {
	    moveLeft(dt);
	} else {
	    moveRight(dt);
	}
    }

}
