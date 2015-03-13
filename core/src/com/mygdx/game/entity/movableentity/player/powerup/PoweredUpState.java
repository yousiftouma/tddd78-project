package com.mygdx.game.entity.movableentity.player.powerup;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.entity.movableentity.player.Player;

/**
 * PoweredupState for player, with higher jump and movementspeed, player is also invincible.
 */
public class PoweredUpState implements PowerUpState
{
    private static final int JUMP_SPEED = 600;
    private static final int RUNNING_SPEED = 600;
    private static final int NORMAL_PLAYER_WIDTH = 96;
    private static final int NORMAL_PLAYER_HEIGHT = 96;

    @Override public States getState() {
	return States.POWERED_UP_STATE;
    }

    @Override public void jump(Player p) {
	p.setVelocity(new Vector2(p.getVelocity().x, JUMP_SPEED));
    }

    @Override public void moveLeft(Player p, float dt) {
	p.setPositionX(p.getPosition().x - RUNNING_SPEED * dt);
    }

    @Override public void moveRight(Player p, float dt) {
	p.setPositionX(p.getPosition().x + RUNNING_SPEED * dt);
    }

    @Override public void setSize(Player p) {
	p.setSize(new Vector2(NORMAL_PLAYER_WIDTH, NORMAL_PLAYER_HEIGHT));
    }

    @Override public boolean isInvincible() {
	return true;
    }
}
