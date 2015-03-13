package com.mygdx.game.entity.movableentity.player.powerup;

import com.mygdx.game.entity.movableentity.player.Player;

public interface PowerupState {
    public void jump(Player p);

    public void moveLeft(Player p, float dt);

    public void moveRight(Player p, float dt);

    public boolean isInvincible();
}
