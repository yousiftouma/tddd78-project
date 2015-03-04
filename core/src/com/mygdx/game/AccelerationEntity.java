package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

/**
 * Entity that handles acceleration
 */
public class AccelerationEntity extends VelocityEntity {

    private Vector2 acceleration;

    /**
     *
     * @param sprite sprite to represent entity
     * @param position Vector2(x,y) position in frame, 0,0 bottomleft
     * @param velocity Vector2(x velocity, y velocity), positive rightward and up
     * @param acceleration Vector2(x acc, y acc), positive rightward and up
     */
    public AccelerationEntity(final Sprite sprite, final Vector2 position, final Vector2 velocity, final Vector2 acceleration)
    {
        super(sprite, position, velocity);
        this.acceleration = acceleration;
    }

    @Override public void update() {
        super.update();
        this.getVelocity().add(acceleration);
    }
}
