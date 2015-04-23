package com.mygdx.game.entity.movableentity.player.states;

/**
 * Replica of normal state but invincible, used to not take massive damage when colliding with enemy
 */
public class NormalInvincibilityState extends NormalState
{

    @Override public boolean isInvincible() {
	return !super.isInvincible(); //True
    }

    //as intended, shouldn't return same state
    @Override public States getState() {
	return States.NORMAL_INVINCIBILITY_STATE;
    }
}
