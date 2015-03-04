package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Entity.Entity;
import com.mygdx.game.Entity.Obstacle.Wall;

public class Game extends ApplicationAdapter {
	SpriteBatch batch;
	Texture floorimg;
	Sprite floorSprite;
	Entity floorEntity;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		floorimg = new Texture(Gdx.files.internal("truck_material-new-256.png"));
		floorSprite = new Sprite(floorimg);
		floorEntity = new Wall(floorSprite, new Vector2(100, 50), new Vector2(500, 100));
	}


	@Override
	public void render () {
		Gdx.gl.glClearColor(0.7F, 0.5F, 0.2F, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		floorEntity.draw(batch);
		batch.end();
	}
}
