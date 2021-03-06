package com.mygdx.game.entity;


import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;


/**
 * Represents all drawable objects in the game.
 * Is abstract as diffrent Entities will have different types for example and
 * we don't need to instantiate this class because of that
 */
public abstract class Entity  {

    /**
     * libgdx class Sprite, drawable class with spritebatch (see GameScreen)
     */
    private Sprite sprite;
    /**
     * Libgdx class Vector2
     * In libgdx, Vector2 can be used as both points (position) and size
     */
    private Vector2 position;
    private Vector2 size;

    /**
     * will return different types for different subclasses
     * @return enum GameObject
     */
    public abstract GameObject getGameObjectType();

    /**
     * Init entity and set its sprites position and size accordingly
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

    public Vector2 getPosition() {
	return position;
    }

    /**
     * setters for position need to set position of both the entityobject itself but also
     * its sprite
     * @param pos Vector2(x,y)
     */
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

    public float getWidth(){
        return size.x;
    }

    public float getHeight(){
        return size.y;
    }

    /**
     * as with setters for position, this functions in the same manner
     * @param size Vector2(width, height)
     */
    public void setSize(final Vector2 size) {
	this.size = size;
	sprite.setSize(size.x, size.y);
    }

    /**
     * draws a sprite using a batch
     * @param batch libgdx class batch
     */
    public void draw(Batch batch){
	sprite.draw(batch);
    }

}
