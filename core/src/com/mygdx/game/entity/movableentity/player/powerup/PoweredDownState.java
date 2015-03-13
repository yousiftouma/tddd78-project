package com.mygdx.game.entity.movableentity.player.powerup;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.entity.movableentity.player.Player;

/**
 * PowereddownState for player, with lower jump- and movementspeed, not invincible.
 */
public class PoweredDownState implements PowerUpState {
    private static final int JUMP_SPEED = 300;
    private static final int RUNNING_SPEED = 200;

    @Override
    public PowerUpEnum getState() {
        return PowerUpEnum.POWERED_DOWN_STATE;
    }

    @Override
    public void jump(Player p) {
            p.setVelocity(new Vector2(p.getVelocity().x, JUMP_SPEED));
        }

    @Override
    public void moveLeft(Player p, float dt) {
            p.setPositionX(p.getPosition().x - RUNNING_SPEED * dt);
        }

    @Override
    public void moveRight(Player p, float dt) {
            p.setPositionX(p.getPosition().x + RUNNING_SPEED * dt);
        }

    @Override
    public void setSize(Player p) {
        p.setSize(new Vector2(40, 40));
    }

    @Override
    public boolean isInvincible() {
            return false;
        }
}
