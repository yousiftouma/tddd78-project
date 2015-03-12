package com.mygdx.game.entity.movableentity.enemy;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.entity.CollisionEntity;
import com.mygdx.game.entity.GameObject;
import com.mygdx.game.entity.Side;
import com.mygdx.game.entity.movableentity.MovableEntity;

/**
 * Abstract enemy class with abstract methods with different implementation for different types of enemies
 */
public class Enemy extends MovableEntity
{
    private boolean movingLeft;

    public Enemy(Sprite sprite, Vector2 position, Vector2 size, Vector2 velocity, Vector2 acceleration, boolean movingLeft, int damage, int hitPointsMax)
    {
	super(sprite, position, size, velocity, acceleration, damage, hitPointsMax);
        this.movingLeft = movingLeft;
    }

    @Override public void doAction(GameObject type, CollisionEntity object) {
        if (type == GameObject.WALL) {
            Side side = getCollisionSide(object);
            separateSide(side, object);
        }
    }

    @Override
    public void update(float dt) {
        super.update(dt);
        if (movingLeft){
            moveLeft(dt);
        }
        else{
            moveRight(dt);
        }
    }

    @Override public void moveLeft(final float dt) {
        setPosition(new Vector2(getPosition().x-velocity.x*dt, getPosition().y));
    }

    @Override public void moveRight(final float dt) {
        setPosition(new Vector2(getPosition().x + velocity.x * dt, getPosition().y));
    }

    @Override
    public void onDeath() {
    }

}
