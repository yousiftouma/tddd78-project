package com.mygdx.game.entity;


import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;


/**
 * Represents all drawable objects in the game.
 * Is abstract as diffrent Entities will have different types for example
 */
public abstract class Entity  {

    private Sprite sprite;
    /**
     * In libgdx, Vector2 can be used as both points (position) and size
     */
    private Vector2 position;
    private Vector2 size;
    public abstract GameObject getGameObjectType();

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

    public void setPosition(final Vector2 pos) {
	this.position = pos;
	sprite.setPosition(position.x, position.y);
    }

    public void setPositionX(float posX){
        this.position = new Vector2(posX, position.y);
	sprite.setPosition(posX, position.y);
    }

    public void setPositionY(float posY){
        this.position = new Vector2(position.x, posY);
	sprite.setPosition(position.x, posY);
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

    public void draw(Batch batch){
	sprite.draw(batch);
    }

}
