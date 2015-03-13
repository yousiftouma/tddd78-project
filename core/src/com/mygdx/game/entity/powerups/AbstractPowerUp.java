package com.mygdx.game.entity.powerups;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.entity.CollisionEntity;

public abstract class AbstractPowerUp extends CollisionEntity {
    private float powerUpTime;
    public AbstractPowerUp(Sprite sprite, Vector2 position, Vector2 size, int damage, float powerUpTime) {
        super(sprite, position, size, damage);
        this.powerUpTime = powerUpTime;
    }

    public float getPowerUpTime() {
        return powerUpTime;
    }
}
