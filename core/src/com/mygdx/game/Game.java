package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.entity.Entity;
import com.mygdx.game.entity.obstacle.Wall;


public class Game extends ApplicationAdapter {
	private SpriteBatch batch;
	private Texture floorimg;
	private Sprite floorSprite;
	private Entity floorEntity;

    /**
     * Runs upon starting game, like a main method, creates all content
     */
	@Override
	public void create () {
		batch = new SpriteBatch();
		floorimg = new Texture(Gdx.files.internal("truck_material-new-256.png"));
		floorSprite = new Sprite(floorimg);
		floorEntity = new Wall(floorSprite, new Vector2(100, 50), new Vector2(500, 100));
	}

    /**
     * Closes the program when window is closed
     */
    @Override
    public void dispose() {
	super.dispose();
    }

    /**
     * Renders everything, like a component
     */
	@Override
	public void render () {
		Gdx.gl.glClearColor(0.7F, 0.5F, 0.2F, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		floorEntity.draw(batch);
		batch.end();

	    // Updates here
	    // Controls here
	}
}
