package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.entity.Entity;
import com.mygdx.game.maps.Map1;

/**
 * Handles the rendering of the game
 */
public class GameRenderer extends ApplicationAdapter {
    private SpriteBatch batch;
    private Game gameToDraw;

    private static final float RED = 0.7f;
    private static final float GREEN = 0.5f;
    private static final float BLUE = 0.2f;

    /**
     * Runs upon starting launcher, creates all content
     */
    @Override
    public void create() {
        gameToDraw = new Game(Map1.getInstance()); //needs to be dynamic
        batch = new SpriteBatch();
    }

    /**
     *
     * Closes the program when window is closed
     */
    /*
    @Override
    public void dispose() {
        super.dispose();
    }
    */


    /**
     * Renders everything, like a component
     */
    @Override
    public void render() {
        //backgroundcolor, rgba
        Gdx.gl.glClearColor(RED, GREEN, BLUE, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // begin drawing here
        batch.begin();
        for(Entity object : gameToDraw.getGameObjects()){
	    object.draw(batch);
	}
        batch.end();
        // stopped drawing here

        // Updates here
	gameToDraw.updateGame();

        // Controls here
	gameToDraw.handleMovement();
        }
    }

