package com.mygdx.game.entity.movableentity.player.states;

import com.mygdx.game.entity.movableentity.player.Player;

/**
 * Interface for different powerupstates, stating the methods that has to be present in a state
 */
public interface State
{
    public void jump(Player p);

    public void moveLeft(Player p, float dt);

    public void moveRight(Player p, float dt);

    public void setSize(Player p);

    public States getState();

    public boolean isInvincible();
}
