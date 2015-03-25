package com.mygdx.game.entity.powerups;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.entity.GameObject;

/**
 * Normal powerup, can return its durationtime, type and size
 */
public class NormalPowerUp extends AbstractPowerUp {

    private final static int POWER_UP_WIDTH = 16;
    private final static int POWER_UP_HEIGHT = 16;

    public NormalPowerUp(Sprite sprite, Vector2 position, Vector2 size, float powerUpTime) {
        super(sprite, position, size, powerUpTime);
    }

    @Override public GameObject getGameObjectType() {
	return GameObject.NORMAL_POWER_UP;
    }

    public static Vector2 getPowerUpSize(){
	return new Vector2(POWER_UP_WIDTH, POWER_UP_HEIGHT);
    }

    @Override public float getPowerUpTime() {
 	return powerUpTime;
     }
}
