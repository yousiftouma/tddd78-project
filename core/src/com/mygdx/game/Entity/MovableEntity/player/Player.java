package com.mygdx.game.entity.movableentity.player;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.entity.Collidable;
import com.mygdx.game.entity.Entity;
import com.mygdx.game.entity.movableentity.MovableEntity;

/**
 * Created by Yousif Touma on 2015-03-04.
 */
public class Player extends MovableEntity implements Collidable
{

    public Player(final Sprite sprite, final Vector2 position, final Vector2 velocity, final Vector2 acceleration)
    {
	super(sprite, position, velocity, acceleration);
    }

    @Override public Boolean checkCollision(final Entity entity) {
	return null;
    }
}
