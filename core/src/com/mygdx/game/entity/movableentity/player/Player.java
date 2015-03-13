package com.mygdx.game.entity.movableentity.player;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.entity.CollisionEntity;
import com.mygdx.game.entity.Entity;
import com.mygdx.game.entity.GameObject;
import com.mygdx.game.entity.Side;
import com.mygdx.game.entity.movableentity.MovableEntity;

import java.util.Collection;

/**
 * Playerclass, can jump, move and collide with objects
 */
public class Player extends MovableEntity {

    private int score;
    private static final int JUMP_SPEED = 500;

    public Player(Sprite sprite, Vector2 position, Vector2 size, Vector2 velocity, Vector2 acceleration, int damage, int hitPointsMax) {
	super(sprite, position, size, velocity, acceleration, damage, hitPointsMax);
	score = 0;
    }


    public void jump() {
        setVelocity(new Vector2(getVelocity().x, JUMP_SPEED));
    }

    @Override
    public void moveLeft(float dt) {
	setPosition(new Vector2(getPosition().x - velocity.x * dt, getPosition().y));
    }

    @Override
    public void moveRight(float dt) {
        setPosition(new Vector2(getPosition().x + velocity.x * dt, getPosition().y));
    }

    @Override
    public void doAction(GameObject type, CollisionEntity object) {
        if (type == GameObject.WALL) {
            Side side = getCollisionSide(object);
            separateSide(side, object);

        } else if (type == GameObject.ENEMY) {
            hitPointsLeft -= object.getDamage();
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
