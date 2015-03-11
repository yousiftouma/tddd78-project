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
    public abstract Boolean hasCollision(CollisionEntity object);

    public CollisionEntity(Sprite sprite, Vector2 position, Vector2 size) {
	super(sprite, position, size);
	//this.setSprite(sprite);
	this.hitBox = new Rectangle(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
    }

    @Override
    public void setPosition(final Vector2 position) {
	//this.position = position;
        hitBox.setPosition(position);
	super.setPosition(position);
	//getSprite().setPosition(position.x, position.y);
    }

    public Rectangle getHitBox() {
	return hitBox;
    }

    public void setHitBox(Rectangle hitBox) {
	this.hitBox = hitBox;
    }

    public Side getCollisionSide(CollisionEntity object){
        double topDif = Math.abs(getPosition().y - (object.getPosition().y+object.getSize().y));

        double bottomDif = Math.abs( getPosition().y+getSize().y - object.getPosition().y );

        double leftDif = Math.abs( getPosition().x+getSize().x - object.getPosition().x );

        double rightDif = Math.abs( getPosition().x - (object.getPosition().x+object.getSize().x) );

        double minDif = Math.min(Math.min(topDif, bottomDif), Math.min(leftDif, rightDif));

        if (minDif == topDif){
            return Side.TOP;
        }
        else if (minDif == bottomDif){
            return Side.BOTTOM;
        }
        else if (minDif == leftDif){
            return Side.LEFT;
        }
        else{
            return Side.RIGHT;
        }
    }

}

