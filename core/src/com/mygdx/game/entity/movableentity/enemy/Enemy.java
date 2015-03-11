package com.mygdx.game.entity.movableentity.enemy;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.entity.CollisionEntity;
import com.mygdx.game.entity.GameObject;
import com.mygdx.game.entity.movableentity.MovableEntity;

/**
 * Created by Yousif Touma on 2015-03-04.
 */
public class Enemy extends MovableEntity
{

    public Enemy(Sprite sprite, Vector2 position, Vector2 size, Vector2 velocity, Vector2 acceleration)
    {
	super(sprite, position, size, velocity, acceleration);
    }

    @Override public void moveLeft(final float dt) {

    }

    @Override public void moveRight(final float dt) {

    }

    @Override public void doAction(GameObject type, CollisionEntity object) {

    }

    // want to check what we collide with and probably change direction or similar.
    @Override
    public Boolean hasCollision(final CollisionEntity object) {
	return null;
    }
}
