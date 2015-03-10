package com.mygdx.game.entity;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Has a hitBox which can collide with others.
 *
 */
public abstract class CollisionEntity extends Entity {

    private Rectangle hitBox;
    public abstract Boolean hasCollision(Entity entity);

    public CollisionEntity(Sprite sprite, Vector2 position, Vector2 size) {
	super(sprite, position, size);
	//this.setSprite(sprite);
	this.hitBox = new Rectangle(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
    }

    @Override
    public void setPosition(final Vector2 position) {
	//this.position = position;
	setHitBoxX(position.x);
	setHitBoxY(position.y);
	super.setPosition(position);
	//getSprite().setPosition(position.x, position.y);
    }

    public Rectangle getHitBox() {
	return hitBox;
    }

    public void setHitBox(Rectangle hitBox) {
	this.hitBox = hitBox;
    }

    public void setHitBoxX(float x){
	this.hitBox.x = x;
    }

    public void setHitBoxY(float y){
	this.hitBox.y = y;
    }

}

