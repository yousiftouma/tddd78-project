package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Game;
import com.mygdx.game.GameWindow;
import com.mygdx.game.entity.Entity;
import com.mygdx.game.entity.obstacle.Wall;
import com.mygdx.game.maps.GameMap;
import com.mygdx.game.maps.Map1;

/**
 * Renders everything
 */
public class GameScreen implements Screen
{
    private static final float RED = 0.7f;
    private static final float GREEN = 0.5f;
    private static final float BLUE = 0.2f;

    private SpriteBatch batch;
    private Game gameToDraw;
    private String scoreDisplay;
    private BitmapFont bmf;

    /**
     * Contains the map to render
     * @param mapNumber which map to render in screen, must be within range
     */
    public GameScreen(int mapNumber) {
	assert (0 <= mapNumber && mapNumber <=10); //necessary?
	GameMap mapToPlay;
	switch (mapNumber) {
	    case 1:
		mapToPlay = Map1.getInstance();
		break;
	    default:
		mapToPlay = Map1.getInstance();
	}
	this.gameToDraw = new Game(mapToPlay);
	this.batch = new SpriteBatch();
    }

    @Override public void show() {
	scoreDisplay = "score: 0";
	bmf = new BitmapFont();
    }

    @Override public void render(final float delta) {
	//backgroundcolor, rgba
	Gdx.gl.glClearColor(RED, GREEN, BLUE, 1);
	Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	scoreDisplay = "score: " + gameToDraw.getPlayer().getScore();

	 // begin drawing here
        batch.begin();
	for (Wall wall : gameToDraw.getObstacles()) {
	    wall.draw(batch);
	}
	for(Entity object : gameToDraw.getGameObjects()){
	    object.draw(batch);
	}
	gameToDraw.getPlayer().draw(batch);
	bmf.setColor(1.0f, 1.0f, 1.0f, 1.0f);
	bmf.draw(batch, scoreDisplay, 10, Game.FRAME_HEIGHT - 10);
        batch.end();
        // stopped drawing here

        // Updates here
	gameToDraw.updateGame(delta);

        // Controls here
	gameToDraw.handleMovement(delta);
    }

    @Override public void resize(final int width, final int height) {

    }

    @Override public void pause() {

    }

    @Override public void resume() {

    }

    @Override public void hide() {

    }

    @Override public void dispose() {

    }
}
