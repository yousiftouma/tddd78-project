package com.mygdx.game.entity.movableentity.player;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.entity.CollisionEntity;
import com.mygdx.game.entity.Entity;
import com.mygdx.game.entity.GameObject;
import com.mygdx.game.entity.Side;
import com.mygdx.game.entity.movableentity.MovableEntity;
import com.mygdx.game.entity.movableentity.player.powerup.NormalState;
import com.mygdx.game.entity.movableentity.player.powerup.PowerupState;

import java.util.Collection;

/**
 * Playerclass, can jump, move and collide with objects
 */
public class Player extends MovableEntity {

    private int score;
    private PowerupState pstate;

    public Player(Sprite sprite, Vector2 position, Vector2 size, Vector2 velocity, Vector2 acceleration, int damage, int hitPointsMax) {
	    super(sprite, position, size, velocity, acceleration, damage, hitPointsMax);
	    score = 0;
        this.pstate = new NormalState();
    }


    public void jump() {
        pstate.jump(this);
    }

    @Override
    public void moveLeft(float dt) {
        pstate.moveLeft(this, dt);
    }

    @Override
    public void moveRight(float dt) {
        pstate.moveRight(this, dt);
    }

    @Override
    public void doAction(GameObject type, CollisionEntity object) {
        if (type == GameObject.WALL) {
            Side side = getCollisionSide(object);
            separateSide(side, object);

        } else if (type == GameObject.ENEMY) {
            if (!pstate.isInvincible()) {
                hitPointsLeft -= object.getDamage();
            }
        }
    }

    @Override
    public void onDeath(final Collection<Entity> objects) {
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
}
