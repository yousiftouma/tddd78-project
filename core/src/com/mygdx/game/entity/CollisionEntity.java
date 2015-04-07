package com.mygdx.game.entity;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Has a hitBox which can collide with other hitBoxes.
 * Doesn't need abstract methods but is abstract cause we only want to create its subclasses
 */
public abstract class CollisionEntity extends Entity {

    private final static float ACCEPTABLE_ERROR = 0.001f;
    protected Rectangle hitBox;
    protected int damage;

    protected CollisionEntity(Sprite sprite, Vector2 position, Vector2 size, int damage) {
	    super(sprite, position, size);
	    this.hitBox = new Rectangle(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
        this.damage = damage;
    }

    public Rectangle getHitBox() {
	return hitBox;
    }

    @Override
    public void setPosition(final Vector2 pos) {
	super.setPosition(pos);
        hitBox.setPosition(pos);
    }

    @Override public void setPositionX(final float posX) {
	super.setPositionX(posX);
	hitBox.setPosition(posX, getPosition().y);
    }

    @Override public void setPositionY(final float posY) {
	super.setPositionY(posY);
	hitBox.setPosition(getPosition().x, posY);
    }

    @Override public void setSize(final Vector2 size) {
	super.setSize(size);
	hitBox.setSize(size.x, size.y);
    }

    @Override public void setWidth(final float width) {
	super.setWidth(width);
	hitBox.setSize(width, getHeight());
    }

    @Override public void setHeight(final float height) {
	super.setHeight(height);
	hitBox.setSize(getWidth(), height);
    }

    public boolean hasCollision(CollisionEntity object) {
	// this is correct, wouldn't want to check collision with self
	if (this != object) {
	    if (hitBox.overlaps(object.hitBox)) {
		return true;
	    }
	}
	return false;
    }


    /**
     * Checks where the overlap is smallest, thus determining which side we have collided with
     * comparisons are equalitychecks that are acceptable for double datatype
     * @param object object that we check collision with
     * @return returns which side we've collided with
     */
    public Side getCollisionSide(CollisionEntity object){
        double topDif = Math.abs(getPosition().y - (object.getPosition().y + object.getHeight()));

        double bottomDif = Math.abs((getPosition().y + getHeight()) - object.getPosition().y);

        double leftDif = Math.abs((getPosition().x + getWidth()) - object.getPosition().x);

        double rightDif = Math.abs(getPosition().x - (object.getPosition().x + object.getWidth()));

        double minDif = Math.min(Math.min(topDif, bottomDif), Math.min(leftDif, rightDif));

	if (Math.abs(minDif - topDif) < ACCEPTABLE_ERROR){
	    return Side.TOP;
	}
        else if (Math.abs(minDif - bottomDif) < ACCEPTABLE_ERROR){
            return Side.BOTTOM;
        }
        else if (Math.abs(minDif - leftDif) < ACCEPTABLE_ERROR){
            return Side.LEFT;
        }
        else {
            return Side.RIGHT;
        }
    }

    public int getDamage(){
        return damage;
    }
}

