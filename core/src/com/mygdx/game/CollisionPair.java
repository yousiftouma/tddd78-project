package com.mygdx.game;


import com.mygdx.game.entity.CollisionEntity;
import com.mygdx.game.entity.movableentity.MovableEntity;

/**
 * Class to represent a collisionpair tuple, used to store collisions in the current
 * gameloop before handling them
 */
public class CollisionPair
{
    private MovableEntity movableObject;
    private CollisionEntity collisionObject;

    public CollisionPair(final MovableEntity movableObject, final CollisionEntity collisionObject) {
	this.movableObject = movableObject;
	this.collisionObject = collisionObject;
    }

    public MovableEntity getMovableObject() {
	return movableObject;
    }

    public CollisionEntity getCollisionObject() {
	return collisionObject;
    }
}
