package com.mygdx.game.entity.powerups;

/**
 * Interface that states that each factory for powerups needs to be able to create a
 * powerup.
 */
public interface PowerUpFactory {
    AbstractPowerUp createPowerUp();
}
