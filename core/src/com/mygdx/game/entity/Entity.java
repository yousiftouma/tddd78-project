package com.mygdx.game.entity;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;


/**
 * Represents all drawable objects in the game.
 * Does not have abstract methods on purpose as they are not needed,
 * but is abstract as we want to create its subclasses
 */
public abstract class Entity  {

    private Sprite sprite;
    private Vector2 position;
    private Vector2 size;

    /**
     * @param sprite holds texture
     * @param position Vector2(x,y) which points to bottomleft of entity
     * @param size Vector2(x,y) where x = width, y = height
     */
    protected Entity(Sprite sprite, Vector2 position, Vector2 size) {
	this.sprite = sprite;
	this.position = position;
	this.size = size;
	sprite.setPosition(position.x, position.y);
	sprite.setSize(size.x, size.y);
    }

    public Sprite getSprite() {
	return sprite;
    }

    public void setSprite(final Sprite sprite) {
	this.sprite = sprite;
    }

    public Vector2 getPosition() {
	return position;
    }

    public void setPosition(final Vector2 position) {
	this.position = position;
	sprite.setPosition(position.x, position.y);
    }

    public void setPositionX(float posX){
        position = new Vector2(posX, position.y);
    }

    public void setPositionY(float posY){
        position = new Vector2(position.x, posY);
    }

    public Vector2 getSize() {
        return size;
    }

    public float getWidth(){
        return size.x;
    }

    public float getHeight(){
        return size.y;
    }

    public void setSize(final Vector2 size) {
	this.size = size;
	sprite.setSize(size.x, size.y);
    }

    public void setWidth(float width) {
        size = new Vector2(width, size.y);
    }

    public void setHeight(float height) {
        size = new Vector2(size.x, height);
    }

    public void draw(SpriteBatch batch){
	sprite.draw(batch);
    }

}
