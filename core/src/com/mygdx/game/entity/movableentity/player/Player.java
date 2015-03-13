package com.mygdx.game.entity.movableentity.player;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.entity.CollisionEntity;
import com.mygdx.game.entity.Entity;
import com.mygdx.game.entity.GameObject;
import com.mygdx.game.entity.Side;
import com.mygdx.game.entity.movableentity.MovableEntity;
import com.mygdx.game.entity.movableentity.player.powerup.NormalState;
import com.mygdx.game.entity.movableentity.player.powerup.States;
import com.mygdx.game.entity.movableentity.player.powerup.PowerUpState;
import com.mygdx.game.entity.movableentity.player.powerup.PoweredUpState;
import com.mygdx.game.entity.powerups.AbstractPowerUp;

import java.util.Collection;

/**
 * Playerclass, can jump, move and collide with objects
 */
public class Player extends MovableEntity {

    private int score;
    private PowerUpState pState;
    private float powerUpTimer;

    public Player(Sprite sprite, Vector2 position, Vector2 size, Vector2 velocity, Vector2 acceleration, int damage, int hitPointsMax) {
	    super(sprite, position, size, velocity, acceleration, damage, hitPointsMax);
	    score = 0;
        this.pState = new NormalState();
        this.powerUpTimer = 0;
    }

    public void setPState(PowerUpState pState){
        this.pState = pState;
    }

    public void jump() {
        pState.jump(this);
    }

    @Override
    public void moveLeft(float dt) {
        pState.moveLeft(this, dt);
    }

    @Override
    public void moveRight(float dt) {
        pState.moveRight(this, dt);
    }

    @Override
    public void doAction(GameObject type, CollisionEntity object) {
        if (type == GameObject.WALL) {
            Side side = getCollisionSide(object);
            separateSide(side, object);

        } else if (type == GameObject.ENEMY) {
            if (!pState.isInvincible()) {
                hitPointsLeft -= object.getDamage();
            }
        }
        else if (type == GameObject.NORMAL_POWER_UP) {
	    this.pState = new PoweredUpState();
	    powerUpTimer = ((AbstractPowerUp) object).getPowerUpTime();
            pState.setSize(this);
        }
    }

    @Override
    public void onDeath(final Collection<Entity> objects) {
    }

    @Override
    public void update(float dt) {
        super.update(dt);
        if (pState.getState() != States.NORMAL_STATE){
            powerUpTimer -= dt;
            if (powerUpTimer <= 0){
		this.pState = new NormalState();
		pState.setSize(this);
            }
        }
    }

    public void setPowerUpTimer(float time){
        powerUpTimer = time;
    }

    public int getScore() {
	return score;
    }

    public void setScore(final int score) {
	this.score = score;
    }

    public void addScore(final int points){
	this.score += points;
    }

    @Override public GameObject getGameObjectType() {
	return GameObject.PLAYER;
    }
}
