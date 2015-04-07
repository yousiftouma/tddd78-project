package com.mygdx.game.entity.movableentity.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Game;

/**
 * Used to create a player.
 * Don't need Factory since we only create one player.
 */
public class PlayerMaker
{
    private int damage;
    private int hitpoints;
    private int width;
    private int height;

    public PlayerMaker(int damage, int hitpoints, int width, int height) {
	this.damage = damage;
	this.hitpoints = hitpoints;
	this.width = width;
	this.height = height;
    }

    public Player createPlayer(){
	return new Player(new Sprite(new Texture(Gdx.files.internal("playertestbox.png"))), new Vector2(-100,-100),
			  new Vector2(width, height), new Vector2(0, 0), new Vector2(0, -Game.getGravity()),
			  damage, hitpoints);
    }
}

