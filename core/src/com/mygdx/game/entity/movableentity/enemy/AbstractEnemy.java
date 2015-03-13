package com.mygdx.game.entity.movableentity.enemy;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.entity.CollisionEntity;
import com.mygdx.game.entity.Entity;
import com.mygdx.game.entity.GameObject;
import com.mygdx.game.entity.Side;
import com.mygdx.game.entity.movableentity.MovableEntity;

import java.util.Collection;

/**
 * Abstract enemy class with abstract methods with different implementation for different types of enemies
 */
public class AbstractEnemy extends MovableEntity
{
    protected boolean movingLeft;

    protected AbstractEnemy(Sprite sprite, Vector2 position, Vector2 size, Vector2 velocity, Vector2 acceleration,
			    int damage, int hitPointsMax, boolean movingLeft)
    {
	super(sprite, position, size, velocity, acceleration, damage, hitPointsMax);
        this.movingLeft = movingLeft;
    }

    @Override public void doAction(GameObject type, CollisionEntity object) {
        if (type == GameObject.WALL) {
            Side side = getCollisionSide(object);
            separateSide(side, object);
	    if (side == Side.LEFT || side == Side.RIGHT) {
		movingLeft = !movingLeft;
	    }
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
        setPositionX(getPosition().x - velocity.x * dt);
    }

    @Override public void moveRight(final float dt) {
	setPositionX(getPosition().x + velocity.x * dt);
    }

    @Override
    public void onDeath(final Collection<Entity> objects) {
	remove(objects);
    }

}
