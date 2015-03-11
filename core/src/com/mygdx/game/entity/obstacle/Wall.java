package com.mygdx.game.entity.obstacle;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.entity.CollisionEntity;
import com.mygdx.game.entity.Entity;


/**
 * Wall, floor and ceiling class used for platformfunctionality. Detects collision.
 */
public class Wall extends CollisionEntity
{

    /**
     * @param sprite Sprite that represents the wall
     * @param position Vector2 (x,y) bottomleft position of sprite, 0,0 bottom left in frame
     * @param size Vector2 (width, height)
     */
    public Wall(final Sprite sprite, final Vector2 position, final Vector2 size) {
	super(sprite, position, size);
    }

    @Override
    public Boolean hasCollision(final Entity entity) {
	return null;
    }

    @Override
    public void draw(SpriteBatch batch){
	super.draw(batch);
    }

}
