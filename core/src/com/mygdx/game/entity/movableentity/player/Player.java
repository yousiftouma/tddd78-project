package com.mygdx.game.entity.movableentity.player;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.entity.CollisionEntity;
import com.mygdx.game.entity.GameObject;
import com.mygdx.game.entity.Side;
import com.mygdx.game.entity.movableentity.MovableEntity;
import com.mygdx.game.entity.movableentity.enemy.AbstractEnemy;
import com.mygdx.game.entity.movableentity.player.states.NormalState;
import com.mygdx.game.entity.movableentity.player.states.States;
import com.mygdx.game.entity.movableentity.player.states.State;
import com.mygdx.game.screens.GameScreen;


/**
 * Playerclass, can jump, move and collide with objects
 */
public class Player extends MovableEntity
{

    private final static int ALLOWED_CONSECUTIVE_JUMPS = 2;
    private int score;
    private State pState;
    private float powerUpTimer;
    private int jumpsCount = ALLOWED_CONSECUTIVE_JUMPS;

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


    /**
     * ignores superclass method as intended as movement is handled
     * based on input and state
     * @param dt time since last update, used for calculating movement
     */
    public void moveLeft(float dt) {
	pState.moveLeft(this, dt);
    }

    /**
     * ignores superclass method as intended as movement is handled
     * based on input and state
     * @param dt time since last update, used for calculating movement
     */
    public void moveRight(float dt) {
	pState.moveRight(this, dt);
    }

    @Override public void doAction(GameObject type, CollisionEntity object) {
	Side side = getCollisionSide(object);
	switch (type) {
	    case WALL:
		separateSide(side, object);
		if (side == Side.TOP) {
		    jumpsCount = ALLOWED_CONSECUTIVE_JUMPS;
		}
		break;
	    case ENEMY:
		// we deal damage to enemy, never take
		separateSide(side, object);
		if (side == Side.TOP) {
		    AbstractEnemy enemy = (AbstractEnemy) object;
		    enemy.takeDamage(this.damage);
		    GameScreen.getDealDamageSound().play();
		}
		break;
	    case NORMAL_MOVING_POWER_DOWN:
	    case SMALL_STATIC_COIN:
	    case SMALL_MOVING_COIN:
	    case NORMAL_STATIC_POWER_UP:
		// all 4 above are handled the same way
		MovableEntity pwrUpOrCoin = (MovableEntity) object;
		pwrUpOrCoin.takeDamage(this.damage);
		GameScreen.getPickUpSound().play();
		break;
	    case PLAYER:
		//will never happen
		break;
	}
    }


    /**
     * If we are powered up or down we progress the timer
     * If timer has run out, we return to normal state
     * @param dt delta time since last frame update
     */
    @Override public void update(float dt) {
	super.update(dt);
	if (pState.getState() != States.NORMAL_STATE) {
	    powerUpTimer -= dt;
	    pState.setSize(this);
	    if (powerUpTimer <= 0) {
		this.pState = new NormalState();
		pState.setSize(this);
	    }
	}
    }

    public State getpState() {
	return pState;
    }

    public void setpState(final State pState) {
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
