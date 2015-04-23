package com.mygdx.game.entity.movableentity.powerups;

/**
 * Interface that states that each factory for powerups needs to be able to create a
 * states.
 */
public interface PowerUpFactory {
    AbstractPowerUp createPowerUp();
}
