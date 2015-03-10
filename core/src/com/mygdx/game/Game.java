package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.entity.Entity;
import com.mygdx.game.entity.movableentity.player.Player;
import com.mygdx.game.entity.obstacle.Wall;

import java.util.ArrayList;
import java.util.List;


public class Game extends ApplicationAdapter {
	private SpriteBatch batch;
	private Wall floorLeft, floorRight;
    	private Player player;
    	private Texture wallTexture;
    	private Texture staticPlayerTexture;
    	private List<Wall> walls;

    /**
     * height of game window
     */
    public static final int FRAME_HEIGHT = 480;
    /**
     * width of game window
     */
    public static final int FRAME_WIDTH = 640;
    /**
     * the thickness of a platform
     */
    public static final int PLATFORM_THICKNESS = 10;

    /**
     * Simulates normal gravity
     */
    public static final int NORMAL_GRAVITY = 10;

    /**
     * Default spped
     */
    public static final int DEFAULT_VELOCITY_X = 200;

    /**
     * Runs upon starting game, like a main method, creates all content
     */
	@Override
	public void create () {

	    createTextures();
	    batch = new SpriteBatch();
	    createGameObjects();
	}

    private void createGameObjects() {
	walls = new ArrayList<Wall>();
	floorLeft = new Wall(new Sprite(wallTexture), new Vector2(0,0), new Vector2(FRAME_WIDTH / 3.0f, PLATFORM_THICKNESS));
	walls.add(floorLeft);
	floorRight = new Wall(new Sprite(wallTexture), new Vector2(2*(FRAME_WIDTH / 3.0f), 0), new Vector2(FRAME_WIDTH / 3.0f, PLATFORM_THICKNESS));
	walls.add(floorRight);
	player = new Player(new Sprite(staticPlayerTexture), new Vector2(100,300), new Vector2(64,64), new Vector2(DEFAULT_VELOCITY_X,0), new Vector2(0, NORMAL_GRAVITY));
    }

    private void createTextures() {
	wallTexture = new Texture(Gdx.files.internal("truck_material-new-256.png"));
	staticPlayerTexture = new Texture(Gdx.files.internal("static1.png"));
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
	    // begin drawing
	    batch.begin();
	    floorLeft.draw(batch);
	    floorRight.draw(batch);
	    player.draw(batch);
	    batch.end();
	    // stopped drawing

	    // Updates here
	    player.update(Gdx.graphics.getDeltaTime());
	    for (Wall wall : walls){
		if (player.hasCollision(wall)){
		    player.doAction("wall", wall.getHitBox().x, wall.getHitBox().y + wall.getHitBox().height); // not correct, need to put player correctly
		}
	    }

	    // Controls here
	    if (Gdx.input.isKeyPressed(Keys.LEFT)){
		player.moveLeft(Gdx.graphics.getDeltaTime());
	    }
	    if (Gdx.input.isKeyPressed(Keys.RIGHT)){
		player.moveRight(Gdx.graphics.getDeltaTime());
	    }
	    if (Gdx.input.isKeyPressed(Keys.UP) || Gdx.input.isKeyPressed(Keys.SPACE)){
		player.jump();
	    }
	}
}
