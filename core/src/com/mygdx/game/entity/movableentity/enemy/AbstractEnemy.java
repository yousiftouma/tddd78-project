package com.mygdx.game.entity.movableentity.enemy;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.entity.CollisionEntity;
import com.mygdx.game.entity.GameObject;
import com.mygdx.game.entity.Side;
import com.mygdx.game.entity.movableentity.MovableEntity;
import com.mygdx.game.entity.movableentity.player.Player;
import com.mygdx.game.entity.movableentity.player.powerup.NormalInvincibilityState;
import com.mygdx.game.maps.AbstractMap;
import com.mygdx.game.screens.GameScreen;

/**
 * Abstract enemy class with abstract methods with different implementation for different types of enemies
 */
public abstract class AbstractEnemy extends MovableEntity
{
    protected boolean movingLeft;

    protected AbstractEnemy(Sprite sprite, Vector2 position, Vector2 size, Vector2 velocity, Vector2 acceleration, int damage,
			    int hitPointsMax, boolean movingLeft)
    {
	super(sprite, position, size, velocity, acceleration, damage, hitPointsMax);
	this.movingLeft = movingLeft;
    }

    @Override public void doAction(GameObject type, CollisionEntity object) {
	Side side = getCollisionSide(object);
	switch (type) {
	    case WALL:
		separateSide(side, object);
		// check both that collisionside returns right or left but also if it seems to be a wall and not platform
		// to avoid sometimes changing direction when about to fall off platform (and getting side = left or right)
		if ((side == Side.LEFT || side == Side.RIGHT) && object.getWidth() < AbstractMap.getNormalWallThickness()) {
		    movingLeft = !movingLeft;
		}
		break;
	    case ENEMY:
		separateSide(side, object);
		if (side == Side.RIGHT) {
		    movingLeft = false;
		    AbstractEnemy collidedEnemy = (AbstractEnemy) object;
		    collidedEnemy.movingLeft = true;
		} else if (side == Side.LEFT) {
		    movingLeft = true;
		    AbstractEnemy collidedEnemy = (AbstractEnemy) object;
		    collidedEnemy.movingLeft = false;
		}
		break;
	    case PLAYER:
		separateSide(side, object);
		Player player = (Player) object;
		if (!player.getpState().isInvincible()) {
		    System.out.println("enemy detects player collision");
		    player.takeDamage(this.damage);
		    player.setpState(new NormalInvincibilityState());
		    player.setPowerUpTimer(3);
		    System.out.println("taken damage:   " + player);
		    GameScreen.getTakeDamageSound().play();
		}
		break;
	    case SMALL_STATIC_COIN:
	    case SMALL_MOVING_COIN:
	    case NORMAL_STATIC_POWER_UP:
		break;
	}
    }

    @Override public void update(float dt) {
	super.update(dt);
	if (movingLeft) {
	    moveLeft(dt);
	} else {
	    moveRight(dt);
	}
	//can add enemy death when falling out of screen to the bottom
    }

    public void setMovingLeft(boolean movingLeft) {
	this.movingLeft = movingLeft;
    }

    @Override public void moveLeft(final float dt) {
	setPositionX(getPosition().x - velocity.x * dt);
    }

    @Override public void moveRight(final float dt) {
	setPositionX(getPosition().x + velocity.x * dt);
    }

}
