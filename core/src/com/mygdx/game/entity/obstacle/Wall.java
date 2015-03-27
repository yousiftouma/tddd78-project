package com.mygdx.game.entity.obstacle;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.entity.CollisionEntity;
import com.mygdx.game.entity.GameObject;


/**
 * Wall, floor and ceiling class used for platformfunctionality. Detects collision.
 */
public class Wall extends CollisionEntity
{

    /**
     * @param sprite   Sprite that represents the wall
     * @param position Vector2 (x,y) bottomleft position of sprite, 0,0 bottom left in frame
     * @param size     Vector2 (width, height)
     * default damage to 0 for walls
     */
    public Wall(final Sprite sprite, final Vector2 position, final Vector2 size) {
	super(sprite, position, size, 0);
    }

    @Override public GameObject getGameObjectType() {
	return GameObject.WALL;
    }
}

