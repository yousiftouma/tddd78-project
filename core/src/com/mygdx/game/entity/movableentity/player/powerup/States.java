package com.mygdx.game.entity.movableentity.player.powerup;

/**
 * Enum representing each player state
 */
public enum States
{
    /**
     * Normal jump- and runspeed
     */
    NORMAL_STATE,
    /**
     * Faster movement and higher jump
     */
    POWERED_UP_STATE,
    /**
     * Slower movement and lower jump
     */
    POWERED_DOWN_STATE,
    /**
     * Normal state but invincible, used after taking damage
     */
    NORMAL_INVINCIBILITY_STATE
}
