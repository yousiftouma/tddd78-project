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
public abstract class AbstractEnemy extends MovableEntity
{
    protected boolean movingLeft;

    protected AbstractEnemy(Sprite sprite, Vector2 position, Vector2 size, Vector2 velocity, Vector2 acceleration,
			    int damage, int hitPointsMax, boolean movingLeft)
    {
	super(sprite, position, size, velocity, acceleration, damage, hitPointsMax);
        this.movingLeft = movingLeft;
    }

    @Override public void doAction(GameObject type, CollisionEntity object) {
        Side side = getCollisionSide(object);
        if (type == GameObject.WALL) {
            separateSide(side, object);
            if (side == Side.LEFT || side == Side.RIGHT) {
                movingLeft = !movingLeft;
            }
        }
        if (type == GameObject.ENEMY) {
            separateSide(side, object);
            if (side == Side.LEFT) {
                movingLeft = false;
                AbstractEnemy collidedEnemy = (AbstractEnemy)object;
                collidedEnemy.setMovingLeft(true);
            }
            else if (side == Side.RIGHT) {
                movingLeft = true;
                AbstractEnemy collidedEnemy = (AbstractEnemy)object;
                collidedEnemy.setMovingLeft(false);

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

    public void setMovingLeft(boolean movingLeft) {
        this.movingLeft = movingLeft;
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
