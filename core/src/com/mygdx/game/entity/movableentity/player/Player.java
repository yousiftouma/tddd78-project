package com.mygdx.game.entity.movableentity.player;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.entity.CollisionEntity;
import com.mygdx.game.entity.GameObject;
import com.mygdx.game.entity.Side;
import com.mygdx.game.entity.movableentity.MovableEntity;

/**
 * Created by Yousif Touma on 2015-03-04.
 */
public class Player extends MovableEntity {

    private static final float JUMP_SPEED = 500;

    public Player(Sprite sprite, Vector2 position, Vector2 size, Vector2 velocity, Vector2 acceleration) {
        super(sprite, position, size, velocity, acceleration);
    }

    public void jump() {
        setVelocity(new Vector2(getVelocity().x, JUMP_SPEED));
    }

    @Override
    public void moveLeft(float dt) {
        setPosition(new Vector2(getPosition().x - velocity.x * dt, getPosition().y));

    }

    @Override
    public void moveRight(float dt) {
        setPosition(new Vector2(getPosition().x + velocity.x * dt, getPosition().y));
    }

    @Override
    public void doAction(GameObject type, CollisionEntity object) {
        if (type == GameObject.WALL) {
            Side side = getCollisionSide(object);
            separateSide(side, object);

        } else if (type == GameObject.ENEMY) {
            setPosition(spawnPosition);
        }
    }
}
