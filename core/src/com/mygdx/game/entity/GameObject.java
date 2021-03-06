package com.mygdx.game.entity;

/**
 * Enum representing each object in the gameworld
 */
public enum GameObject {
    /**
     * Walls, floors, ceilings, platforms
     */
    WALL,
    /**
     * Enemy
     */
    ENEMY,
    /**
     * Player
     */
    PLAYER,
    /**
     * Small nonmoving coin
     */
    SMALL_STATIC_COIN,
    /**
     * Small moving coin
     */
    SMALL_MOVING_COIN,
    /**
     * normal nonmoving powerUp
     */
    NORMAL_STATIC_POWER_UP,
    /**
     * normal, moving powerDown
     */
    NORMAL_MOVING_POWER_DOWN


}
