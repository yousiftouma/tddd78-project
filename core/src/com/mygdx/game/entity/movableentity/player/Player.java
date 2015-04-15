package com.mygdx.game.entity.movableentity.player;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.entity.CollisionEntity;
import com.mygdx.game.entity.GameObject;
import com.mygdx.game.entity.Side;
import com.mygdx.game.entity.movableentity.MovableEntity;
import com.mygdx.game.entity.movableentity.enemy.AbstractEnemy;
import com.mygdx.game.entity.movableentity.player.powerup.NormalState;
import com.mygdx.game.entity.movableentity.player.powerup.States;
import com.mygdx.game.entity.movableentity.player.powerup.PowerUpState;
import com.mygdx.game.screens.GameScreen;


/**
 * Playerclass, can jump, move and collide with objects
 */
public class Player extends MovableEntity
{

    private final static int NUMBER_OF_JUMPS = 2;
    private int score;
    private PowerUpState pState;
    private float powerUpTimer;
    private int jumpsCount = NUMBER_OF_JUMPS;

    public Player(Sprite sprite, Vector2 position, Vector2 size, Vector2 velocity, Vector2 acceleration, int damage,
		  int hitPointsMax)
    {
	super(sprite, position, size, velocity, acceleration, damage, hitPointsMax);
	score = 0;
	this.pState = new NormalState();
	this.powerUpTimer = 0;
    }

    public void jump() {
	if (jumpsCount > 0) {
	    pState.jump(this);
	    jumpsCount--;
	}

    }


    @Override public void moveLeft(float dt) {
	pState.moveLeft(this, dt);
    }

    @Override public void moveRight(float dt) {
	pState.moveRight(this, dt);
    }

    @Override public void doAction(GameObject type, CollisionEntity object) {
	Side side = getCollisionSide(object);
	switch (type) {
	    case WALL:
		separateSide(side, object);
		if (side == Side.TOP) {
		    jumpsCount = NUMBER_OF_JUMPS;
		}
		break;
	    case ENEMY:
		separateSide(side, object);
		if (side == Side.TOP) {
		    AbstractEnemy enemy = (AbstractEnemy) object;
		    enemy.takeDamage(this.damage);
		    GameScreen.getDealDamageSound().play();
		} /*else {
		    System.out.println("player detects enemy collision");
		    if (!pState.isInvincible()) {
			hitPointsLeft -= object.getDamage();
			this.pState = new NormalInvincibilityState();
			powerUpTimer = 3;
			System.out.println("taken damage:   " + this);
		    }
		}*/
		break;
	    case SMALL_STATIC_COIN:
	    case SMALL_MOVING_COIN:
	    case NORMAL_STATIC_POWER_UP:
		MovableEntity pwrUpOrCoin = (MovableEntity) object;
		pwrUpOrCoin.takeDamage(this.damage);
		GameScreen.getPickUpSound().play();
		break;
	    case PLAYER:
		//will never happen
		break;
	}
    }



    @Override public void update(float dt) {
	super.update(dt);
	if (pState.getState() != States.NORMAL_STATE) {
	    powerUpTimer -= dt;
	    pState.setSize(this);
	    System.out.println("powerup= " + powerUpTimer);
	    if (powerUpTimer <= 0) {
		this.pState = new NormalState();
		pState.setSize(this);
	    }
	}
    }

    public float getPowerUpTimer() {
	return powerUpTimer;
    }

    public PowerUpState getpState() {
	return pState;
    }

    public void setpState(final PowerUpState pState) {
	this.pState = pState;
    }

    public void setPowerUpTimer(float time) {
	powerUpTimer = time;
    }

    public int getScore() {
	return score;
    }

    public void setScore(final int score) {
	this.score = score;
    }

    @Override public GameObject getGameObjectType() {
	return GameObject.PLAYER;
    }

    @Override public String toString() {
   	return "Player{" +
   			       ", pos=" + getPosition().x + "x" + getPosition().y +
   			       ", velo=" + velocity.x + "x" + velocity.y +
   			       ", acc=" + acceleration.x + "x" + acceleration.y +
   			       ", size=" + getWidth() + "x" + getHeight() +
   			       ", hp=" + hitPointsLeft +
   			       '}';
       }
}
