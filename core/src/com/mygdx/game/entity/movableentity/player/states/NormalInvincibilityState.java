package com.mygdx.game.entity.movableentity.player.states;

/**
 * Replica of normal state but invincible, used to not take massive damage instantly when colliding with enemy
 */
public class NormalInvincibilityState extends NormalState
{

    @Override public boolean isInvincible() {
	return !super.isInvincible(); //True
    }

    /**
     * Ignores superclass method on purpose as they should return different GameObject enum.
     * @return actual GameObject type
     */
    @Override public States getState() {
	return States.NORMAL_INVINCIBILITY_STATE;
    }
}
