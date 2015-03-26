package com.mygdx.game.entity.movableentity.powerups;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.entity.GameObject;


/**
 * Normal powerup, can return its durationtime, type and size
 */
public class NormalStaticPowerUp extends AbstractPowerUp {

    private final static int POWER_UP_WIDTH = 16;
    private final static int POWER_UP_HEIGHT = 16;
    private final static float POWER_UP_TIME = 10;

    public NormalStaticPowerUp(Sprite sprite, Vector2 position, Vector2 size, Vector2 velocity, Vector2 acceleration) {
        super(sprite, position, size, velocity, acceleration);
	setPowerUpTime(POWER_UP_TIME);
    }

    @Override public GameObject getGameObjectType() {
	return GameObject.NORMAL_STATIC_POWER_UP;
    }

    public static Vector2 getPowerUpSize(){
	return new Vector2(POWER_UP_WIDTH, POWER_UP_HEIGHT);
    }

}
