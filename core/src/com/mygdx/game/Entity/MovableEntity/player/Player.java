package com.mygdx.game.entity.movableentity.player;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.entity.Entity;
import com.mygdx.game.entity.movableentity.MovableEntity;

/**
 * Created by Yousif Touma on 2015-03-04.
 */
public class Player extends MovableEntity
{

    public Player(Sprite sprite, Vector2 position, Vector2 size, Vector2 velocity, Vector2 acceleration)
    {
	super(sprite, position, size, velocity, acceleration);
    }

    public void jump(){
	velocity.y = 5;
    }

    @Override public void moveLeft(float dt) {
	setHitBoxX(getHitBox().x - (velocity.x * dt));
	getSprite().setPosition(getHitBox().x, getHitBox().y);

    }

    @Override public void moveRight(float dt) {
	setHitBoxX(getHitBox().x + (velocity.x * dt));
	getSprite().setPosition(getHitBox().x, getHitBox().y);
    }

    @Override public void doAction(String type, float x, float y) {
	if (type.equals("wall")){
	    setVelocity(new Vector2(getVelocity().x, 0));
	    setPosition(new Vector2(getHitBox().x, y));
	}
    }

    @Override
    public Boolean hasCollision(Entity entity) {
	Rectangle checkRectangle = new Rectangle(entity.getSprite().getX(), entity.getSprite().getY(), entity.getSprite().getWidth(), entity.getSprite().getHeight());
	if (getHitBox().overlaps(checkRectangle)){
	    //setCollided(rectangle);
	    return true;
	}
	return false;
    }
}
