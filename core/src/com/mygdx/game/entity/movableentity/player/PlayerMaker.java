package com.mygdx.game.entity.movableentity.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Game;

/**
 * Can create a player, used by game to create a player for itself.
 */
public class PlayerMaker
{
    private static final int MAX_PLAYER_HP = 20;
    private static final int PLAYER_DMG = 10;
    private static final int PLAYER_WIDTH = 48;
    private static final int PLAYER_HEIGHT = 64;

    public Player createPlayer(){
	return new Player(new Sprite(new Texture(Gdx.files.internal("playertestbox.png"))), new Vector2(-100,-100),
			  new Vector2(PLAYER_WIDTH, PLAYER_HEIGHT), new Vector2(0, Game.getGravity()), new Vector2(0,0),
			  PLAYER_DMG, MAX_PLAYER_HP);
    }
}

