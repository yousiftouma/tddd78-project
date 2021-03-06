package com.mygdx.game.entity.movableentity;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Game;
import com.mygdx.game.entity.CollisionEntity;
import com.mygdx.game.entity.GameObject;
import com.mygdx.game.entity.Side;


/**
 * Entity that can move
 * These entities can also die (in some sense, they have some sort of lifespan), so they have hitpoints
 * Also have acceleration and velocity vectors beside spawnpositions
 * Like CollisionEntity we never instantiate this class so it's abstract, with inherited
 * abstract methods
 */
public abstract class MovableEntity extends CollisionEntity
{
    /**
     * Libgdx class Vector2
     */
    protected Vector2 velocity;
    protected Vector2 acceleration;
    protected int hitPointsLeft;

    /**
     * when colliding, we perform an action, depending on self and other object
     * @param type what type of gameobject we collide with
     * @param object actual object to interact with
     */
    public abstract void doAction(GameObject type, CollisionEntity object);

    /**
     * maximum velocity a MovableEntity can achieve
     */
    public final static int MAX_FREE_FALL_VELOCITY = 1000;

    /**
     * The default velocity for a MovableEntity
     */
    public final static int DEFAULT_VELOCITY_X = 100;


    protected MovableEntity(Sprite sprite, Vector2 position, Vector2 size, Vector2 velocity, Vector2 acceleration, int damage,
			    int hitPointsMax) {
        super(sprite, position, size, damage);
        this.velocity = velocity;
        this.acceleration = acceleration;
        this.hitPointsLeft = hitPointsMax;
    }

    public Vector2 getVelocity() {
        return velocity;
    }


    public void setVelocity(final Vector2 velocity) {
        this.velocity = velocity;
    }


    /**
     * primarily updates fallspeed according to gravity
     * @param dt delta time since last frame update
     */
    public void update(float dt) {
	setPositionY(getPosition().y + velocity.y * dt);
        if (Math.abs(velocity.y) < MAX_FREE_FALL_VELOCITY) {
            velocity.y += acceleration.y * dt;
        }
        teleportIfOutsideFrame();
    }

    public void moveLeft(final float dt) {
	setPositionX(getPosition().x - velocity.x * dt);
    }

    public void moveRight(final float dt) {
	setPositionX(getPosition().x + velocity.x * dt);
    }

    /**
     * Seperates our MovableEntity from the CollisionEntity we collided with, depending on
     * what side we collided with
     *
     * @param side   side we collided with
     * @param object object we collided with
     */
    public void separateSide(Side side, CollisionEntity object) {
        if (side == Side.TOP) {
            //to avoid setting fallspeed to 0 if not actually on top
            if (velocity.y < 0) {
                velocity.y = 0;
            }
            setPosition(new Vector2(getPosition().x, object.getPosition().y + object.getHeight()));
        } else if (side == Side.BOTTOM) {
            //to start falling upon collision with bottom
            if (velocity.y > 0) {
                velocity.y = 0;
            }
            setPosition(new Vector2(getPosition().x, object.getPosition().y - getHeight()));
        } else if (side == Side.LEFT) {
            setPosition(new Vector2(object.getPosition().x - getWidth(), getPosition().y));
        } else if (side == Side.RIGHT) {
            setPosition(new Vector2(object.getPosition().x + object.getWidth(), getPosition().y));
        }
    }

    private void teleportIfOutsideFrame() {
        // entity has its full size outside the frame to the left
        if (getPosition().x < -getWidth()) {
            setPosition(new Vector2(Game.FRAME_WIDTH, getPosition().y));
        }

        // entity has its full size outside the frame to the right
        else if (getPosition().x > Game.FRAME_WIDTH) {
            setPosition(new Vector2(-getWidth(), getPosition().y));
        }

        // entity has its full size outside the bottom of the frame
        else if (getPosition().y < -getHeight()) {
            setPosition(new Vector2(getPosition().x, Game.FRAME_HEIGHT));
        }
    }

    public int getHitPointsLeft() {
        return hitPointsLeft;
    }

    public void takeDamage(int dmg){
	assert (dmg >= 0): "trying to deal negative damage";
	hitPointsLeft -= dmg;
    }

    @Override public String toString() {
	return "MovableEntity{" +
			       ", pos=" + getPosition().x + "x" + getPosition().y +
			       ", velo=" + velocity.x + "x" + velocity.y +
			       ", acc=" + acceleration.x + "x" + acceleration.y +
			       ", size=" + getWidth() + "x" + getHeight() +
			       ", hp=" + hitPointsLeft +
			       '}';
    }
}
