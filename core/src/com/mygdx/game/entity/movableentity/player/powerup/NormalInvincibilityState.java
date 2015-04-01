package com.mygdx.game.entity.movableentity.player.powerup;

/**
 * Replica of normal state but invincible, used to not take massive damage when colliding with enemy
 */
public class NormalInvincibilityState extends NormalState
{

    @Override public boolean isInvincible() {
	return !super.isInvincible(); //True
    }
}
