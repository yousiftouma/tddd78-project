package com.mygdx.game.entity.movableentity.player.powerup;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.entity.movableentity.player.Player;

public class PoweredupState implements PowerupState{
    private static final int JUMP_SPEED = 800;
    private static final int RUNNING_SPEED = 600;

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
    public boolean isInvincible() {
            return true;
        }
}
